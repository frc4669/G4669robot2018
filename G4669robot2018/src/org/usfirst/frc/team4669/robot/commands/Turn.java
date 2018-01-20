package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {
	
	private double degree;
	private double initialAngle;
	

    public Turn(double degree) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	initialAngle = Robot.driveTrain.getGyroAngle();
    	this.degree = degree;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.driveTrain.calibrateGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//If degrees is negative, turn clockwise
    	if (degree < 0) {
    		Robot.driveTrain.driveForward(0.5, -0.5);
    	}
    	//If it is positive, turn counterclockwise
    	else if (degree > 0) {
    		Robot.driveTrain.driveForward(-0.5, 0.5);
    	}
    	else end();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Stop turning once robot turns past specified angle
        if (degree < 0 && Robot.driveTrain.getGyroAngle() <= degree + initialAngle) {
    		return true;
    	}
    	else if (degree > 0 && Robot.driveTrain.getGyroAngle() >= degree + initialAngle) {
    		return true;
    	}
    	else {
    		return false;
    	}
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
    
    public void setDegree(double degree) {
    	this.degree = degree;
    }
}
