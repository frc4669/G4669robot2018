package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Launch extends Command {

	boolean running = false;
	
	public Launch() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.fuelLauncher);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.getRightRawButton(RobotMap.launchButton)) {
			if (!running) {
				Robot.fuelLauncher.startLaunch();
				running = true;
			}
			Robot.fuelLauncher.launch();
		}
		else {
			if (running) {
				Robot.fuelLauncher.stop();
				running = false;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.fuelLauncher.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

