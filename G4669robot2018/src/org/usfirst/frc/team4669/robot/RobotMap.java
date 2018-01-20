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
	public static final int driveTrainTopLeft = 13; //8
	public static final int driveTrainBottomLeft = 3; //3
	public static final int driveTrainTopRight = 12; //5
	public static final int driveTrainBottomRight = 1; //1

	//Other subsystem motors
//	public static final int launchMotorLeft = 6;
	public static final int launchMotorRight = 7;
//	public static final int feederMotorLeft = 11; 
	public static final int feederMotorRight = 10; 
	public static final int intakeElevatorMotor = 8;
	public static final int doorMotor = 9;
	public static final int climbMotor = 3;
	public static final int agitatorMotor = 6;
	
	
	//DRIVER STATION	
	// Reverse output of motors if moving backwords
	public static final boolean reverseOutputTrain = false;
	public static final int leftJoystick = 0;
	public static final int rightJoystick = 1;
	
	
	//Left Joystick Controls
	public static final int intakeFrontButton = 3;
	public static final int gearHolderFrontButton = 2;
	public static final int moveBackButton= 1;
	
	
	//Right Joystick Controls
	public static final int launchButton = 2;
	public static final int intakeButton = 1;
	public static final int turn180Button = 4;
//	public static final int feedButton = 3;
//	public static final int openDoorButton = 4;
//	public static final int closeDoorButton = 5;
	
	
	//DOOR CONSTANT
	//PercentVBus for opening the door
	public static final double doorSpeed = 0.75;//value in PercentVbus
	
	
	//DRIVE TRAIN SPEED PROPORTION
	public static final double driveTrainSpeedProportion = 0.85;	
	
	// OTHER
	public static final double wheelDiameter = 3.25;
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
	public static final int f310 = 2;
	public static final double distancePerRotation = 40.8;
}

