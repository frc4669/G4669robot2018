package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.OI;
import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveMotionMagic extends Command {

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
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.setPID(
    			SmartDashboard.getNumber("PID_P_LEFT", OI.DEFAULT_PID_P), 
    			SmartDashboard.getNumber("PID_I_LEFT", OI.DEFAULT_PID_I),
    			SmartDashboard.getNumber("PID_D_LEFT", OI.DEFAULT_PID_D),
    			SmartDashboard.getNumber("PID_P_RIGHT", OI.DEFAULT_PID_P),
    			SmartDashboard.getNumber("PID_I_RIGHT", OI.DEFAULT_PID_I),
    			SmartDashboard.getNumber("PID_D_RIGHT", OI.DEFAULT_PID_D)
    	);
    	Robot.driveTrain.driveMotionMagic(distanceToTravel);
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
