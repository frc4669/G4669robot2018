package org.usfirst.frc.team4669.robot.commands;


import java.io.File;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class TestPath extends Command {
	
//	Trajectory trajectory;
//	Trajectory.Config config;
//	TankModifier modifier;
	DistanceFollower left;
	DistanceFollower right;
	double l,r, turn;
	double gyro_heading, desired_heading, angleDifference;
	int intEncPosR, intEncPosL;

    public TestPath() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);

    	/*left.configureEncoder(Robot.driveTrain.getLeftEncoder(), 4096, RobotMap.wheelDiameter/12);
    	right.configureEncoder(Robot.driveTrain.getRightEncoder(), 4096, RobotMap.wheelDiameter/12);    	

    	modifier = new TankModifier(trajectory).modify(1.85417); //Wheelbase in ft
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());*/
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.zeroEncoders();
    	Robot.driveTrain.resetGyro();
    	
    	/*Getting the left and right trajectories from the CSV files*/
    	File leftCSV = new File("/home/lvuser/Profiles/Test Path_left_Jaci.csv");
    	File rightCSV = new File("/home/lvuser/Profiles/Test Path_right_Jaci.csv");
    	left = new DistanceFollower(Pathfinder.readFromCSV(leftCSV));
    	right = new DistanceFollower(Pathfinder.readFromCSV(rightCSV));
    	
    	intEncPosL = Robot.driveTrain.getLeftEncoder();
    	intEncPosR = Robot.driveTrain.getRightEncoder();
    	
    	left.configurePIDVA(RobotMap.leftkP, RobotMap.leftkI, RobotMap.leftkD, 1/.5, 0);
    	right.configurePIDVA(RobotMap.rightkP, RobotMap.rightkI, RobotMap.rightkD, 1/.5, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("Running Command");
    	double leftDistCovered = (Robot.driveTrain.getLeftEncoder()-intEncPosL)/RobotMap.encoderToInch;
    	double rightDistCovered = (Robot.driveTrain.getRightEncoder()-intEncPosR)/RobotMap.encoderToInch;
    	l = left.calculate(leftDistCovered);
    	r = right.calculate(rightDistCovered);
    	
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
