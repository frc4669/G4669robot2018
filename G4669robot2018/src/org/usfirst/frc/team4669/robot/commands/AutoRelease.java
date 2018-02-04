package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoRelease extends Command {

    public AutoRelease() {
        // Use requires() here to declare subsystem dependencies
    	// eg. requires(chassis);
        requires(Robot.cubeIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cubeIntake.releaseCube();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.cubeIntake.getUltrasonicInches()>10.0;
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
