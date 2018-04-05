package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UnwindRight extends TimedCommand {

    public UnwindRight(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (SmartDashboard.getBoolean("Invert Wind", false)==false){
    		Robot.climber.unwindRight(true);
    	} else {
    		Robot.climber.windRight(true);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after timeout
    protected void end() {
    	Robot.climber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.stop();
    	end();
    }
}
