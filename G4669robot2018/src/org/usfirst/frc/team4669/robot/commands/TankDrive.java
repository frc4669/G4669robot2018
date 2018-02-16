package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {
	
	int direction = -1;
	boolean motionMagicRunning = false;
	boolean motionMagic180 = false;
	boolean turnRunning = false;
	boolean clockwise = false;
	double turnAngle;
	double initialAngle;

    public TankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	//Robot.driveTrain.driveForward(-Robot.f310.getLeftY(), -Robot.f310.getRightY());
    	if (Robot.f310.getDPadPOV()!=-1&& !turnRunning) {
    		turnAngle = Robot.f310.getDPadPOV();
    		initialAngle = Robot.driveTrain.getGyroAngle();
    		turnRunning = true;
    	}
    	else if (turnRunning) {
    		Robot.driveTrain.turnTo(clockwise);
    	}
    	else if (turnRunning && (turnAngle < 0 && Robot.driveTrain.getGyroAngle() <= turnAngle + initialAngle) ||
    			(turnAngle > 0 && Robot.driveTrain.getGyroAngle() >= turnAngle + initialAngle)) {
    		turnRunning = false;
    	}
//    	if (Robot.oi.getLeftRawButton(RobotMap.moveBackButton) && !motionMagicRunning) {
//    		Robot.driveTrain.zeroEncoders();
//    		Robot.driveTrain.driveMotionMagic(12/40.8);
//    		motionMagicRunning = true;
//		}
//		else if (motionMagicRunning 
//				&& Math.abs(12/40.8 - Robot.driveTrain.getPosition()) < 0.1) {
//				motionMagicRunning = false;
//		}
//		else if (Robot.oi.getLeftRawButton(RobotMap.turn180Button) && !motionMagic180) {
//    		Robot.driveTrain.zeroEncoders();
//    		Robot.driveTrain.turn(180);
//    		motionMagic180 = true;
//		}       
		else if (motionMagic180 
				&& Math.abs((((RobotMap.wheelBase * Math.PI) * (180 / 360.0)) / RobotMap.distancePerRotation) - Robot.driveTrain.getPosition()) < 0.05) {
				motionMagic180 = false;
		}
		else if (!motionMagicRunning && !motionMagic180 && !turnRunning) {
			if (direction == 1) {
				Robot.driveTrain.driveForward(-Robot.oi.leftY(), -Robot.oi.rightY());
			}
			else {
				Robot.driveTrain.driveForward(Robot.oi.leftY(), Robot.oi.rightY());
			}
		}
    	
//    	Robot.driveTrain.setSpeed(1000);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.stop();
    }
}
