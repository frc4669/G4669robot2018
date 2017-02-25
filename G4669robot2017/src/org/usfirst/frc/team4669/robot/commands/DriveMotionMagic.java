package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DriveMotionMagic extends InstantCommand {

    private double distanceToTravel;

	public DriveMotionMagic(double distance) {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        distanceToTravel = distance / RobotMap.encoderCountConstant;
        requires(Robot.driveTrain);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.driveTrain.driveMotionMagic(distanceToTravel);
    }

}
