package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorCommand extends Command {
	
	double targetPos;
	boolean motionMagicRunning = false;
	boolean positionRunning = false;

    public ElevatorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	 requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.zeroEncoders();
    	motionMagicRunning = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.elevator.getForwardLimit()){
    		Robot.elevator.zeroEncoders();
    	}
    	if (Robot.oi.getLeftRawButton(RobotMap.stopElevatorButton)){
    		Robot.elevator.stop();
    	} 
    	else {
    		if (Robot.oi.getLeftRawButton(RobotMap.zeroEncoderElevatorButton) && !motionMagicRunning){
    			Robot.elevator.zeroEncoders();
    		}
    		if (Robot.oi.getLeftRawButton(RobotMap.groundElevatorButton) && !motionMagicRunning){
    			Robot.elevator.setHeight(0);
    			targetPos = 0;
    			motionMagicRunning = true;
    		}
    		else if(Robot.oi.getLeftRawButton(RobotMap.switchElevatorButton) && !motionMagicRunning){
    			Robot.elevator.setHeight(RobotMap.elevatorSwitch);
    			targetPos = RobotMap.elevatorSwitch;
    			motionMagicRunning = true;
    		}
    		else if(Robot.oi.getLeftRawButton(RobotMap.midElevatorButton) && !motionMagicRunning){
    			Robot.elevator.setHeight(RobotMap.elevatorMid);
    			targetPos = RobotMap.elevatorMid;
    			motionMagicRunning = true;
    		}
    		else if(Robot.oi.getLeftRawButton(RobotMap.maxElevatorButton) && !motionMagicRunning){
    			Robot.elevator.setHeight(RobotMap.elevatorMax);
    			targetPos = RobotMap.elevatorMax;
    			motionMagicRunning = true;
    		}
			else if (motionMagicRunning 
					&& Math.abs(targetPos-Robot.elevator.getEncoderPos()) < 2) {
					motionMagicRunning = false;
			}
    		
    		else if (!motionMagicRunning) {
    			if (Robot.oi.leftY()==0&&!positionRunning){
    				Robot.elevator.zeroVelocity();
    				positionRunning = true;
    			} 
    			if (Robot.oi.leftY()!=0){
    				Robot.elevator.customHeight(Robot.oi.leftY());
    				positionRunning = false;
    			}
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
