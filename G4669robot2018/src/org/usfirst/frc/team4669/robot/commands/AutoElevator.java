package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoElevator extends Command {
	private int height;

	public AutoElevator(int height) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		this.height = height;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.setHeight(height);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.elevator.getEncoderPos()-height)<200;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
