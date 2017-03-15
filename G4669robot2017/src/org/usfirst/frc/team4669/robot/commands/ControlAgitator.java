package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlAgitator extends Command {

    public ControlAgitator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.fuelAgitator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.f310.getDPadPOV() > -1) {
    		Robot.fuelAgitator.run(0.25);
    	}
    	else {
    		Robot.fuelAgitator.run(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.fuelAgitator.run(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
