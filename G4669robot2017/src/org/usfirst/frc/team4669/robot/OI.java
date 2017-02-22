package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.DriveForward;
import org.usfirst.frc.team4669.robot.commands.Turn;
import org.usfirst.frc.team4669.robot.commands.ZeroSensors;

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
		SmartDashboard.putData("Turn45DegreesLeft", new Turn(45));
		SmartDashboard.putData("Turn45DegreesRight", new Turn(-45));
		SmartDashboard.putData("DriveForward", new DriveForward(45));
		SmartDashboard.putData("Turn180Degrees", new Turn(180));
		SmartDashboard.putNumber("RPM1", 0);
		SmartDashboard.putNumber("RPM3", 0);
		SmartDashboard.putNumber("RPM2", 0);
		SmartDashboard.putNumber("Turn Angle", 0);
		SmartDashboard.putNumber("EncoderVel", Robot.fuelLauncher.getEncoderVel());

	}

	//Getting joystick values
	public double leftY() {
		return leftStick.getY();
	}
	public double rightY() {
		return rightStick.getY();
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

