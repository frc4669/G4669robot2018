package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Intake extends Command {

    public Intake() {
        // Use requires() here to declare subsystem dependencies
    	// eg. requires(chassis);
        requires(Robot.cubeIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.f310.getLeftShoulderButton()){
    		Robot.cubeIntake.releaseArms();
    	}
    	else if(!Robot.f310.getLeftShoulderButton()){
    		Robot.cubeIntake.stopServo();
    	}
    	if(Robot.f310.getBlueButton()){
    		Robot.cubeIntake.turnCubeLeft();
    	} else if(Robot.f310.getRedButton()){
    		Robot.cubeIntake.turnCubeRight();
    	}
    	else if(Robot.f310.getGreenButton()){
//    		Robot.cubeIntake.intake();
    		//Attempt at autorotate and intake
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
		else if(Robot.f310.getOrangeButton()){
    		Robot.cubeIntake.releaseCube();
    	}
		else if(Robot.f310.getRightTrigger()!=0){
    		Robot.cubeIntake.set(-0.7);
    	}
		else {
    		Robot.cubeIntake.stopIntake();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
