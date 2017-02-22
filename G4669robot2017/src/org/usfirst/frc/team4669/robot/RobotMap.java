package org.usfirst.frc.team4669.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	//SUBSYSTEMS
	//Drivetrain motors
	public static final int driveTrainFrontLeft = 8;
	public static final int driveTrainRearLeft = 5;
	public static final int driveTrainFrontRight = 1;
	public static final int driveTrainRearRight = 4;

	//Other subsystem motors
	public static final int launchMotor = 7;
	public static final int intakeMotor = 3;
	public static final int feederMotor = 2; 
	public static final int doorMotor = 6;
	
	
	//DRIVER STATION	
	// Reverse output of motors if moving backwords
	public static final boolean reverseOutputTrain = true;
	public static final int leftJoystick = 0;
	public static final int rightJoystick = 1;

	public static final int launchButton = 1;
	public static final int intakeButton = 3;
	public static final int feedButton = 2;
	public static final int openDoorButton = 4;
	public static final int closeDoorButton = 5;
	
	//DOOR CONSTANT
	//RPM for opening the door
	public static final int doorSpeed = 0;//value in RPM relative to motor
	
	
	//DRIVE TRAIN SPEED PROPORTION
		public static final double driveTrainSpeedProportion = 0.85;	
	
	// OTHER
	public static final double wheelDiameter = 3.25;
	public static final double wheelBase = 29; //figure out real distance later
	/**
	 * Calculates circumference of wheel given diameter.
	 */
    public static final double wheelCircumference = Math.PI * wheelDiameter;
	
	public static final double encoderCountConstant  = wheelCircumference / 1440;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

