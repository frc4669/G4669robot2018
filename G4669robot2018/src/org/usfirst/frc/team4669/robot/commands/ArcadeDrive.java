package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArcadeDrive extends Command {
//	DriveTrain driveTrain = Robot.driveTrain;
	
	double left; //left motor
	double right; //right motor
	double t_left;
	double t_right;
	
	boolean motionMagicRunning = false;
	boolean turnRunning = false;
	boolean clockwise = false;
	double turnAngle;
	
    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnRunning = false;
    	motionMagicRunning = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Red Button to stop driving
    	if(Robot.f310.getRedButton()){
    		turnRunning = false;
    		motionMagicRunning = false;
    		Robot.driveTrain.stop();
    	}
    	else{
	    	//Using the DPad for Turning
//    		if (Robot.f310.getDPadPOV()!=-1 && !turnRunning) {
//	    		turnAngle = Robot.f310.getDPadPOV();
//	    		initialAngle = currentAngle;
//	    		if (((turnAngle-initialAngle)>=180&&turnAngle>initialAngle)||  //Shortest path to turn counterclockwise
//	    			((turnAngle-initialAngle)>=-180&&turnAngle<initialAngle)){
//	    			clockwise = true;
//	    		}
//	    		turnRunning = true;
//	    	}
//	    	else if (turnRunning &&
//	    			((Math.abs(turnAngle%360-currentAngle)<RobotMap.angleTolerance)))
//	    	{
//	    		turnRunning = false;
//	    		Robot.driveTrain.stop();
//	    	}
//	    	else if (turnRunning) {
//	    		Robot.driveTrain.turnTo(clockwise);
//	    	}
    		
    		//Turns the Robot to D-Pad angle
    		if (Robot.f310.getDPadPOV()!=-1 && !turnRunning) {
    			turnAngle = Robot.f310.getDPadPOV();
    			Robot.driveTrain.enableTurnPID();
    			Robot.driveTrain.setTurnAngle(turnAngle);
    			turnRunning = true;
	    	}
	    	else if (turnRunning &&Robot.driveTrain.getTurnDone())
	    	{
	    		Robot.driveTrain.disableTurnPID();
	    		Robot.driveTrain.stop();
	    		turnRunning = false;
	    	}
	    	else if (turnRunning) {
	    		Robot.driveTrain.driveForward(Robot.driveTrain.getTurnOutput(), -Robot.driveTrain.getTurnOutput());
	    	}
    		
	    	//Joystick driving
			else if (!motionMagicRunning && !turnRunning){
//		    	left = Robot.f310.getLeftY() + Robot.f310.getRightX();
//		    	right = Robot.f310.getLeftY() - Robot.f310.getRightX();
		    	
	    		t_left = Robot.f310.getLeftY() + Robot.f310.getRightX();
	    		t_right = Robot.f310.getLeftY() - Robot.f310.getRightX();

	    		left = t_left + skim(t_right);
	    		right = t_right + skim(t_left);
		    		
		    	if (Robot.elevator.getEncoderPos()<-9500||Robot.f310.getRightShoulderButton()){
		    		Robot.driveTrain.driveForward(0.3*left, 0.3*right);
		    	} else{
		    		Robot.driveTrain.driveForward(0.6*left, 0.6*right);
		    	}
//	    		Robot.driveTrain.setSpeed(left*2400, right*2400);
		    	
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
    
    double skim(double v) {
		  // gain determines how much to skim off the top
		  if (v > 1.0)
		    return -((v - 1.0) * RobotMap.gain);
		  else if (v < -1.0)
		    return -((v + 1.0) * RobotMap.gain);
		  return 0;
		}
}
