package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.motionProfile.MotionProfileExample;
import org.usfirst.frc.team4669.robot.motionProfile.Profile;
import org.usfirst.frc.team4669.robot.motionProfile.Trajectories;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FiveFeetMotion extends Command {
		MotionProfileExample motion;
		Profile fiveFeetAndTurn = new Profile(Trajectories.fiveFeetL,Trajectories.fiveFeetR);
		SetValueMotionProfile setOutput;
		int runProfile = 0;

    public FiveFeetMotion() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motion = new MotionProfileExample(fiveFeetAndTurn,Robot.driveTrain.topLeftMotor,Robot.driveTrain.topRightMotor);
    	Robot.driveTrain.topLeftMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 50, 10);
    	Robot.driveTrain.topRightMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 50, 10);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setOutput = motion.getSetValue();
    	Robot.driveTrain.topLeftMotor.set(ControlMode.MotionProfile, setOutput.value);
    	Robot.driveTrain.topRightMotor.set(ControlMode.MotionProfile, setOutput.value);
    	if (runProfile == 0) motion.startMotionProfile();
    	motion.control();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	return false;
        return motion.isLastL()&&motion.isLastR();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Finished");
    	Robot.driveTrain.stop();
    	motion.reset();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
