package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoRelease extends TimedCommand {

    public AutoRelease(double timeout) {
        // Use requires() here to declare subsystem dependencies
    	// eg. requires(chassis);
    	super(timeout);
        requires(Robot.cubeIntake);
    }
    
    public AutoRelease() {
        // Use requires() here to declare subsystem dependencies
    	// eg. requires(chassis);
    	super(2);
        requires(Robot.cubeIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cubeIntake.releaseCube();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
