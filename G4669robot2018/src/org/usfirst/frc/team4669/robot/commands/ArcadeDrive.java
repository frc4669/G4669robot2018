package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {
	DriveTrain driveTrain = Robot.driveTrain;
	
	double left; //left motor
	double right; //right motor
	
	boolean turnRunning = false;
	double turnAngle;
	double initialAngle;
	double turnHeading;

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//button2 stop
    	if(Robot.f310.getRedButton()){
    		turnRunning = false;
    		driveTrain.stop();
    	}
    	else{
	    	//Using the DPad for Turning
	    	if (Robot.f310.getDPadPOV()!=-1 && !turnRunning) {
	    		turnAngle = Robot.f310.getDPadPOV();
	    		if(turnAngle>180){
	    			turnAngle -= 360;
	    		}
	    		initialAngle = driveTrain.getGyroAngle()%360;
	    		turnHeading = driveTrain.calculateTurningValue(turnAngle);
	    		turnRunning = true;
	    	}
	    	else if (turnRunning &&
	    			((Math.abs(turnHeading%360-driveTrain.getGyroAngle()%360)<2))){
	    		turnRunning = false;
	    		driveTrain.stop();
	    	}
	    	else if (turnRunning) {
	    		driveTrain.turnTo(turnAngle);
	    	}
	    	
	    	//Joystick driving
	    	else if (!turnRunning){
		    	left = Robot.f310.getLeftY() - Robot.f310.getLeftX();
		    	right = Robot.f310.getLeftY() + Robot.f310.getLeftX();
		    	driveTrain.driveForward(left, right);
	    	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
