package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveMotionMagic extends Command {

	private double distance;

	public DriveMotionMagic(double distance) {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.distance = distance;
        requires(Robot.driveTrain);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.driveTrain.zeroEncoders();
    	Robot.driveTrain.driveMotionMagic(distance/RobotMap.distancePerRotation);    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(distance/40.8 - Robot.driveTrain.getPosition()) < 0.1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
