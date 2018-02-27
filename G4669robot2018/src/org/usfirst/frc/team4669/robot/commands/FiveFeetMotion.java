package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.motionProfile.MotionProfileExample;
import org.usfirst.frc.team4669.robot.motionProfile.Profile;
import org.usfirst.frc.team4669.robot.motionProfile.Trajectories;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FiveFeetMotion extends Command {
		MotionProfileExample motion;
		Profile fiveFeetAndTurn = new Profile(Trajectories.fiveFeetL,Trajectories.fiveFeetR);
		

    public FiveFeetMotion() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motion = new MotionProfileExample(fiveFeetAndTurn,Robot.driveTrain.topLeftMotor,Robot.driveTrain.topRightMotor);
    	Robot.driveTrain.topLeftMotor.set(ControlMode.MotionProfile, 1);
    	Robot.driveTrain.topRightMotor.set(ControlMode.MotionProfile, 1);
    	motion.startMotionProfile();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//motion.control();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	return false;
        return motion.isLastL()&&motion.isLastR();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	motion.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
