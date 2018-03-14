package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoIntake extends TimedCommand {

    public AutoIntake(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.cubeIntake);
    }

    public AutoIntake() {
        super(0.5);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.cubeIntake);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cubeIntake.intake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after timeout
    protected void end() {
    	Robot.cubeIntake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
