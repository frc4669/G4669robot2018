package org.usfirst.frc.team4669.robot;

import edu.wpi.first.wpilibj.Joystick;

public class F310 {
	
	//x on back switch, mode light off
	private Joystick f310;
	
	public F310() {
		f310 = new Joystick(RobotMap.f310);
	}
	
	public double getLeftX() {
		return deadzone(0);
	}
	
	public double getLeftXSquared() {
		return Math.pow(getLeftX(),3);
	}
	
	public double getLeftY() {
		return -deadzone(1);
	}
	
	public double getLeftYCubed() {
		return Math.pow(getLeftY(),3);
	}
	
	public double getLeftTrigger() {
		return f310.getRawAxis(2);
	}
	
	public double getRightTrigger() {
		return f310.getRawAxis(3);
	}
	
	public double getRightX() {
		return deadzone(4);
	}
	
	public double getRightXCubed() {
		return Math.pow(getRightX(),3);
	}
	
	
	
	//ControlWinch
	public double getRightY() {
		return -deadzone(5);
	}
	//OpenDoor
	public boolean getGreenButton() {
		return f310.getRawButton(1);
	}
	//Stop Bot
	public boolean getRedButton() {
		return f310.getRawButton(2);
	}
	
	public boolean getBlueButton() {
		return f310.getRawButton(3);
	}
	
	public boolean getOrangeButton() {
		return f310.getRawButton(4);
	}
	
	public boolean getLeftShoulderButton() {
		return f310.getRawButton(5);
	}
	
	public boolean getRightShoulderButton() {
		return f310.getRawButton(6);
	}
	
	public boolean getBackButton() {
		return f310.getRawButton(7);
	}
	
	public boolean getStartButton() {
		return f310.getRawButton(8);
	}
	
	public boolean getLeftJoyButton() {
		return f310.getRawButton(9);
	}
	
	public boolean getRightJoyButton() {
		return f310.getRawButton(10);
	}
	
	public double getDPadPOV() {
		return f310.getPOV();
	}
	
	public double deadzone(int port){
		double joystickValue = f310.getRawAxis(port);
		double joystickOffset = 0.075;
		double absJoystickValue = Math.abs(joystickValue);
		if (absJoystickValue > joystickOffset) {
			double speed = absJoystickValue;
			speed = (speed*speed) + joystickOffset;
			if (joystickValue > 0) 
				return speed;
			else
				return -speed;
		}
		else {
			return 0;
		}
	}

}
