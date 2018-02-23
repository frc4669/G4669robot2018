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
	public static final int driveTrainTopLeft = 5;
	public static final int driveTrainBottomLeft = 10;
	public static final int driveTrainTopRight = 8;
	public static final int driveTrainBottomRight = 7;

	//Other subsystem motors
	public static final int leftIntake = 1;
	public static final int rightIntake = 2;
	public static final int elevator = 3;
	public static final int climber = 6;
	public static final int armRaiser = 13;
	
	//Sensors
	public static final int forwardLimitSwitch = 1;
	public static final int reverseLimitSwitch = 2;
	public static final int distanceSensor = 1;
	
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
	public static final int zeroEncoderElevatorButton = 9;
	public static final int motionMagicTest = 10;
	
	
	//Right Joystick Controls
	
	
	//CONSTANTS
	public static int elevatorVel = 785;
	public static int elevatorAccel = 3140;
	
	public static int elevatorSwitch = -11350;
	public static int elevatorMid = -19379;
	public static int elevatorMax = -25290;
	
	public static double gain = 0.3;
	
	//FIELD MEASUREMENTS
	public static int distToSwitchSides = 217;
	public static int distToScaleStraight = 230;
	public static int distLeftAndRightScales = 210;
	
	//DRIVE TRAIN SPEED PROPORTION
	public static final double driveTrainSpeedProportion = 0.85;
	
	//NEW TALON SRX STUFF
	public static int timeout = 20;
	public static int slotIdx = 0;
	public static int pidIdx = 0;
	
	// OTHER, units are inches
	public static final double wheelDiameter = 4;
	public static final double wheelBase = 21.75; //figure out real distance later
	/**
	 * Calculates circumference of wheel given diameter.
	 * 
	 */
    public static final double wheelCircumference = Math.PI * wheelDiameter;
	
	public static final double encoderCountConstant  = wheelCircumference / 4096;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final double distancePerRotation = 40.8;
	
	public static final int angleTolerance = 2;
}

