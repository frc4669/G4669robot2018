package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.HookClimb;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmRaiser extends Subsystem {
	
	WPI_TalonSRX armRaiseMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ArmRaiser(){
		super();
		armRaiseMotor = new WPI_TalonSRX(RobotMap.armRaiser);
	}

	public void lowerArm(){
		armRaiseMotor.set(ControlMode.PercentOutput,0.5);
	}
	
	public void raiseArm(){
		armRaiseMotor.set(ControlMode.PercentOutput,-0.5);
	}
	
	public void stop(){
		armRaiseMotor.set(ControlMode.PercentOutput, 0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new HookClimb());
    }
}

