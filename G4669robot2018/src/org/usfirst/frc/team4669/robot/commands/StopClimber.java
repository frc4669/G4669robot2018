package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class StopClimber extends InstantCommand {

    public StopClimber() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.climber);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.climber.stop();
    }

}
