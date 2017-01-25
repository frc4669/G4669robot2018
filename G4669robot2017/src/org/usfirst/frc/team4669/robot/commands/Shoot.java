package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class Shoot extends Command {
	
	private int _loops = 0;

    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	
    	
    	requires(Robot.fuelLauncher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 if(++_loops >= 10) {

         	_loops = 0;
    	 }
    	 Robot.fuelLauncher.shoot();

         	// System.out.println(Robot.oi._sb.toString());
    	
    	// SmartDashboard.putNumber("Motor Speed" , TalonControlMode.Voltage);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.oi.getRawButton(1)) return true;
    	else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

