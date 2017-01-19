package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.DriveForward;
import org.usfirst.frc.team4669.robot.commands.Turn180Degrees;
import org.usfirst.frc.team4669.robot.commands.Turn45Degrees;
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
	private Joystick leftStick;
	private Joystick rightStick ;
	
	public OI() {
		leftStick = new Joystick(RobotMap.leftJoystick);
		rightStick = new Joystick(RobotMap.rightJoystick);
		
		SmartDashboard.putData("Zero Sensors", new ZeroSensors());
		SmartDashboard.putData("Turn45Degrees", new Turn45Degrees());
		SmartDashboard.putData("DriveForward", new DriveForward());
		SmartDashboard.putData("Turn180Degrees", new Turn180Degrees());
	}
	
	
	
	public double leftY() {
		return leftStick.getY();
	}
	
	public double rightY() {
		return rightStick.getY();
	}
	
	
	
}

