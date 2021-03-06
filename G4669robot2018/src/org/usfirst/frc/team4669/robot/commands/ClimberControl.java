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
    	} else {
	    	if (Robot.oi.getLeftRawButton(8)) {
	    		System.out.println(Robot.climber.getAccelX()+" "+Robot.climber.getAccelY());
	    		if (Math.abs(Robot.climber.getAccelX())<0.1&&Math.abs(Robot.climber.getAccelY())<0.1){
	    			Robot.climber.climbAll();
	    		} else if(Robot.climber.getAccelY()<-0.1){
	    			Robot.climber.climbCenter(false);
	    			Robot.climber.climbLeft(true);
	    			Robot.climber.climbRight(true);
	    		} else if(Robot.climber.getAccelY()>0.1){
	    			Robot.climber.climbLeft(false);
	    			Robot.climber.climbRight(false);
	    			Robot.climber.climbCenter(true);
	    		} else if(Robot.climber.getAccelX()>0.1){
	    			Robot.climber.climbCenter(false);
	    			Robot.climber.climbRight(false);
	    			Robot.climber.climbLeft(true);
	    		} else if(Robot.climber.getAccelX()<-0.1){
	    			Robot.climber.climbCenter(false);
	    			Robot.climber.climbLeft(false);
	    			Robot.climber.climbRight(true);
	    		}
	    	} else {
				Robot.climber.stop();
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
