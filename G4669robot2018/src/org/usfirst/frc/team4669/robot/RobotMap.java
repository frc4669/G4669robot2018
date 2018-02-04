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
	public static final int driveTrainTopLeft = 2; //8
	public static final int driveTrainBottomLeft = 98; //3
	public static final int driveTrainTopRight = 99; //5
	public static final int driveTrainBottomRight = 3; //1

	//Other subsystem motors
	public static final int leftIntake = 7;
	public static final int rightIntake = 8;
	public static final int elevator = 5;
	
	//DRIVER STATION	
	// Reverse output of motors if moving backwards
	public static final boolean reverseOutputTrain = false;
	public static final int leftJoystick = 0;
	public static final int rightJoystick = 1;
	public static final int f310 = 2;
	
	
	//Left Joystick Controls
	public static final int stopElevatorButton= 1;
	public static final int groundElevatorButton= 2;
	public static final int maxElevatorButton= 3;
	public static final int midElevatorButton= 4;
	public static final int switchElevatorButton= 5;
	
	
	//Right Joystick Controls
	
	
	//CONSTANTS
	public static int elevatorVel = 1365;
	public static int elevatorAccel = 340;
	
	public static int elevatorSwitch = 0;
	public static int elevatorMid = 0;
	public static int elevatorMax = 0;
	
	//DRIVE TRAIN SPEED PROPORTION
	public static final double driveTrainSpeedProportion = 0.85;
	
	//NEW TALON SRX STUFF
	public static int timeout = 20;
	public static int slotIdx = 0;
	public static int pidIdx = 0;
	
	// OTHER, units are inches
	public static final double wheelDiameter = 4;
	public static final double wheelBase = 27.4; //figure out real distance later
	/**
	 * Calculates circumference of wheel given diameter.
	 * 
	 */
    public static final double wheelCircumference = Math.PI * wheelDiameter;
	
	public static final double encoderCountConstant  = wheelCircumference / 1440;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final double distancePerRotation = 40.8;
	
	public static final int angleTolerance = 5;
}

