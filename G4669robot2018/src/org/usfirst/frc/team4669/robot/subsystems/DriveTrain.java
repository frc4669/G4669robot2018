package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.TankDrive;
import org.usfirst.frc.team4669.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private static final double kAngleSetpoint = 0.0;
	private static final double kP = 0.005;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private WPI_TalonSRX topLeftMotor;
	private WPI_TalonSRX bottomLeftMotor;
	private WPI_TalonSRX topRightMotor;
	private WPI_TalonSRX bottomRightMotor;
	
	int timeout = RobotMap.timeout;
	int slotIdx = RobotMap.slotIdx;
	int pidIdx = RobotMap.pidIdx;
	
	private Gyro analogGyro;
	
	public DriveTrain() {
		super();
		analogGyro = new ADXRS450_Gyro();
		
		topLeftMotor = new WPI_TalonSRX(RobotMap.driveTrainTopLeft);
		bottomLeftMotor = new WPI_TalonSRX(RobotMap.driveTrainBottomLeft);
		topRightMotor = new WPI_TalonSRX(RobotMap.driveTrainTopRight);
		bottomRightMotor = new WPI_TalonSRX(RobotMap.driveTrainBottomRight);
		
		int velocity = 200;
		int accel = 50;
		
		topLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,pidIdx,timeout);
		topLeftMotor.setInverted(false);
		//topLeftMotor.configEncoderCodesPerRev(1440); // if using
		// FeedbackDevice.QuadEncoder
		// _talon.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 1 means full */
		topLeftMotor.configNominalOutputForward(0, timeout);
		topLeftMotor.configNominalOutputReverse(0, timeout);
		topLeftMotor.configPeakOutputForward(1, timeout);
		topLeftMotor.configPeakOutputReverse(-1, timeout);
		/* set closed loop gains in slot0 - see documentation */
		topLeftMotor.selectProfileSlot(slotIdx,pidIdx);
		topLeftMotor.config_kF(slotIdx,0.3739,timeout);
		topLeftMotor.config_kP(slotIdx,0.3,timeout);
		topLeftMotor.config_kI(slotIdx,0,timeout);
		topLeftMotor.config_kD(slotIdx,16,timeout);
		/* set acceleration and vcruise velocity - see documentation */
		topLeftMotor.configMotionCruiseVelocity(velocity,timeout);
		topLeftMotor.configMotionAcceleration(accel,timeout);
		topLeftMotor.setSelectedSensorPosition(0,pidIdx,timeout);

		bottomLeftMotor.set(ControlMode.Follower, topLeftMotor.getDeviceID());
		
		topRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,pidIdx,timeout);
		topRightMotor.setInverted(false);
		//topRightMotor.configEncoderCodesPerRev(1440); // if using
		// FeedbackDevice.QuadEncoder
		// _talon.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 1 means full */
		topRightMotor.configNominalOutputForward(0, timeout);
		topRightMotor.configNominalOutputReverse(0, timeout);
		topRightMotor.configPeakOutputForward(1, timeout);
		topRightMotor.configPeakOutputReverse(-1, timeout);
		/* set closed loop gains in slot0 - see documentation */
		topRightMotor.selectProfileSlot(slotIdx, pidIdx);
		topRightMotor.config_kF(slotIdx,0.3739,timeout);
		topRightMotor.config_kP(slotIdx,0.3,timeout);
		topRightMotor.config_kI(slotIdx,0,timeout);
		topRightMotor.config_kD(slotIdx,16,timeout);
		/* set acceleration and vcruise velocity - see documentation */
		topRightMotor.configMotionCruiseVelocity(velocity,timeout); //855
		topRightMotor.configMotionAcceleration(accel,timeout); //855
		topRightMotor.setSelectedSensorPosition(0,pidIdx,timeout);
		
		bottomRightMotor.set(ControlMode.Follower, topRightMotor.getDeviceID());
		//Current Limit on Drive Motors to Wall Proof Robot
		
	}
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
    
    public void driveForward(double vBusLeft, double vBusRight) {
    	topLeftMotor.set(ControlMode.PercentOutput,0.3*-vBusLeft);
    	topRightMotor.set(ControlMode.PercentOutput,0.3*vBusRight);
    }
    
    public void setSpeed(double speed) {
    	topLeftMotor.set(ControlMode.Velocity,speed);
    	topRightMotor.set(ControlMode.Velocity,speed);
    }
    
    public void stop() {
    	topLeftMotor.set(ControlMode.PercentOutput,0);
    	topRightMotor.set(ControlMode.PercentOutput,0);
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
    	return topLeftMotor.getSensorCollection().getQuadraturePosition();
    }
    
    public double getRightEncoder() {
    	return topRightMotor.getSensorCollection().getQuadraturePosition();
    }
    
    public double getLeftEncoderSpeed() {
    	return topLeftMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public double getRightEncoderSpeed() {
    	return topRightMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public void zeroEncoders() {
    	topLeftMotor.setSelectedSensorPosition(0,pidIdx,timeout);
    	topRightMotor.setSelectedSensorPosition(0,pidIdx,timeout);
    }
    
    public void driveMotionMagic(double targetEncPosition) {
    	topLeftMotor.set(ControlMode.MotionMagic,targetEncPosition);
    	topRightMotor.set(ControlMode.MotionMagic,targetEncPosition);
    }
    
    public void turn(double angle) {
		//double d = ((RobotMap.wheelBase * Math.PI) * (angle / 360.0) / RobotMap.wheelDiameter / Math.PI * 360*4)/40.8;
		double d = ((RobotMap.wheelBase * Math.PI) * (angle / 360.0)) / RobotMap.distancePerRotation;
		topLeftMotor.set(ControlMode.MotionMagic,d);
    	topRightMotor.set(ControlMode.MotionMagic,d);
	}
    
    public void turnTo(boolean clockwise) {
		//If it is negative, turn clockwise
    	if (clockwise) {
    		Robot.driveTrain.driveForward(0.5, -0.5);
    	}
    	//If it is positive, turn counterclockwise
    	else {
    		Robot.driveTrain.driveForward(-0.5, 0.5);
    	}
    }

	public double getPosition() {
		// TODO Auto-generated method stub
		return topLeftMotor.getSensorCollection().getQuadraturePosition();
	}
	
	public double getLeftCurrent() {
		return topLeftMotor.getOutputCurrent();
	}
	
	public double getRightCurrent() {
		return topRightMotor.getOutputCurrent();
	}
}
    
    


