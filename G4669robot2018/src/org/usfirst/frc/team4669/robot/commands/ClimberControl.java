package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberControl extends Command {

    public ClimberControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.getLeftRawButton(RobotMap.stopClimb)) {
    		Robot.climber.stop();
    	}
    	if (Robot.f310.getRightShoulderButton()) {
    		Robot.climber.climbAll();
    	}
		Robot.climber.climbCenter(Robot.oi.getLeftRawButton(RobotMap.centerClimb));
		Robot.climber.climbLeft(Robot.oi.getLeftRawButton(RobotMap.leftClimb));
		Robot.climber.climbRight(Robot.oi.getLeftRawButton(RobotMap.rightClimb));
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
