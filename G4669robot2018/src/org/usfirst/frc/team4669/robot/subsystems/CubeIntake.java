package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Ultrasonic;


/**
 *
 */
public class CubeIntake extends Subsystem {
	
	private WPI_TalonSRX leftIntakeMotor;
	private WPI_TalonSRX rightIntakeMotor;
	private Ultrasonic ultra = new Ultrasonic(1,1);
	
	private int timeout = RobotMap.timeout;
	private int slotIdx = RobotMap.slotIdx;
	private int pidIdx = RobotMap.pidIdx;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

	public CubeIntake(){
		super();
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
		leftIntakeMotor.config_kF(slotIdx,0.03,timeout); //0.03
		leftIntakeMotor.config_kP(slotIdx,0.17,timeout); //0.17
		leftIntakeMotor.config_kI(slotIdx,0,timeout); //0
		leftIntakeMotor.config_kD(slotIdx,5,timeout); //20
		
		rightIntakeMotor.selectProfileSlot(slotIdx,pidIdx);
		rightIntakeMotor.config_kF(slotIdx,0.03,timeout);
		rightIntakeMotor.config_kP(slotIdx,0.17,timeout);
		rightIntakeMotor.config_kI(slotIdx,0,timeout); 
		rightIntakeMotor.config_kD(slotIdx,5,timeout);
		
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
		
		leftIntakeMotor.setInverted(false);
		rightIntakeMotor.setInverted(true);
		
		//Setting Current limits
		
		leftIntakeMotor.configContinuousCurrentLimit(12, timeout);
		leftIntakeMotor.configPeakCurrentLimit(15, timeout);
		leftIntakeMotor.configPeakCurrentDuration(100, timeout);
		leftIntakeMotor.enableCurrentLimit(true);
		
		rightIntakeMotor.configContinuousCurrentLimit(12, timeout);
		rightIntakeMotor.configPeakCurrentLimit(15, timeout);
		rightIntakeMotor.configPeakCurrentDuration(100, timeout);
		rightIntakeMotor.enableCurrentLimit(true);
		
		ultra.setAutomaticMode(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	//setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new Intake());
    }
    
    public void intake(){
    	double speed = SmartDashboard.getNumber("CubeIntakeVel", 6000);
		leftIntakeMotor.set(ControlMode.PercentOutput,0.5);
		rightIntakeMotor.set(ControlMode.PercentOutput,0.5);
//    	leftIntakeMotor.set(ControlMode.Velocity,speed);
//    	rightIntakeMotor.set(ControlMode.Velocity,-speed);
    }
    
    public void releaseCube(){
    	double speed = SmartDashboard.getNumber("CubeReleaseVel", 6000);
		leftIntakeMotor.set(ControlMode.PercentOutput,-0.2);
		rightIntakeMotor.set(ControlMode.PercentOutput,-0.2);
//    	leftIntakeMotor.set(ControlMode.Velocity,-speed);
//    	rightIntakeMotor.set(ControlMode.Velocity,speed);
    }
    
    public void stop(){
		leftIntakeMotor.set(0);
		rightIntakeMotor.set(0);
    }
    
    //Getting  encoder velocities and position
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
    
    public double getUltrasonicInches() {
    	return ultra.getRangeInches(); // reads the range on the ultrasonic sensor
    }
}

