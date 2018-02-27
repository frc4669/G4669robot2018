/**
 * Example logic for firing and managing motion profiles.
 * This example sends MPs, waits for them to finish
 * Although this code uses a CANTalon, nowhere in this module do we changeMode() or call set() to change the output.
 * This is done in Robot.java to demonstrate how to change control modes on the fly.
 * 
 * The only routines we call on Talon are....
 * 
 * changeMotionControlFramePeriod
 * 
 * getMotionProfileStatus		
 * clearMotionProfileHasUnderrun     to get status and potentially clear the error flag.
 * 
 * pushMotionProfileTrajectory
 * clearMotionProfileTrajectories
 * processMotionProfileBuffer,   to push/clear, and process the trajectory points.
 * 
 * getControlMode, to check if we are in Motion Profile Control mode.
 * 
 * Example of advanced features not demonstrated here...
 * [1] Calling pushMotionProfileTrajectory() continuously while the Talon executes the motion profile, thereby keeping it going indefinitely.
 * [2] Instead of setting the sensor position to zero at the start of each MP, the program could offset the MP's position based on current position. 
 */
package org.usfirst.frc.team4669.robot.motionProfile;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;

import org.usfirst.frc.team4669.robot.RobotMap;

import com.ctre.phoenix.motion.*;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;

public class MotionProfileExample {

	/**
	 * The status of the motion profile executer and buffer inside the Talon.
	 * Instead of creating a new one every time we call getMotionProfileStatus,
	 * keep one copy.
	 */
	private MotionProfileStatus _statusL = new MotionProfileStatus();
	private MotionProfileStatus _statusR = new MotionProfileStatus();

	/** additional cache for holding the active trajectory point */
	double _posL=0,_velL=0,_headingL=0;
	double _posR=0,_velR=0,_headingR=0;

	/**
	 * reference to the talon we plan on manipulating. We will not changeMode()
	 * or call set(), just get motion profile status and make decisions based on
	 * motion profile.
	 */
	private TalonSRX _talonL;
	private TalonSRX _talonR;
	/**
	 * State machine to make sure we let enough of the motion profile stream to
	 * talon before we fire it.
	 */
	private int _state = 0;
	/**
	 * Any time you have a state machine that waits for external events, its a
	 * good idea to add a timeout. Set to -1 to disable. Set to nonzero to count
	 * down to '0' which will print an error message. Counting loops is not a
	 * very accurate method of tracking timeout, but this is just conservative
	 * timeout. Getting time-stamps would certainly work too, this is just
	 * simple (no need to worry about timer overflows).
	 */
	private int _loopTimeout = -1;
	/**
	 * If start() gets called, this flag is set and in the control() we will
	 * service it.
	 */
	private boolean _bStart = false;

	/**
	 * Since the CANTalon.set() routine is mode specific, deduce what we want
	 * the set value to be and let the calling module apply it whenever we
	 * decide to switch to MP mode.
	 */
	private SetValueMotionProfile _setValue = SetValueMotionProfile.Disable;
	/**
	 * How many trajectory points do we wait for before firing the motion
	 * profile.
	 */
	private static final int kMinPointsInTalon = 5;
	/**
	 * Just a state timeout to make sure we don't get stuck anywhere. Each loop
	 * is about 20ms.
	 */
	private static final int kNumLoopsTimeout = 10;
	
	private Profile _path;
	
	/**
	 * Lets create a periodic task to funnel our trajectory points into our talon.
	 * It doesn't need to be very accurate, just needs to keep pace with the motion
	 * profiler executer.  Now if you're trajectory points are slow, there is no need
	 * to do this, just call _talon.processMotionProfileBuffer() in your teleop loop.
	 * Generally speaking you want to call it at least twice as fast as the duration
	 * of your trajectory points.  So if they are firing every 20ms, you should call 
	 * every 10ms.
	 */
	class PeriodicRunnable implements java.lang.Runnable {
	    public void run() {
	    	_talonL.processMotionProfileBuffer();
	    	_talonR.processMotionProfileBuffer();
	    }
	    
	}
	Notifier _notifer = new Notifier(new PeriodicRunnable());

	

	/**
	 * C'tor
	 * 
	 * @param talon
	 *            reference to Talon object to fetch motion profile status from.
	 */
	public MotionProfileExample(Profile path,TalonSRX talonL,TalonSRX talonR) {
		_path = path;
		_talonL = talonL;
		_talonR = talonR;
		/*
		 * since our MP is 10ms per point, set the control frame rate and the
		 * notifer to half that
		 */
		_talonL.changeMotionControlFramePeriod(5);
		_talonR.changeMotionControlFramePeriod(5);
		_notifer.startPeriodic(0.005);
	}

	/**
	 * Called to clear Motion profile buffer and reset state info during
	 * disabled and when Talon is not in MP control mode.
	 */
	public void reset() {
		/*
		 * Let's clear the buffer just in case user decided to disable in the
		 * middle of an MP, and now we have the second half of a profile just
		 * sitting in memory.
		 */
		_talonL.clearMotionProfileTrajectories();
		_talonR.clearMotionProfileTrajectories();
		/* When we do re-enter motionProfile control mode, stay disabled. */
		_setValue = SetValueMotionProfile.Disable;
		/* When we do start running our state machine start at the beginning. */
		_state = 0;
		_loopTimeout = -1;
		/*
		 * If application wanted to start an MP before, ignore and wait for next
		 * button press
		 */
		_bStart = false;
	}

	/**
	 * Called every loop.
	 */
	public void control() {
		/* Get the motion profile status every loop */
		_talonL.getMotionProfileStatus(_statusL);
		_talonR.getMotionProfileStatus(_statusR);

		/*
		 * track time, this is rudimentary but that's okay, we just want to make
		 * sure things never get stuck.
		 */
		if (_loopTimeout < 0) {
			/* do nothing, timeout is disabled */
		} else {
			/* our timeout is nonzero */
			if (_loopTimeout == 0) {
				/*
				 * something is wrong. Talon is not present, unplugged, breaker
				 * tripped
				 */
				Instrumentation.OnNoProgress();
			} else {
				--_loopTimeout;
			}
		}

		/* first check if we are in MP mode */
		if (_talonL.getControlMode() != ControlMode.MotionProfile&&_talonR.getControlMode() != ControlMode.MotionProfile) {
			/*
			 * we are not in MP mode. We are probably driving the robot around
			 * using gamepads or some other mode.
			 */
			_state = 0;
			_loopTimeout = -1;
		} else {
			/*
			 * we are in MP control mode. That means: starting Mps, checking Mp
			 * progress, and possibly interrupting MPs if thats what you want to
			 * do.
			 */
			switch (_state) {
				case 0: /* wait for application to tell us to start an MP */
					if (_bStart) {
						_bStart = false;
	
						_setValue = SetValueMotionProfile.Disable;
						startFilling();
						/*
						 * MP is being sent to CAN bus, wait a small amount of time
						 */
						_state = 1;
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 1: /*
						 * wait for MP to stream to Talon, really just the first few
						 * points
						 */
					/* do we have a minimum numberof points in Talon */
					if (_statusL.btmBufferCnt > kMinPointsInTalon&&_statusR.btmBufferCnt > kMinPointsInTalon) {
						/* start (once) the motion profile */
						_setValue = SetValueMotionProfile.Enable;
						/* MP will start once the control frame gets scheduled */
						_state = 2;
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 2: /* check the status of the MP */
					/*
					 * if talon is reporting things are good, keep adding to our
					 * timeout. Really this is so that you can unplug your talon in
					 * the middle of an MP and react to it.
					 */
					if (_statusL.isUnderrun == false&&_statusR.isUnderrun == false) {
						_loopTimeout = kNumLoopsTimeout;
					}
					/*
					 * If we are executing an MP and the MP finished, start loading
					 * another. We will go into hold state so robot servo's
					 * position.
					 */
					if (_statusL.activePointValid && _statusL.isLast&&_statusR.activePointValid && _statusR.isLast) {
						/*
						 * because we set the last point's isLast to true, we will
						 * get here when the MP is done
						 */
						_setValue = SetValueMotionProfile.Hold;
						_state = 0;
						_loopTimeout = -1;
					}
					break;
			}

			/* Get the motion profile status every loop */
			_talonL.getMotionProfileStatus(_statusL);
			_headingL = _talonL.getActiveTrajectoryHeading();
			_posL = _talonL.getActiveTrajectoryPosition();
			_velL = _talonL.getActiveTrajectoryVelocity();
			
			_talonR.getMotionProfileStatus(_statusR);
			_headingR = _talonR.getActiveTrajectoryHeading();
			_posR = _talonR.getActiveTrajectoryPosition();
			_velR = _talonR.getActiveTrajectoryVelocity();

			/* printfs and/or logging */
			Instrumentation.process(_statusL, _posL, _velL, _headingL,_statusR, _posR, _velR, _headingR);
		}
	}
	/**
	 * Find enum value if supported.
	 * @param durationMs
	 * @return enum equivalent of durationMs
	 */
	private TrajectoryDuration GetTrajectoryDuration(int durationMs)
	{	 
		/* create return value */
		TrajectoryDuration retval = TrajectoryDuration.Trajectory_Duration_0ms;
		/* convert duration to supported type */
		retval = retval.valueOf(durationMs);
		/* check that it is valid */
		if (retval.value != durationMs) {
			DriverStation.reportError("Trajectory Duration not supported - use configMotionProfileTrajectoryPeriod instead", false);		
		}
		/* pass to caller */
		return retval;
	}
	/** Start filling the MPs to all of the involved Talons. */
	private void startFilling() {
		/* since this example only has one talon, just update that one */
		System.out.println("Begin filling with points");
		startFilling(_path.getLeft(),_path.getRight(), _path.kNumPointsL(),_path.kNumPointsR());
		
	}

	private void startFilling(double[][] profileL,double[][] profileR, int totalCntL,int totalCntR) {

		/* create an empty point */
		TrajectoryPoint pointL = new TrajectoryPoint();
		TrajectoryPoint pointR = new TrajectoryPoint();
		System.out.println("Creating Trajectory Points");

		/* did we get an underrun condition since last time we checked ? */
		if (_statusL.hasUnderrun||_statusR.hasUnderrun) {
			/* better log it so we know about it */
			Instrumentation.OnUnderrun();
			/*
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 */
			_talonL.clearMotionProfileHasUnderrun(0);
			_talonR.clearMotionProfileHasUnderrun(0);
		}
		/*
		 * just in case we are interrupting another MP and there is still buffer
		 * points in memory, clear it.
		 */
		_talonL.clearMotionProfileTrajectories();
		_talonR.clearMotionProfileTrajectories();
		System.out.println("Clearing Profiles");

		/* set the base trajectory period to zero, use the individual trajectory period below */
		_talonL.configMotionProfileTrajectoryPeriod(RobotMap.baseTrajPeriodMs, RobotMap.timeout);
		_talonR.configMotionProfileTrajectoryPeriod(RobotMap.baseTrajPeriodMs, RobotMap.timeout);
		
		/* This is fast since it's just into our TOP buffer */
		for (int i = 0; i < totalCntL; ++i) {
			double positionRotL = profileL[i][0];
			double velocityRPML = profileL[i][1];
			double positionRotR = profileR[i][0];
			double velocityRPMR = profileR[i][1];
			/* for each point, fill our structure and pass it to API */
			pointL.position = positionRotL * RobotMap.encoderTicksPerRotation; //Convert Revolutions to Units
			pointL.velocity = velocityRPML * RobotMap.encoderTicksPerRotation / 600.0; //Convert RPM to Units/100ms
			pointL.headingDeg = 0; /* future feature - not used in this example*/
			pointL.profileSlotSelect0 = 0; /* which set of gains would you like to use [0,3]? */
			pointL.profileSlotSelect1 = 0; /* future feature  - not used in this example - cascaded PID [0,1], leave zero */
			pointL.timeDur = GetTrajectoryDuration((int)profileL[i][2]);
			pointL.zeroPos = false;
			
			pointR.position = positionRotR * RobotMap.encoderTicksPerRotation; //Convert Revolutions to Units
			pointR.velocity = velocityRPMR * RobotMap.encoderTicksPerRotation / 600.0; //Convert RPM to Units/100ms
			pointR.headingDeg = 0; /* future feature - not used in this example*/
			pointR.profileSlotSelect0 = 0; /* which set of gains would you like to use [0,3]? */
			pointR.profileSlotSelect1 = 0; /* future feature  - not used in this example - cascaded PID [0,1], leave zero */
			pointR.timeDur = GetTrajectoryDuration((int)profileR[i][2]);
			pointR.zeroPos = false;
			if (i == 0){
				pointL.zeroPos = true; /* set this to true on the first point */
				pointR.zeroPos = true;
			}
			pointL.isLastPoint = false;
			pointR.isLastPoint = false;
			if ((i + 1) == totalCntL){
				pointL.isLastPoint = true; /* set this to true on the last point  */
				pointR.isLastPoint = true;
			}
			_talonL.pushMotionProfileTrajectory(pointL);
			_talonR.pushMotionProfileTrajectory(pointR);
			System.out.println("Pushing Trajectory points");
		}
	}
	/**
	 * Called by application to signal Talon to start the buffered MP (when it's
	 * able to).
	 */
	public void startMotionProfile() {
		_bStart = true;
	}

	/**
	 * 
	 * @return the output value to pass to Talon's set() routine. 0 for disable
	 *         motion-profile output, 1 for enable motion-profile, 2 for hold
	 *         current motion profile trajectory point.
	 */
	SetValueMotionProfile getSetValue() {
		return _setValue;
	}
	
	public boolean isLastL(){
		return _statusL.isLast;
	}
	
	public boolean isLastR(){
		return _statusL.isLast;
	}
}
