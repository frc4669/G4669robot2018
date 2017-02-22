package org.usfirst.frc.team4669.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.subsystems.Switches;

public class FuelLoader extends Command {
	
	public FuelLoader() {
		requires(Robot.switches);
	}
	
	protected void initialize() {
		Robot.switches.initializeCounter();
		Robot.servoControl.thirdSpin(Robot.servoControl.getServo2());
	}
	
	protected void execute() {
//		Robot.switches.initializeCounter();
//		Robot.servoControl.servoMove(Robot.servoControl.getServo2(), 1);
		Robot.servoControl.thirdSpin(Robot.servoControl.getServo2());
	}
	
	protected void end() {
//		Robot.servoControl.servoMove(Robot.servoControl.getServo2(), 0);
    }
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.switches.isSwitchSet();
	}
	
	
}
