package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LaunchAndIntake extends Command {

    public LaunchAndIntake() {
        requires(Robot.fuelLauncher);
        requires(Robot.fuelIntakeElevator);
    }

 // Called just before this Command runs the first time
 	protected void initialize() {

 	}

 	// Called repeatedly when this Command is scheduled to run
 	protected void execute() {
 		if (Robot.oi.getRightRawButton(RobotMap.launchButton)) {
 				Robot.fuelLauncher.launch();
 				Robot.fuelIntakeElevator.intake();
 		}
 	}

 	// Make this return true when this Command no longer needs to run execute()
 	protected boolean isFinished() {
 		if (Robot.oi.getRightRawButton(RobotMap.launchButton)) {
 			return false;
 		}
 		else {
 			return true;
 		}
 	}

 	// Called once after isFinished returns true
 	protected void end() {
 		Robot.fuelLauncher.stop();
 		Robot.fuelIntakeElevator.stop();
 	}

 	// Called when another command which requires one or more of the same
 	// subsystems is scheduled to run
 	protected void interrupted() {
 		end();
 	}
}
