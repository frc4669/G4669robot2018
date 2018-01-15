package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.TankDrive;

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
	
	private int timeout = 1;
	private int slotIdx = 0;
	private int pidIdx = 0;
	
//	private RobotDrive driveTrain;
	
	private Gyro analogGyro;
	
	public DriveTrain() {
		super();
		analogGyro = new ADXRS450_Gyro();
		
		topLeftMotor = new WPI_TalonSRX(RobotMap.driveTrainTopLeft);
		bottomLeftMotor = new WPI_TalonSRX(RobotMap.driveTrainBottomLeft);
		topRightMotor = new WPI_TalonSRX(RobotMap.driveTrainTopRight);
		bottomRightMotor = new WPI_TalonSRX(RobotMap.driveTrainBottomRight);
		
//		driveTrain = new RobotDrive(topLeftMotor, bottomLeftMotor, topRightMotor, bottomRightMotor);
		
//		setupLeftMotor(topLeftMotor);
////		setupLeftMotor(bottomLeftMotor);
//		setupRightMotor(topRightMotor);
////		setupRightMotor(bottomRightMotor);
		
		int velocity = 200;
		int accel = 50;
		
		topLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,pidIdx,timeout);
		topLeftMotor.setInverted(false);
		//topLeftMotor.configEncoderCodesPerRev(1440); // if using
		// FeedbackDevice.QuadEncoder
		// _talon.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
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
		//Current Limit on Drive Motors to Wall Proof Robot
		topLeftMotor.configContinuousCurrentLimit(15,timeout);
		topLeftMotor.enableCurrentLimit(true);
		/* set acceleration and vcruise velocity - see documentation */
		topLeftMotor.configMotionCruiseVelocity(velocity,timeout);
		topLeftMotor.configMotionAcceleration(accel,timeout);
		topLeftMotor.setSelectedSensorPosition(0,pidIdx,timeout);

		bottomLeftMotor.set(ControlMode.Follower, topLeftMotor.getDeviceID());
		//Current Limit on Drive Motors to Wall Proof Robot
		bottomLeftMotor.configContinuousCurrentLimit(15,timeout);
		bottomLeftMotor.enableCurrentLimit(true);
		
		topRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,pidIdx,timeout);
		topRightMotor.setInverted(false);
		//topRightMotor.configEncoderCodesPerRev(1440); // if using
		// FeedbackDevice.QuadEncoder
		// _talon.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
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
		//Current Limit on Drive Motors to Wall Proof Robot
		topRightMotor.configContinuousCurrentLimit(15,timeout);
		topRightMotor.enableCurrentLimit(true);
		/* set acceleration and vcruise velocity - see documentation */
		topRightMotor.configMotionCruiseVelocity(velocity,timeout); //855
		topRightMotor.configMotionAcceleration(accel,timeout); //855
		topRightMotor.setSelectedSensorPosition(0,pidIdx,timeout);
		
		bottomRightMotor.set(ControlMode.Follower, topRightMotor.getDeviceID());
		//Current Limit on Drive Motors to Wall Proof Robot
		bottomRightMotor.configContinuousCurrentLimit(15,timeout);
		bottomRightMotor.enableCurrentLimit(true);
		
	}
	
//	public void setPID(
//			double pLeft, 
//			double iLeft, 
//			double dLeft, 
//			double pRight, 
//			double iRight, 
//			double dRight,
//			double fLeft,
//			double fRight) {
//		topLeftMotor.setP(pLeft);
////		bottomLeftMotor.setP(pLeft);
//		topRightMotor.setP(pRight);
////		bottomRightMotor.setP(pRight);
//		topLeftMotor.setI(iLeft);
////		bottomLeftMotor.setI(iLeft);
//		topRightMotor.setI(iRight);
////		bottomRightMotor.setI(iRight);
//		topLeftMotor.setD(dLeft);
////		bottomLeftMotor.setD(dLeft);
//		topRightMotor.setD(dRight);
////		bottomRightMotor.setD(dRight);
//		topLeftMotor.setF(fLeft);
////		bottomLeftMotor.setF(fLeft);
//		topRightMotor.setF(fRight);
////		bottomRightMotor.setF(fRight);
//	}
	
//	public void setupLeftMotor(WPI_TalonSRX talon) {
////		talon.enable();
//		talon.changeControlMode(TalonControlMode.PercentVbus);
////		talon.enableBrakeMode(false);
//		talon.reverseOutput(!RobotMap.reverseOutputTrain);
//		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		talon.configNominalOutputVoltage(+0.0f, -0.0f);
//		talon.configPeakOutputVoltage(+12.0f, -12.0f);
////		talon.setProfile(0);
////		talon.setF(OI.TALON_F_LEFT.get());
////		talon.setP(OI.TALON_P_LEFT.get());
////		talon.setI(OI.TALON_I_LEFT.get());
////		talon.setD(OI.TALON_D_LEFT.get());
////		talon.setMotionMagicCruiseVelocity(937);
////		talon.setMotionMagicAcceleration(937);
//	}
//	
//	public void setupRightMotor(WPI_TalonSRX talon) {
////		talon.enable();
//		talon.changeControlMode(TalonControlMode.PercentVbus);
////		talon.enableBrakeMode(false);
//		talon.reverseOutput(RobotMap.reverseOutputTrain);
//		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		talon.configNominalOutputVoltage(+0.0f, -0.0f);
//		talon.configPeakOutputVoltage(+12.0f, -12.0f);
////		talon.setProfile(0);
////		talon.setF(OI.TALON_F_RIGHT.get());
////		talon.setP(OI.TALON_P_RIGHT.get());
////		talon.setI(OI.TALON_I_RIGHT.get());
////		talon.setD(OI.TALON_D_RIGHT.get());
////		talon.setMotionMagicCruiseVelocity(937);
////		talon.setMotionMagicAcceleration(937);
//	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TankDrive());
    }
    
    public void driveForward(double vBusLeft, double vBusRight) {
//    	driveTrain.tankDrive(speedLeft, speedRight, false);
    	topLeftMotor.set(ControlMode.PercentOutput,vBusLeft);
    	topRightMotor.set(ControlMode.PercentOutput,-vBusRight);
    }
    
    public void drive(double outputMag, double outputCurv) {
//    	driveTrain.drive(outputMag, outputCurv);
    }
    
    public void setDrive(double speed, double turnrate) {
//    	driveTrain.drive(speed, turnrate);
    }
    
    public void setSpeed(double speed) {
    	topLeftMotor.set(ControlMode.Velocity,speed);
    	topRightMotor.set(ControlMode.Velocity,speed);
    }
    
    public void stop() {
//    	driveTrain.tankDrive(0, 0);
    	topLeftMotor.set(0);
    	topRightMotor.set(0);
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
    
    public double getLeftEnconderSpeed() {
    	return topLeftMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public double getRightEnconderSpeed() {
    	return topRightMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public void zeroEncoders() {
    	topLeftMotor.setSelectedSensorPosition(0,pidIdx,timeout);
    	topRightMotor.setSelectedSensorPosition(0,pidIdx,timeout);
    }
    
//    public void changeControlMode(ControlMode mode) {
//    	topLeftMotor.set(mode);
//    	bottomLeftMotor.changeControlMode(mode);
//    	topRightMotor.set(mode);
//    	bottomRightMotor.changeControlMode(mode);
//    }
    
    public void driveMotionMagic(double targetEncPosition) {
//    	changeControlMode(TalonControlMode.MotionMagic);
    	topLeftMotor.set(ControlMode.MotionMagic,targetEncPosition);
//    	bottomLeftMotor.set(-targetEncPosition);
    	topRightMotor.set(ControlMode.MotionMagic,targetEncPosition);
//    	bottomRightMotor.set(targetEncPosition);
    }
    
    public void turn(double angle) {
		//double d = ((RobotMap.wheelBase * Math.PI) * (angle / 360.0) / RobotMap.wheelDiameter / Math.PI * 360*4)/40.8;
		double d = ((RobotMap.wheelBase * Math.PI) * (angle / 360.0)) / RobotMap.distancePerRotation;
		topLeftMotor.set(ControlMode.MotionMagic,d);
    	topRightMotor.set(ControlMode.MotionMagic,d);
	}

	public double getPosition() {
		// TODO Auto-generated method stub
		return topLeftMotor.getSensorCollection().getQuadraturePosition();
	}
	
}
    
    


