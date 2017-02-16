package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import com.ctre.*;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private static final double kAngleSetpoint = 0.0;
	private static final double kP = 0.005;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon frontLeftMotor;
	private CANTalon rearLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon rearRightMotor;
	
	
	private RobotDrive driveTrain;
	
	private Gyro analogGyro;
	
	public DriveTrain() {
		super();
		frontLeftMotor = new CANTalon(RobotMap.driveTrainFrontLeft);
		rearLeftMotor = new CANTalon(RobotMap.driveTrainRearLeft);
		frontRightMotor = new CANTalon(RobotMap.driveTrainFrontRight);
		rearRightMotor = new CANTalon(RobotMap.driveTrainRearRight);
		
		driveTrain = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		
		frontLeftMotor.enable();
		frontLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
		frontLeftMotor.setSafetyEnabled(true);
		frontLeftMotor.enableBrakeMode(true);
		frontLeftMotor.reverseOutput(RobotMap.reverseOutputTrain);
		frontLeftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontLeftMotor.configEncoderCodesPerRev(360);
		
		rearLeftMotor.enable();
		rearLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
		rearLeftMotor.setSafetyEnabled(true);
		rearLeftMotor.enableBrakeMode(true);
		rearLeftMotor.reverseOutput(RobotMap.reverseOutputTrain);
		rearLeftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearLeftMotor.configEncoderCodesPerRev(360);
		
		frontRightMotor.enable();
		frontRightMotor.changeControlMode(TalonControlMode.PercentVbus);
		frontRightMotor.setSafetyEnabled(true);
		frontRightMotor.enableBrakeMode(true);
		frontRightMotor.reverseOutput(RobotMap.reverseOutputTrain);
		frontRightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRightMotor.configEncoderCodesPerRev(360);
		
		rearRightMotor.enable();
		rearRightMotor.changeControlMode(TalonControlMode .PercentVbus );
		rearRightMotor.setSafetyEnabled(true);
		rearRightMotor.enableBrakeMode(true);
		rearRightMotor.reverseOutput(RobotMap.reverseOutputTrain);
		rearRightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearRightMotor.configEncoderCodesPerRev(360);
		
		analogGyro = new ADXRS450_Gyro();
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TankDrive());
    }
    
    public void driveForward(double speedLeft, double speedRight) {
    	driveTrain.tankDrive(-1*speedLeft, -1*speedRight, false);
    }
    
    public void drive(double outputMag, double outputCurv) {
    	driveTrain.drive(outputMag, outputCurv);
    }
    
    public void setDrive(double speed, double turnrate) {
    	driveTrain.drive(speed, turnrate);
    }
    
    public void stop() {
    	driveTrain.tankDrive(0, 0);
    }
    
    public void calibrateGyro() {
    	analogGyro.calibrate();
    }
    
    public double getGyroAngle() {
    	return analogGyro.getAngle();
    }
    
    public double calculateTurningValue(double direction) {
    	double turningValue = kAngleSetpoint - analogGyro.getAngle() * kP;
    	turningValue = Math.copySign(turningValue, direction);
    	return turningValue;
    }
    
    public double getLeftEncoder() {
    	return frontLeftMotor.getEncPosition();
    }
    
    public double getRightEncoder() {
    	return frontRightMotor.getEncPosition();
    }
    
    public double getEnconderVel() {
    	return frontRightMotor.getEncVelocity();
    }
    
    public void zeroEncoders() {
    	frontLeftMotor.setPosition(0);
    	frontRightMotor.setPosition(0);
    }
    
    public void changeControlMode(TalonControlMode mode) {
    	frontLeftMotor.changeControlMode(mode);
    	rearLeftMotor.changeControlMode(mode);
    	frontRightMotor.changeControlMode(mode);
    	rearRightMotor.changeControlMode(mode);
    }
    

    // Beware Motion Profile stuff
    
	/**
	 * The status of the motion profile executer and buffer inside the Talon.
	 * Instead of creating a new one every time we call getMotionProfileStatus,
	 * keep one copy.
	 */
	private CANTalon.MotionProfileStatus _status = new CANTalon.MotionProfileStatus();

	/**
	 * reference to the talon we plan on manipulating. We will not changeMode()
	 * or call set(), just get motion profile status and make decisions based on
	 * motion profile.
	 */
//	private CANTalon frontLeftMotor;
	
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
	private CANTalon.SetValueMotionProfile _setValue = CANTalon.SetValueMotionProfile.Disable;
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
	    	frontLeftMotor.processMotionProfileBuffer();
	    	rearLeftMotor.processMotionProfileBuffer();
	    	frontRightMotor.processMotionProfileBuffer();
	    	rearRightMotor.processMotionProfileBuffer();
	    }
	}
	Notifier _notifer = new Notifier(new PeriodicRunnable());
	

	/**
	 * C'tor
	 * 
	 * @param talon
	 *            reference to Talon object to fetch motion profile status from.
	 */
	public void setupMotionProfile() {
		/*
		 * since our MP is 10ms per point, set the control frame rate and the
		 * notifer to half that
		 */
		frontLeftMotor.changeMotionControlFramePeriod(5);
		rearLeftMotor.changeMotionControlFramePeriod(5);
		frontRightMotor.changeMotionControlFramePeriod(5);
		rearRightMotor.changeMotionControlFramePeriod(5);
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
		frontLeftMotor.clearMotionProfileTrajectories();
		rearLeftMotor.clearMotionProfileTrajectories();
		frontRightMotor.clearMotionProfileTrajectories();
		rearRightMotor.clearMotionProfileTrajectories();
		/* When we do re-enter motionProfile control mode, stay disabled. */
		_setValue = CANTalon.SetValueMotionProfile.Disable;
		/* When we do start running our state machine start at the beginning. */
		set_state(0);
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
		frontLeftMotor.getMotionProfileStatus(_status);
		rearLeftMotor.getMotionProfileStatus(_status);
		frontRightMotor.getMotionProfileStatus(_status);
		rearRightMotor.getMotionProfileStatus(_status);
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
//				instrumentation.OnNoProgress();
			} else {
				--_loopTimeout;
			}
		}

		/* first check if we are in MP mode */
		if (frontLeftMotor.getControlMode() != TalonControlMode.MotionProfile || 
			rearLeftMotor.getControlMode() != TalonControlMode.MotionProfile ||
			frontRightMotor.getControlMode() != TalonControlMode.MotionProfile ||
			rearRightMotor.getControlMode() != TalonControlMode.MotionProfile) {
			/*
			 * we are not in MP mode. We are probably driving the robot around
			 * using gamepads or some other mode.
			 */
			set_state(0);
			_loopTimeout = -1;
			
		} else {
			/*
			 * we are in MP control mode. That means: starting Mps, checking Mp
			 * progress, and possibly interrupting MPs if thats what you want to
			 * do.
			 */
			switch (get_state()) {
				case 0: /* wait for application to tell us to start an MP */
					if (_bStart) {
						_bStart = false;
						
						_setValue = CANTalon.SetValueMotionProfile.Disable;
						
						startFilling();
						/*
						 * MP is being sent to CAN bus, wait a small amount of time
						 */
						set_state(1);
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 1: /*
						 * wait for MP to stream to Talon, really just the first few
						 * points
						 */
					
					/* do we have a minimum numberof points in Talon */
					if (_status.btmBufferCnt > kMinPointsInTalon) {
						/* start (once) the motion profile */
						_setValue = CANTalon.SetValueMotionProfile.Enable;
						/* MP will start once the control frame gets scheduled */
						set_state(2);
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 2: /* check the status of the MP */
					/*
					 * if talon is reporting things are good, keep adding to our
					 * timeout. Really this is so that you can unplug your talon in
					 * the middle of an MP and react to it.
					 */
					
					if (_status.isUnderrun == false) {
						_loopTimeout = kNumLoopsTimeout;
					}
					/*
					 * If we are executing an MP and the MP finished, start loading
					 * another. We will go into hold state so robot servo's
					 * position.
					 */
					if (_status.activePointValid && _status.activePoint.isLastPoint) {
						/*
						 * because we set the last point's isLast to true, we will
						 * get here when the MP is done
						 */
						_setValue = CANTalon.SetValueMotionProfile.Hold;
						set_state(0);
						_loopTimeout = -1;
					}
					break;
			}
		}
		/* printfs and/or logging */
//		instrumentation.process(_status);
	}

	/** Start filling the MPs to all of the involved Talons. */
	private void startFilling() {
		/* since this example only has one talon, just update that one */
		
		/**
		 * generate
		 * 
		 * Creates either a triangular or trapezoidal motion profile depending on
		 * distance required and maximum velocity
		 * 
		 * acc - acceleration in rotations/sec^2
		 * dec - deceleration in rotations/sec^2
		 * maxV - maximum velocity in rotations/sec
		 * beginPos - begin position in rotations
		 * endPos - end position in rotations
		 * interval - profile interval in sec
		 * profile - array of double[3] with 
		 * 		the position in rotations
		 * 		the velocity in RPM
		 * 		the duration in ms
		 */
		
		double acc = .2;
		double dec = .1;
		double maxV = 1.0;
		double beginPos = 0;
		double endPos = 6;
		double interval = .01; 
		
		double accDuration = maxV /acc;
		double decDuration = maxV/ dec;
		double accLength = acc * accDuration * accDuration /2;
		double decLength = dec * decDuration * decDuration /2;
		double positionChange = endPos - beginPos;
		double ad = acc * 60;
		double dd = dec * 60;
		double ad2 = acc /2;
		double dd2 = dec /2;
		double intervalMs = interval * 1000;
		double direction;
		double maxVDir;
		if (positionChange < 0) {
			direction = -1;
			ad2 = -ad2;
			dd2 = -dd2;
			ad = -ad;
			dd = -dd;
			maxVDir = -maxV * 60;
		} else {
			direction = 1;
			maxVDir = -maxV * 60;
		}
		double maxVDuration; 
		if (positionChange*direction < accLength+decLength) { // triangular profile
			accDuration = Math.sqrt(positionChange*direction * 2 /(acc + acc*acc /dec));
			decDuration = accDuration * acc /dec;
			accLength = acc * accDuration * accDuration /2;
			decLength = dec * decDuration * decDuration /2;
			maxVDuration = 0;
		} else { // trapezoidal profile
			maxVDuration = (positionChange*direction - accLength - decLength) / maxV;
		}	
		double totalTime = accDuration+decDuration+maxVDuration;
		double decTime = totalTime - decDuration;
		int size = (int) Math.floor(totalTime / interval);
		double time = interval;
		double[][] profile = new double[size][3];
		double maxPos = beginPos+accLength * direction;
		for (int x = 0; x < size-1; ++x) {
			if (time < accDuration) {
				profile[x][0] = ad2 * time * time + beginPos;
				profile[x][1] = ad * time;
				profile[x][2] = intervalMs;
			} else if (time > decTime) {
				double t = totalTime - time;
				profile[x][0] = endPos - dd2 * t * t;
				profile[x][1] = dd * t;
				profile[x][2] = intervalMs;
			} else {
				double t = time - accDuration;
				profile[x][0] = maxPos +maxVDir * t;
				profile[x][1] = maxVDir;
				profile[x][2] = intervalMs;
			}
			time += interval;
		}
		profile[size-1][0] = endPos;
		profile[size-1][1] = 0;
		profile[size-1][2] = (totalTime - size * interval )*1000;
		
		startFilling(profile, size);
	}

	private void startFilling(double[][] profile, int totalCnt) {

		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		/* did we get an underrun condition since last time we checked ? */
		if (_status.hasUnderrun) {
			/* better log it so we know about it */
//			instrumentation.OnUnderrun();
			/*
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 */
			frontLeftMotor.clearMotionProfileHasUnderrun();
			rearLeftMotor.clearMotionProfileHasUnderrun();
			frontRightMotor.clearMotionProfileHasUnderrun();
			rearRightMotor.clearMotionProfileHasUnderrun();
		}
		/*
		 * just in case we are interrupting another MP and there is still buffer
		 * points in memory, clear it.
		 */
		frontLeftMotor.clearMotionProfileTrajectories();
		rearLeftMotor.clearMotionProfileTrajectories();
		frontRightMotor.clearMotionProfileTrajectories();
		rearRightMotor.clearMotionProfileTrajectories();

		/* This is fast since it's just into our TOP buffer */
		for (int i = 0; i < totalCnt; ++i) {
			/* for each point, fill our structure and pass it to API */
			point.position = profile[i][0];
			point.velocity = profile[i][1];
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* set true to not do any position
										 * servo, just velocity feedforward
										 */
			point.zeroPos = false;
			if (i == 0)
				point.zeroPos = true; /* set this to true on the first point */

			point.isLastPoint = false;
			if ((i + 1) == totalCnt)
				point.isLastPoint = true; /* set this to true on the last point  */

			frontLeftMotor.pushMotionProfileTrajectory(point);
			rearLeftMotor.pushMotionProfileTrajectory(point);
			frontRightMotor.pushMotionProfileTrajectory(point);
			rearRightMotor.pushMotionProfileTrajectory(point);
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
	public CANTalon.SetValueMotionProfile getSetValue() {
		return _setValue;
	}

	public void set(double value) {
		frontLeftMotor.set(value);
		rearLeftMotor.set(value);
		frontRightMotor.set(value);
		rearRightMotor.set(value);
	}
	
	public void set(int value) {
		frontLeftMotor.set(value);
		rearLeftMotor.set(value);
		frontRightMotor.set(value);
		rearRightMotor.set(value);
	}

	public int get_state() {
		return _state;
	}

	public void set_state(int _state) {
		this._state = _state;
	}
	
	
}
    
    


