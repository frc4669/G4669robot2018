package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardNorth extends Command {

	private DriveTrain driveTrain;
	private double distanceToTravel;
	private double direction;
	
	private static final double kP = 0.03;	
	
	
    public DriveForwardNorth(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	driveTrain = Robot.driveTrain;
    	distanceToTravel = distance / RobotMap.encoderCountConstant;
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.zeroEncoders();
    	direction = Robot.driveTrain.getGyroAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	driveTrain.setDrive(0.5*RobotMap.driveTrainSpeedProportion, -(imu.getAngle()-direction)*kP);
    	driveTrain.setDrive(RobotMap.driveTrainSpeedProportion, 0);
        }
   

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distanceToTravel < driveTrain.getLeftEncoder();
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
