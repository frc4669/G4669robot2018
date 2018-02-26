package org.usfirst.frc.team4669.robot.commands;


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
public class LeftSideToRightScalePath extends Command {
	
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

    public LeftSideToRightScalePath() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    	Waypoint[] points = new Waypoint[] {
//    		    new Waypoint(0, 22.5, 0),
//    		    new Waypoint(15, 22.5, 0),
//    		    new Waypoint(20, 19, Pathfinder.d2r(-90)),
//    		    new Waypoint(20, 9, Pathfinder.d2r(-90)),
//    		    new Waypoint(24, 6.5, 0),
    		    
    		    new Waypoint(0, 23, 0),
    		    new Waypoint(0.5, 23, 0),
    		    new Waypoint(2, 21, Pathfinder.d2r(-90))
    	};
    	// Create the Trajectory Configuration
    	//
    	// Arguments:
    	// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
    	// Sample Count:        SAMPLES_HIGH (100 000)
//    	                      SAMPLES_LOW  (10 000)
//    	                      SAMPLES_FAST (1 000)
    	// Time Step:           0.05 Seconds
    	// Max Velocity:        1.7 m/s
    	// Max Acceleration:    2.0 m/s/s
    	// Max Jerk:            60.0 m/s/s/s
//    	config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 4.0, 3.0, 60.0);
    	config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 0.5, 0.25, 60.0);
    	trajectory = Pathfinder.generate(points, config);
    	modifier = new TankModifier(trajectory).modify(1.85417); //Should be in ft
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	


    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.zeroEncoders();
    	Robot.driveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Running Command");
    	
    	left.configureEncoder(Robot.driveTrain.getLeftEncoder(), 4096, RobotMap.wheelDiameter/12);
    	right.configureEncoder(Robot.driveTrain.getRightEncoder(), 4096, RobotMap.wheelDiameter/12);
    	
//    	left.configurePIDVA(RobotMap.leftkP, RobotMap.leftkI, RobotMap.leftkD, 1 / 4, 0);
//    	right.configurePIDVA(RobotMap.rightkP, RobotMap.rightkI, RobotMap.rightkD, 1 / 4, 0);
    	left.configurePIDVA(RobotMap.leftkP, RobotMap.leftkI, RobotMap.leftkD, 1 / 0.5, 0);
    	right.configurePIDVA(RobotMap.rightkP, RobotMap.rightkI, RobotMap.rightkD, 1 / 0.5, 0);
    	
    	l = left.calculate(Robot.driveTrain.getLeftEncoder());
    	r = right.calculate(Robot.driveTrain.getRightEncoder());
    	
    	

    	gyro_heading = Robot.driveTrain.getGyroAngle(); // Assuming the gyro is giving a value in degrees
    	desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

    	angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
    	turn = 0.8 * (-1.0/80.0) * angleDifference;

    	Robot.driveTrain.driveForward(l + turn, r - turn);
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
