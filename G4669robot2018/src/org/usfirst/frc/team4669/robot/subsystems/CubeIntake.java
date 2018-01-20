package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.CubeIntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CubeIntake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private WPI_TalonSRX leftIntakeMotor;
	private WPI_TalonSRX rightIntakeMotor;
	
	int timeout = RobotMap.timeout;
	int slotIdx = RobotMap.timeout;
	int pidIdx = RobotMap.timeout;
	
	public CubeIntake(){
		super();
		leftIntakeMotor =  new WPI_TalonSRX(99);
		rightIntakeMotor =  new WPI_TalonSRX(99);
		
		leftIntakeMotor.configNominalOutputForward(0, timeout);
		leftIntakeMotor.configNominalOutputReverse(0, timeout);
		
		rightIntakeMotor.configNominalOutputForward(1, timeout);
		rightIntakeMotor.configNominalOutputReverse(1, timeout);
		
		leftIntakeMotor.configPeakOutputForward(1, timeout);
		leftIntakeMotor.configPeakOutputReverse(-1, timeout);
		
		rightIntakeMotor.configPeakOutputForward(-1, timeout);
		rightIntakeMotor.configPeakOutputReverse(-1, timeout);
		
		//Configuring PID Values
		leftIntakeMotor.selectProfileSlot(slotIdx,pidIdx);
		leftIntakeMotor.config_kF(slotIdx,0.03,timeout);
		leftIntakeMotor.config_kP(slotIdx,0.17,timeout);
		leftIntakeMotor.config_kI(slotIdx,0,timeout); 
		leftIntakeMotor.config_kD(slotIdx,20,timeout);
		
		rightIntakeMotor.selectProfileSlot(slotIdx,pidIdx);
		rightIntakeMotor.config_kF(slotIdx,0.03,timeout);
		rightIntakeMotor.config_kP(slotIdx,0.17,timeout);
		rightIntakeMotor.config_kI(slotIdx,0,timeout); 
		rightIntakeMotor.config_kD(slotIdx,20,timeout);
		
		leftIntakeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,pidIdx,timeout);
		rightIntakeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,pidIdx,timeout);
		
		leftIntakeMotor.setSelectedSensorPosition(0,pidIdx,timeout);
		rightIntakeMotor.setSelectedSensorPosition(0,pidIdx,timeout);
		
		leftIntakeMotor.setInverted(true);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CubeIntakeCommand());
    }
    
    public void intake(){
    	double speed = SmartDashboard.getNumber("Cube Intake Speed", 0);
		leftIntakeMotor.set(ControlMode.Velocity,-speed);
		rightIntakeMotor.set(ControlMode.Velocity,speed);
    }
    
    public void releaseCube(){
    	double speed = SmartDashboard.getNumber("Cube Release Speed", 0);
		leftIntakeMotor.set(ControlMode.Velocity,speed);
		rightIntakeMotor.set(ControlMode.Velocity,-speed);
    }
    
    public void stop(){
		leftIntakeMotor.set(ControlMode.Velocity,0);
		rightIntakeMotor.set(ControlMode.Velocity,0);
    }
}

