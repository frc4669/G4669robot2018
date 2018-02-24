package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.ClimberControl;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	
	private WPI_TalonSRX centerClimber;
	private WPI_TalonSRX rightClimber;
	private WPI_TalonSRX leftClimber;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Climber() {
		super();
		centerClimber = new WPI_TalonSRX(RobotMap.centerClimber);
		rightClimber = new WPI_TalonSRX(RobotMap.rightClimber);
		leftClimber = new WPI_TalonSRX(RobotMap.leftClimber);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClimberControl());
    }
    
    public void climb() {
    	centerClimber.set(0.8);
    	rightClimber.set(0.8);
    	leftClimber.set(0.8);
    }
    
    public void stop() {
    	centerClimber.set(0);
    	rightClimber.set(0);
    	leftClimber.set(0);
    }
}

