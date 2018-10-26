package org.usfirst.frc.team4669.robot.commands;


import java.io.File;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class TestPath extends Command {
	
	Trajectory trajectory;
	Trajectory.Config config;
	TankModifier modifier;
	EncoderFollower left;
	EncoderFollower right;
	double l;
	double r;
	double gyro_heading;
	double desired_heading;
	double angleDifference;
	double turn;

    public TestPath() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	File leftCSV = new File("/home/lvuser/Profiles/Test Path_left_Jaci.csv");
    	File rightCSV = new File("/home/lvuser/Profiles/Test Path_right_Jaci.csv");
    	left = new EncoderFollower(Pathfinder.readFromCSV(leftCSV));
    	right = new EncoderFollower(Pathfinder.readFromCSV(rightCSV));
       	
    	left.configureEncoder(Robot.driveTrain.getLeftEncoder(), 4096, RobotMap.wheelDiameter/12);
    	right.configureEncoder(Robot.driveTrain.getRightEncoder(), 4096, RobotMap.wheelDiameter/12);
    	
    	left.configurePIDVA(0.4, 0, 0, 1/.5, 0);
    	right.configurePIDVA(0.4, 0, 0, 1/.5, 0);

//    	modifier = new TankModifier(trajectory).modify(1.85417); //Wheelbase in ft
//    	left = new EncoderFollower(modifier.getLeftTrajectory());
//    	right = new EncoderFollower(modifier.getRightTrajectory());
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.zeroEncoders();
    	Robot.driveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("Running Command");
 
    	
    	l = left.calculate(Robot.driveTrain.getLeftEncoder());
    	r = right.calculate(Robot.driveTrain.getRightEncoder());
    	
    	gyro_heading = Robot.driveTrain.getGyroAngle(); // Assuming the gyro is giving a value in degrees
    	desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

    	angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
    	turn = 0.8 * (-1.0/80.0) * angleDifference;
   
    	System.out.println("Left: " + (l - turn));
    	System.out.println("Right: " + (r + turn));
    	
    	Robot.driveTrain.driveForward(l - turn, r + turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.f310.getRedButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
