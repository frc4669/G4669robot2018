package org.usfirst.frc.team4669.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
//import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;

/**
 *
 */
public class TurnToGoal extends Command {
	
//	private DriveTrain driveTrain;
	private double degree = 0;
//	private double degreesToTurn;
//	private double rotationToTravel;

	public TurnToGoal() {
//        driveTrain = Robot.driveTrain;
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.zeroEncoders();
    	double distance = -0.154*Robot.visionTable.getNumber("w", 130)+28.3;
    	degree = Math.atan((Robot.visionTable.getNumber("x", 320)-320)/distance);
//    	degreesToTurn = degree*(RobotMap.wheelBase*Math.PI/360); //16.5 distance between wheels
//    	rotationToTravel = degreesToTurn / RobotMap.distancePerRotation;
    	Robot.driveTrain.turn(degree);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs((((RobotMap.wheelBase * Math.PI) * (degree / 360.0)) / RobotMap.distancePerRotation) - Robot.driveTrain.getPosition()) < 0.05;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}