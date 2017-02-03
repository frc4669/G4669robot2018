package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class Launch extends Command {
	
	private int _loops = 0;

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
    	 if(++_loops >= 10) {

         	_loops = 0;
    	 }
    	 Robot.fuelLauncher.launch();

         	// System.out.println(Robot.oi._sb.toString());
    	
    	// SmartDashboard.putNumber("Motor Speed" , TalonControlMode.Voltage);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.oi.getRawButton(RobotMap.launchButton)) return false;
    	else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

