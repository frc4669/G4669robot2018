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
	
	boolean motionMagicRunning = false;
	boolean turnRunning = false;
	boolean clockwise = false;
	double turnAngle;
	double initialAngle;
	double currentAngle;
	
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
    	
    	
    	if (Robot.driveTrain.getGyroAngle()>=0&&Robot.driveTrain.getGyroAngle()<=360) currentAngle = Robot.driveTrain.getGyroAngle();
    	if (Robot.driveTrain.getGyroAngle() > 360) currentAngle = Robot.driveTrain.getGyroAngle()%360;
    	if (Robot.driveTrain.getGyroAngle() < 0) currentAngle = Robot.driveTrain.getGyroAngle()+360;
    	//Red Button to stop driving
    	if(Robot.f310.getRedButton()){
    		turnRunning = false;
    		motionMagicRunning = false;
    		Robot.driveTrain.stop();
    	}
    	else{
	    	//Using the DPad for Turning
    		if (Robot.f310.getDPadPOV()!=-1 && !turnRunning) {
	    		turnAngle = Robot.f310.getDPadPOV();
	    		initialAngle = currentAngle;
	    		if (((turnAngle-initialAngle)>=180&&turnAngle>initialAngle)||  //Shortest path to turn counterclockwise
	    			((turnAngle-initialAngle)>=-180&&turnAngle<initialAngle)){
	    			clockwise = true;
	    		}
	    		turnRunning = true;
	    	}
	    	else if (turnRunning &&
	    			((Math.abs(turnAngle%360-currentAngle)<RobotMap.angleTolerance)))
	    	{
	    		turnRunning = false;
	    		Robot.driveTrain.stop();
	    	}
	    	else if (turnRunning) {
	    		Robot.driveTrain.turnTo(clockwise);
	    	}
    		
    		//Motion Magic Stuff
    		
    		if (Robot.f310.getBlueButton() && !motionMagicRunning) {
        		Robot.driveTrain.zeroEncoders();
        		Robot.driveTrain.driveMotionMagic(-50/RobotMap.encoderCountConstant);
        		motionMagicRunning = true;
    		}
    		else if (motionMagicRunning 
    				&& Math.abs(-50/RobotMap.encoderCountConstant - Robot.driveTrain.getPosition()) < 30) {
				motionMagicRunning = false;
    		}
    		
	    	//Joystick driving
			else if (!motionMagicRunning && !turnRunning){
		    	left = Robot.f310.getLeftY() + Robot.f310.getRightX();
		    	right = Robot.f310.getLeftY() - Robot.f310.getRightX();
		    	if (!Robot.elevator.getForwardLimit()){
		    		if(left>0.3){
		    			left = 0.3;
		    		}
		    		if (right>0.3){
		    			right = 0.3;
		    		}
		    		if(left<-0.3){
		    			left = -0.3;
		    		}
		    		if (right<-0.3){
		    			right = -0.3;
		    		}
		    	}
//	    		Robot.driveTrain.setSpeed(left*2400, right*2400);
		    	Robot.driveTrain.driveForward(left, right);
	    	}
    	}
    	SmartDashboard.putNumber("Current Angle", currentAngle);
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
