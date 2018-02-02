package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorCommand extends Command {

    public ElevatorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	 requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.getLeftRawButton(RobotMap.stopElevatorButton)){
    		Robot.elevator.stop();
    	} 
    	else {
    		if (Robot.oi.getLeftRawButton(RobotMap.groundElevatorButton)){
    			Robot.elevator.groundHeight();
    		}
    		else if(Robot.oi.getLeftRawButton(RobotMap.switchElevatorButton)){
    			Robot.elevator.setHeight(RobotMap.elevatorSwitch);
    		}
    		else if(Robot.oi.getLeftRawButton(RobotMap.midElevatorButton)){
    			Robot.elevator.setHeight(RobotMap.elevatorMid);
    		}
    		else if(Robot.oi.getLeftRawButton(RobotMap.maxElevatorButton)){
    			Robot.elevator.setHeight(RobotMap.elevatorMax);
    		}
    		else {
    			Robot.elevator.customHeight(Robot.oi.leftY());
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
}
