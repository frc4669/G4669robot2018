package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoIntake extends Command {

    public AutoIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cubeIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(0.35);
//    	Robot.cubeIntake.intake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Attempt at autorotate
    	if ((Robot.cubeIntake.getLeftDistance()>1)&&!(Robot.cubeIntake.getRightDistance()>1)) { //If cube is recognized on left but not right, turn right motors
    		Robot.cubeIntake.stopLeft();
    		Robot.cubeIntake.turnCubeRight();
    	} else if (!(Robot.cubeIntake.getLeftDistance()>1)&&(Robot.cubeIntake.getRightDistance()>1)) { //If cube is recognized on right but not  left, turn left motors
    		Robot.cubeIntake.stopRight();
    		Robot.cubeIntake.turnCubeLeft();
    	} else {
    		Robot.cubeIntake.intake();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cubeIntake.hasCube()||isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeIntake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
