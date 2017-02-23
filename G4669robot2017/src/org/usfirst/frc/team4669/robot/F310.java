package org.usfirst.frc.team4669.robot;

import edu.wpi.first.wpilibj.Joystick;

public class F310 {
	
	private Joystick f310;
	
	public F310() {
		f310 = new Joystick(RobotMap.f310);
	}
	
	public double getLeftX() {
		return f310.getRawAxis(0);
	}
	
	public double getLeftY() {
		return f310.getRawAxis(1);
	}
	
	public double getLeftTrigger() {
		return f310.getRawAxis(2);
	}
	
	public double getRightTrigger() {
		return f310.getRawAxis(3);
	}
	
	public double getRightX() {
		return f310.getRawAxis(4);
	}
	
	public double getRightY() {
		return f310.getRawAxis(5);
	}
	
	public boolean getGreenButton() {
		return f310.getRawButton(0);
	}
	
	public boolean getRedButton() {
		return f310.getRawButton(1);
	}
	
	public boolean getBlueButton() {
		return f310.getRawButton(3);
	}
	
	public boolean getOrangeButton() {
		return f310.getRawButton(2);
	}
	
	public boolean getLeftShoulderButton() {
		return f310.getRawButton(4);
	}
	
	public boolean getRightShoulderButton() {
		return f310.getRawButton(5);
	}
	
	public boolean getBackButton() {
		return f310.getRawButton(6);
	}
	
	public boolean getStartButton() {
		return f310.getRawButton(7);
	}
	
	public boolean getLeftJoyButton() {
		return f310.getRawButton(8);
	}
	
	public boolean getRightJoyButton() {
		return f310.getRawButton(9);
	}
	
	public double getDPadPOV() {
		return f310.getPOV();
	}

}
