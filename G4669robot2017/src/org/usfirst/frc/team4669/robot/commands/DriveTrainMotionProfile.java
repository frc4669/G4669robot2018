package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainMotionProfile extends Command {
	
	private boolean [] _btnsLast = {false,false,false,false,false,false,false,false,false,false};
    
    public DriveTrainMotionProfile() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	boolean [] btns= new boolean [_btnsLast.length];
		for(int i=1;i<_btnsLast.length;++i)
			btns[i] = Robot.oi.getLeftStick().getRawButton(i);

		/* get the left joystick axis on Logitech Gampead */
		double leftYjoystick = -1 * Robot.oi.getLeftStick().getY(); /* multiple by -1 so joystick forward is positive */

		/* call this periodically, and catch the output.  Only apply it if user wants to run MP. */
		Robot.driveTrain.control();
		
		if (btns[5] == false) { /* Check button 5 (top left shoulder on the logitech gamead). */
			/*
			 * If it's not being pressed, just do a simple drive.  This
			 * could be a RobotDrive class or custom drivetrain logic.
			 * The point is we want the switch in and out of MP Control mode.*/
		
			/* button5 is off so straight drive */
			Robot.driveTrain.changeControlMode(TalonControlMode.Voltage);
			Robot.driveTrain.set(12.0 * leftYjoystick);

			Robot.driveTrain.reset();
		} else {
			/* Button5 is held down so switch to motion profile control mode => This is done in MotionProfileControl.
			 * When we transition from no-press to press,
			 * pass a "true" once to MotionProfileControl.
			 */
			Robot.driveTrain.changeControlMode(TalonControlMode.MotionProfile);
			
			CANTalon.SetValueMotionProfile setOutput = Robot.driveTrain.getSetValue();
					
			Robot.driveTrain.set(setOutput.value);

			/* if btn is pressed and was not pressed last time,
			 * In other words we just detected the on-press event.
			 * This will signal the robot to start a MP */
			if( (btns[6] == true) && (_btnsLast[6] == false) ) {
				/* user just tapped button 6 */
				Robot.driveTrain.startMotionProfile();
			}
		}

		/* save buttons states for on-press detection */
		for(int i=1;i<10;++i)
			_btnsLast[i] = btns[i];
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.get_state() == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
		/* it's generally a good idea to put motor controllers back
		 * into a known state when robot is disabled.  That way when you
		 * enable the robot doesn't just continue doing what it was doing before.
		 * BUT if that's what the application/testing requires than modify this accordingly */
		Robot.driveTrain.changeControlMode(TalonControlMode.PercentVbus);
		Robot.driveTrain.set( 0 );
		/* clear our buffer and put everything into a known state */
		Robot.driveTrain.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    
}
