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
	double turn;
	double drive;
	
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
    	if(Robot.f310.getBackButton()){
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
				drive = Robot.f310.getLeftY();
				turn = Robot.f310.getRightX();
				
				if (Robot.elevator.getEncoderPos()<-22000||Robot.f310.getRightShoulderButton()||Robot.f310.getGreenButton()){
		    		drive = 0.3*Robot.f310.getLeftY();
					turn = 0.3*Robot.f310.getRightX();
		    	}
				else if (Robot.elevator.getEncoderPos()<RobotMap.elevatorExchange+300||Robot.cubeIntake.hasCube()){
	    			drive = 0.7*Robot.f310.getLeftY();
		    		turn = 0.3*Robot.f310.getRightX();
	    		}
		    	else{
		    		drive = 0.7*Robot.f310.getLeftY();
		    		turn = 0.5*Robot.f310.getRightX();
		    	}
		    	
		    	left = drive + turn;
		    	right = drive - turn;
		    	Robot.driveTrain.driveForward(left, right);
		    	
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
    
//    double skim(double v) {
//		  // gain determines how much to skim off the top
//		  if (v > 1.0)
//		    return -((v - 1.0) * RobotMap.gain);
//		  else if (v < -1.0)
//		    return -((v + 1.0) * RobotMap.gain);
//		  return 0;
//		}
}
