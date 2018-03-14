package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.DriveMotionMagic;
import org.usfirst.frc.team4669.robot.commands.ZeroSensors;

//import java.util.concurrent.atomic.AtomicReference;

//import org.usfirst.frc.team4669.robot.commands.ZeroSensors;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released  and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

//	// PID variables with default vals
//	public static double DEFAULT_PID_P = 0.07;
//	public static double DEFAULT_PID_I = 0.001;
//	public static double DEFAULT_PID_D = 0.7;
//	public static double DEFAULT_PID_F = 0.25;
//	public static AtomicReference<Double> TALON_P_LEFT = new AtomicReference<Double>(DEFAULT_PID_P);
//	public static AtomicReference<Double> TALON_P_RIGHT = new AtomicReference<Double>(DEFAULT_PID_P);
//	public static AtomicReference<Double> TALON_I_LEFT = new AtomicReference<Double>(DEFAULT_PID_I);
//	public static AtomicReference<Double> TALON_I_RIGHT = new AtomicReference<Double>(DEFAULT_PID_I);
//	public static AtomicReference<Double> TALON_D_LEFT = new AtomicReference<Double>(DEFAULT_PID_D);
//	public static AtomicReference<Double> TALON_D_RIGHT = new AtomicReference<Double>(DEFAULT_PID_D);
//	public static AtomicReference<Double> TALON_F_LEFT = new AtomicReference<Double>(DEFAULT_PID_F);
//	public static AtomicReference<Double> TALON_F_RIGHT = new AtomicReference<Double>(DEFAULT_PID_F);
//	
	//Joystick variables
	private Joystick leftStick;
	private Joystick rightStick;

	public OI() {
		//Mapping joysticks
		leftStick = new Joystick(RobotMap.leftJoystick);
		rightStick = new Joystick(RobotMap.rightJoystick);
		//StringBuilder _sb = new StringBuilder();

		//SmartDashboard commands
		SmartDashboard.putData("Zero Sensors", new ZeroSensors());
//		SmartDashboard.putNumber("MotionMagicDistance", 0);
//		SmartDashboard.putNumber("TurnAngle", 90);
		SmartDashboard.putNumber("Drive Left Speed", 0);
		SmartDashboard.putNumber("Drive Right Speed", 0);
//		//PID SmartDashboard vals
//		SmartDashboard.putNumber("PID_P_LEFT", TALON_P_LEFT.get());
//		SmartDashboard.putNumber("PID_I_LEFT", TALON_I_LEFT.get());
//		SmartDashboard.putNumber("PID_D_LEFT", TALON_D_LEFT.get());
//		SmartDashboard.putNumber("PID_P_RIGHT", TALON_P_RIGHT.get());
//		SmartDashboard.putNumber("PID_I_RIGHT", TALON_I_RIGHT.get());
//		SmartDashboard.putNumber("PID_D_RIGHT", TALON_D_RIGHT.get());
//		SmartDashboard.putNumber("PID_F_LEFT", TALON_F_LEFT.get());
//		SmartDashboard.putNumber("PID_F_RIGHT", TALON_F_RIGHT.get());
	}

	//Getting joystick values
	public double leftY() {
		double joystickValue = leftStick.getY();
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
	public double leftX() {
		double joystickValue = leftStick.getX();
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
	public double rightY() {
		double joystickValue = rightStick.getY();
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
	public double rightX() {
		double joystickValue = rightStick.getX();
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
	
	public boolean getLeftRawButton(int button) {
		return leftStick.getRawButton(button);
	}
	public boolean getRightRawButton(int button) {
		return rightStick.getRawButton(button);
	}
	
	public Joystick getLeftStick() {
		return leftStick;
	}
	public Joystick getRightStick() {
		return rightStick;
	}
	/**
	public StringBuilder get_sb() {
		return Robot.oi._sb.toString();
	}
	 */
}

