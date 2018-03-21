package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Ultrasonic;


/**
 *
 */
public class CubeIntake extends Subsystem {
	
	private WPI_TalonSRX leftIntakeMotor;
	private WPI_TalonSRX rightIntakeMotor;
	private Servo ropeServo;
	private AnalogInput distSensorLeft;
	private AnalogInput distSensorRight;
	
	private int timeout = RobotMap.timeout;
	private int slotIdx = RobotMap.slotIdx;
	private int pidIdx = RobotMap.pidIdx;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

	public CubeIntake(){
		super();
		
		distSensorLeft = new AnalogInput(0);
		distSensorRight = new AnalogInput(1);
		ropeServo = new Servo(0);
		
		leftIntakeMotor =  new WPI_TalonSRX(RobotMap.leftIntake);
		rightIntakeMotor =  new WPI_TalonSRX(RobotMap.rightIntake);
		
		leftIntakeMotor.configNominalOutputForward(0, timeout);
		leftIntakeMotor.configNominalOutputReverse(0, timeout);
		
		rightIntakeMotor.configNominalOutputForward(0, timeout);
		rightIntakeMotor.configNominalOutputReverse(0, timeout);
		
		leftIntakeMotor.configPeakOutputForward(1, timeout);
		leftIntakeMotor.configPeakOutputReverse(-1, timeout);
		
		rightIntakeMotor.configPeakOutputForward(1, timeout);
		rightIntakeMotor.configPeakOutputReverse(-1, timeout);
		
		//Configuring PID Values
		leftIntakeMotor.selectProfileSlot(slotIdx,pidIdx);
		leftIntakeMotor.config_kF(slotIdx,0.097,timeout); //0.03
		leftIntakeMotor.config_kP(slotIdx,0.22,timeout); //0.17
		leftIntakeMotor.config_kI(slotIdx,0.0011,timeout); //0
		leftIntakeMotor.config_kD(slotIdx,30,timeout); //20
		
		rightIntakeMotor.selectProfileSlot(slotIdx,pidIdx);
		rightIntakeMotor.config_kF(slotIdx,0.097,timeout);
		rightIntakeMotor.config_kP(slotIdx,0.22,timeout);
		rightIntakeMotor.config_kI(slotIdx,0.0011,timeout); 
		rightIntakeMotor.config_kD(slotIdx,22,timeout);
		
		leftIntakeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,pidIdx,timeout);
		rightIntakeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,pidIdx,timeout);
		
		leftIntakeMotor.setSelectedSensorPosition(0,pidIdx,timeout);
		rightIntakeMotor.setSelectedSensorPosition(0,pidIdx,timeout);
		
//		leftIntakeMotor.configMotionCruiseVelocity(200, timeout);
//		leftIntakeMotor.configMotionAcceleration(50, timeout);
//		rightIntakeMotor.configMotionCruiseVelocity(200, timeout);
//		rightIntakeMotor.configMotionAcceleration(50, timeout);
		
		leftIntakeMotor.setSensorPhase(true);
		rightIntakeMotor.setSensorPhase(false);
		
		leftIntakeMotor.setInverted(true);
		rightIntakeMotor.setInverted(false);
		
		//Setting Current limits
		
		leftIntakeMotor.configContinuousCurrentLimit(12, timeout);
		leftIntakeMotor.configPeakCurrentLimit(15, timeout);
		leftIntakeMotor.configPeakCurrentDuration(100, timeout);
		leftIntakeMotor.enableCurrentLimit(true);
		
		rightIntakeMotor.configContinuousCurrentLimit(12, timeout);
		rightIntakeMotor.configPeakCurrentLimit(15, timeout);
		rightIntakeMotor.configPeakCurrentDuration(100, timeout);
		rightIntakeMotor.enableCurrentLimit(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	//setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new Intake());
    }
    
    public void intake(){
//    	double speed = SmartDashboard.getNumber("CubeIntakeVel", 2500);
    	if(!Robot.cubeIntake.hasCube()){
			leftIntakeMotor.set(ControlMode.PercentOutput,-0.4);
			rightIntakeMotor.set(ControlMode.PercentOutput,-0.4);
    	}
//    	leftIntakeMotor.set(ControlMode.Velocity,speed);
//    	rightIntakeMotor.set(ControlMode.Velocity,speed);
    }
    
    public void releaseCube(){
		leftIntakeMotor.set(ControlMode.PercentOutput,0.3);
		rightIntakeMotor.set(ControlMode.PercentOutput,0.3);
    }
    
    public void turnCube(){
		leftIntakeMotor.set(ControlMode.PercentOutput,-0.3);
		rightIntakeMotor.set(ControlMode.PercentOutput,0.3);
    }
    
    public void set(double output){
    	leftIntakeMotor.set(ControlMode.PercentOutput,output);
    	rightIntakeMotor.set(ControlMode.PercentOutput,output);
    }
    
    public void stopIntake(){
		leftIntakeMotor.set(0);
		rightIntakeMotor.set(0);
    }
    
    public void releaseArms() {
    	ropeServo.set(0);//0 means rotate full counter-clockwise, 1 means full clockwise
    }
    
    public void stopServo() {
    	ropeServo.set(0.5);//0.5 means stop
    }
    
    //Getting  encoder velocities and position, distance sensor range
    public double getLeftEncoder() {
    	return leftIntakeMotor.getSensorCollection().getQuadraturePosition();
    }
    
    public double getRightEncoder() {
    	return rightIntakeMotor.getSensorCollection().getQuadraturePosition();
    }
    
    public double getLeftEncoderSpeed() {
    	return leftIntakeMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public double getRightEncoderSpeed() {
    	return rightIntakeMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public double getLeftDistance() {
    	return distSensorLeft.getVoltage(); // reads the range on the distance sensor in voltage, 0 is farther
    }
    public double getRightDistance() {
    	return distSensorRight.getVoltage(); // reads the range on the distance sensor in voltage, 0 is farther
    }
    public boolean hasCube(){
//    	return getLeftDistance() > 1 && getRightDistance() > 1;
    	return getLeftDistance() > 1;
    }
}

