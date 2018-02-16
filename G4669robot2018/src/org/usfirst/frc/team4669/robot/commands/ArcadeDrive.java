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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//button2 stop
    	if (Robot.driveTrain.getGyroAngle()>=0&&Robot.driveTrain.getGyroAngle()<=360) currentAngle = Robot.driveTrain.getGyroAngle();
    	if (Robot.driveTrain.getGyroAngle() > 360) currentAngle = Robot.driveTrain.getGyroAngle()%360;
    	if (Robot.driveTrain.getGyroAngle() < 0) currentAngle = Robot.driveTrain.getGyroAngle()+360;
    	if(Robot.f310.getRedButton()){
    		turnRunning = false;
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
    		
//    		if (Robot.oi.getLeftRawButton(RobotMap.moveBackButton) && !motionMagicRunning) {
//        		Robot.driveTrain.zeroEncoders();
//        		Robot.driveTrain.driveMotionMagic(12/40.8);
//        		motionMagicRunning = true;
//    		}
//    		else if (motionMagicRunning 
//    				&& Math.abs(12/40.8 - Robot.driveTrain.getPosition()) < 0.1) {
//    				motionMagicRunning = false;
//    		}
//    		
//			else if (motionMagic180 
//					&& Math.abs((((RobotMap.wheelBase * Math.PI) * (180 / 360.0)) / RobotMap.distancePerRotation) - Robot.driveTrain.getPosition()) < 0.05) {
//					motionMagic180 = false;
//			}
	    	
	    	//Joystick driving
			else if (!motionMagicRunning && !turnRunning){
		    	left = Robot.f310.getLeftY() - Robot.f310.getRightX();
		    	right = Robot.f310.getLeftY() + Robot.f310.getRightX();
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
