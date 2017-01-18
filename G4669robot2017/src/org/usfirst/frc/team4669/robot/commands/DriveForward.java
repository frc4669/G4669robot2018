package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

    public DriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveForward(0.25, 0.25);
    	isFinished();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.driveTrain.getRightEncoder() >= 16000) return true;
        	return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
