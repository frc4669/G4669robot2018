package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.TankDrive;
import org.usfirst.frc.team4669.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
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
	public WPI_TalonSRX topLeftMotor;
	private WPI_TalonSRX bottomLeftMotor;
	public WPI_TalonSRX topRightMotor;
	private WPI_TalonSRX bottomRightMotor;
	
	public PIDController gyroPID;
	private PIDOutputWrapper turnOutput;
	
	int velocity = 2300; //About 200 RPM, vel units are in sensor units per 100ms
	int accel = 2300;
	
	public Gyro analogGyro;
	
	public DriveTrain() {
		super();
		analogGyro = new ADXRS450_Gyro();
		
		topLeftMotor = new WPI_TalonSRX(RobotMap.driveTrainTopLeft);
		bottomLeftMotor = new WPI_TalonSRX(RobotMap.driveTrainBottomLeft);
		topRightMotor = new WPI_TalonSRX(RobotMap.driveTrainTopRight);
		bottomRightMotor = new WPI_TalonSRX(RobotMap.driveTrainBottomRight);
		
		gyroPID = new PIDController(RobotMap.kPGyro,RobotMap.kIGyro,RobotMap.kDGyro,(PIDSource) analogGyro,turnOutput);		
		gyroPID.setInputRange(-180, 180);
		
		gyroPID.setContinuous(true);
		
		gyroPID.setOutputRange(-0.5, 0.5);
		
		gyroPID.setAbsoluteTolerance(2);
		
		topRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,RobotMap.pidIdx,RobotMap.timeout);
		topLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,RobotMap.pidIdx,RobotMap.timeout);
		
		topRightMotor.setInverted(true);
		topLeftMotor.setInverted(false);
		bottomRightMotor.setInverted(true);
		bottomLeftMotor.setInverted(false);
		
		topRightMotor.setSensorPhase(true);
		topLeftMotor.setSensorPhase(true);

		/* set the peak and nominal outputs, 1 means full */
		topRightMotor.configNominalOutputForward(0, RobotMap.timeout);
		topRightMotor.configNominalOutputReverse(0, RobotMap.timeout);
		topRightMotor.configPeakOutputForward(1, RobotMap.timeout);
		topRightMotor.configPeakOutputReverse(-1, RobotMap.timeout);
		
		topLeftMotor.configNominalOutputForward(0, RobotMap.timeout);
		topLeftMotor.configNominalOutputReverse(0, RobotMap.timeout);
		topLeftMotor.configPeakOutputForward(1, RobotMap.timeout);
		topLeftMotor.configPeakOutputReverse(-1, RobotMap.timeout);
		
		/* set closed loop gains in slot0 - see documentation */
		topRightMotor.selectProfileSlot(RobotMap.slotIdx, RobotMap.pidIdx);
		topRightMotor.config_kF(RobotMap.slotIdx,RobotMap.rightkF,RobotMap.timeout);
		topRightMotor.config_kP(RobotMap.slotIdx,RobotMap.rightkP,RobotMap.timeout);
		topRightMotor.config_kI(RobotMap.slotIdx,RobotMap.rightkI,RobotMap.timeout);
		topRightMotor.config_kD(RobotMap.slotIdx,RobotMap.rightkD,RobotMap.timeout);
		topRightMotor.config_IntegralZone(RobotMap.slotIdx,RobotMap.rightkIZone,RobotMap.timeout);
		
		topLeftMotor.selectProfileSlot(RobotMap.slotIdx,RobotMap.pidIdx);
		topLeftMotor.config_kF(RobotMap.slotIdx,RobotMap.leftkF,RobotMap.timeout);
		topLeftMotor.config_kP(RobotMap.slotIdx,RobotMap.leftkP,RobotMap.timeout);
		topLeftMotor.config_kI(RobotMap.slotIdx,RobotMap.leftkI,RobotMap.timeout);
		topLeftMotor.config_kD(RobotMap.slotIdx,RobotMap.leftkD,RobotMap.timeout);
		topLeftMotor.config_IntegralZone(RobotMap.slotIdx,RobotMap.leftkIZone,RobotMap.timeout);
		
		/* set acceleration and vcruise velocity - see documentation */
		topRightMotor.configMotionCruiseVelocity(velocity,RobotMap.timeout); //855
		topRightMotor.configMotionAcceleration(accel,RobotMap.timeout); //855
		topRightMotor.setSelectedSensorPosition(0,RobotMap.pidIdx,RobotMap.timeout);
		
		topLeftMotor.configMotionCruiseVelocity(velocity,RobotMap.timeout);
		topLeftMotor.configMotionAcceleration(accel,RobotMap.timeout);
		topLeftMotor.setSelectedSensorPosition(0,RobotMap.pidIdx,RobotMap.timeout);
		
		//Set Current limit 
		topRightMotor.configContinuousCurrentLimit(20, RobotMap.timeout);
		topRightMotor.configPeakCurrentLimit(25, RobotMap.timeout);
		topRightMotor.configPeakCurrentDuration(100, RobotMap.timeout);
		topRightMotor.enableCurrentLimit(true);
		
		topLeftMotor.configContinuousCurrentLimit(20, RobotMap.timeout);
		topLeftMotor.configPeakCurrentLimit(25, RobotMap.timeout);
		topLeftMotor.configPeakCurrentDuration(100, RobotMap.timeout);
		topLeftMotor.enableCurrentLimit(true);
		
		//Setting bottom motors to follow top
		bottomRightMotor.set(ControlMode.Follower, topRightMotor.getDeviceID());
		bottomLeftMotor.set(ControlMode.Follower, topLeftMotor.getDeviceID());
	}
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
    
    public void driveForward(double vBusLeft, double vBusRight) {
    	topLeftMotor.set(ControlMode.PercentOutput,vBusLeft);
    	topRightMotor.set(ControlMode.PercentOutput,vBusRight);
    }
    
    public void setSpeed(double leftSpeed,double rightSpeed) {
    	topLeftMotor.set(ControlMode.Velocity,leftSpeed);
    	topRightMotor.set(ControlMode.Velocity,rightSpeed);
    }
    
    public void stop() {
    	topLeftMotor.set(ControlMode.PercentOutput,0);
    	topRightMotor.set(ControlMode.PercentOutput,0);
    }
    
    public void calibrateGyro() {
    	analogGyro.calibrate();
    }
    
    public void resetGyro() {
    	analogGyro.reset();
    }
    
    public double getGyroAngle() {
    	return analogGyro.getAngle();
    }
    
    public double calculateTurningValue(double direction) {
    	double turningValue = kAngleSetpoint - analogGyro.getAngle() * kP;
    	turningValue = Math.copySign(turningValue, direction);
    	return turningValue;
    }
    
    public int getLeftEncoder() {
    	return topLeftMotor.getSensorCollection().getQuadraturePosition();
    }
    
    public int getRightEncoder() {
    	return topRightMotor.getSensorCollection().getQuadraturePosition();
    }
    
    public double getLeftEncoderSpeed() {
    	return topLeftMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public double getRightEncoderSpeed() {
    	return topRightMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public void zeroEncoders() {
    	topLeftMotor.setSelectedSensorPosition(0,RobotMap.pidIdx,RobotMap.timeout); 
    	topRightMotor.setSelectedSensorPosition(0,RobotMap.pidIdx,RobotMap.timeout);
    }
    
    public void driveMotionMagic(double targetEncPosition) {
    	setMotionVelAccel(this.velocity,this.accel);
    	topLeftMotor.set(ControlMode.MotionMagic,targetEncPosition);
    	topRightMotor.set(ControlMode.MotionMagic,targetEncPosition);
    }
    
    public void setMotionVelAccel(int velocity,int accel) {
		topLeftMotor.configMotionCruiseVelocity(velocity,RobotMap.timeout);
		topLeftMotor.configMotionAcceleration(accel,RobotMap.timeout);
		topRightMotor.configMotionCruiseVelocity(velocity,RobotMap.timeout);
		topRightMotor.configMotionAcceleration(accel,RobotMap.timeout);
    }
    
    public void turn(double angle) {
		//double d = ((RobotMap.wheelBase * Math.PI) * (angle / 360.0) / RobotMap.wheelDiameter / Math.PI * 360*4)/40.8;
//		double d = (((RobotMap.wheelBase * Math.PI) * (angle / 360.0)) / RobotMap.distancePerRotation)*4096;
    	setMotionVelAccel(1365,6825);
    	double d = -(((RobotMap.wheelBase * Math.PI) * (angle / 360.0)) / RobotMap.inchToEncoder);
		topLeftMotor.set(ControlMode.MotionMagic,-d);
    	topRightMotor.set(ControlMode.MotionMagic,d);
    	System.out.println(d);
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
		return topRightMotor.getSensorCollection().getQuadraturePosition();
	}
	
	public double getLeftCurrent() {
		return topLeftMotor.getOutputCurrent();
	}
	
	public double getRightCurrent() {
		return topRightMotor.getOutputCurrent();
	}
	
	public void enableTurnPID(){
		gyroPID.reset();
		gyroPID.enable();
		
	}
	
	public void disableTurnPID(){
		gyroPID.disable();
	}
	
	public void setTurnAngle(double angle){
		gyroPID.setSetpoint(angle);
	}
	
	public double getAngle(){
		return analogGyro.getAngle();
	}
	
	public double getAngleFixed(){
		double angle = analogGyro.getAngle();
		while(angle>180){
			angle-=180;
		}
		while(angle<-180){
			angle+=180;
		}
		return angle;
	}
	
	public boolean getTurnDone(){
	    return gyroPID.onTarget();
	}

	public double getTurnPIDError()
	{
		return gyroPID.getError();
	}
	
	public double getTurnOutput()
	{
		return turnOutput.getOutput();
	}
	
	public void setMode(ControlMode mode, double value){
		topLeftMotor.set(mode, value);
		topRightMotor.set(mode, value);
	}
	
}
    
    


