package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.ElevatorCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	private WPI_TalonSRX elevatorMotor;
	
	private int timeout = RobotMap.timeout;
	private int slotIdx = RobotMap.slotIdx;
	private int pidIdx = RobotMap.pidIdx;


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Elevator(){
		super();
		elevatorMotor = new WPI_TalonSRX(RobotMap.elevator);
		
		elevatorMotor.configNominalOutputForward(0, timeout);
		elevatorMotor.configNominalOutputReverse(0, timeout);
		elevatorMotor.configPeakOutputForward(1, timeout);
		elevatorMotor.configPeakOutputReverse(-1, timeout);
		
		//Configuring PID Values
		elevatorMotor.selectProfileSlot(slotIdx,pidIdx);
		elevatorMotor.config_kF(slotIdx,0.03,timeout);
		elevatorMotor.config_kP(slotIdx,0.17,timeout);
		elevatorMotor.config_kI(slotIdx,0,timeout);
		elevatorMotor.config_kD(slotIdx,5,timeout);
		
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,pidIdx,timeout);
		elevatorMotor.setSelectedSensorPosition(0,pidIdx,timeout);
		elevatorMotor.setSensorPhase(false);
		elevatorMotor.setInverted(false);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorCommand());
    }
    
    public void changeHeight(double percent)  {
    	elevatorMotor.set(ControlMode.PercentOutput, percent);
    }
    
    public void groundHeight()  {
    	elevatorMotor.set(ControlMode.Position, 0);
    }
    
    public void maxHeight()  {
    	elevatorMotor.set(ControlMode.Position, 0);
    }
    
    public void switchHeight()  {
    	elevatorMotor.set(ControlMode.Position, 0);
    }
    
    public void midHeight()  {
    	elevatorMotor.set(ControlMode.Position, 0);
    }
    
    public void stop()  {
    	elevatorMotor.set(0);
    }
}

