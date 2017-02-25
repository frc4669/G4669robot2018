//
//package org.usfirst.frc.team4669.robot.commands;
//
//import edu.wpi.first.wpilibj.command.Command;
//
//import org.usfirst.frc.team4669.robot.Robot;
//
///**
// *
// */
//public class ServoMove extends Command {
//
//    public ServoMove() {
//        // Use requires() here to declare subsystem dependencies
//        requires(Robot.servoControl);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	 Robot.servoControl.buttonControl();
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	if(Robot.oi.getRightRawButton(5) || Robot.oi.getRightRawButton(3)) return true;
//    	else return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
