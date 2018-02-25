package org.usfirst.frc.team4669.robot.commands;

import java.io.File;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class LeftSideToRightScalePath extends Command {
	
	Trajectory trajectory;
	TankModifier modifier;
	EncoderFollower left;
	EncoderFollower right;

    public LeftSideToRightScalePath() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	File myFile = new File("Generated Profiles/LeftSideToRightScale_left_detailed.csv");
    	trajectory = Pathfinder.readFromCSV(myFile);
    	modifier = new TankModifier(trajectory).modify(1.85417); //Should be in ft
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	


    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	left.configureEncoder(Robot.driveTrain.getLeftEncoder(), 4096, RobotMap.wheelDiameter/12);
    	right.configureEncoder(Robot.driveTrain.getRightEncoder(), 4096, RobotMap.wheelDiameter/12);
    	
    	left.configurePIDVA(RobotMap.leftkP, RobotMap.leftkI, RobotMap.leftkD, 1 / 4, 0);
    	right.configurePIDVA(RobotMap.rightkP, RobotMap.rightkI, RobotMap.rightkD, 1 / 4, 0);
    	
    	double l = left.calculate(Robot.driveTrain.getLeftEncoder());
    	double r = right.calculate(Robot.driveTrain.getRightEncoder());

    	double gyro_heading = Robot.driveTrain.getGyroAngle(); // Assuming the gyro is giving a value in degrees
    	double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

    	double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
    	double turn = 0.8 * (-1.0/80.0) * angleDifference;

    	Robot.driveTrain.setSpeed(l + turn, r - turn);
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
