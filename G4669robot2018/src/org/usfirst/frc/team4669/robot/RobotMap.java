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
	public static final int elevator = 11;
	public static final int rightClimber = 9;
	public static final int centerClimber = 13;
	public static final int leftClimber = 6;
	
	//Sensors
	public static final int forwardLimitSwitch = 1;
	public static final int reverseLimitSwitch = 2;
	public static final int distanceSensorLeft = 0;
	public static final int distanceSensorRight = 1;

	
	//DRIVER STATION	
	// Reverse output of motors if moving backwards
	public static final boolean reverseOutputTrain = false;
	public static final int leftJoystick = 0;
	public static final int rightJoystick = 1;
	public static final int f310 = 2;
	
	
	//Joystick Controls
	public static final int stopElevatorButton= 1;
	public static final int groundElevatorButton= 2;
	public static final int midElevatorButton= 3;
	public static final int exchangeElevatorButton= 4;
	public static final int switchElevatorButton= 5;
	public static final int leftClimb = 6;
	public static final int centerClimb = 7;
	public static final int zeroEncoderElevatorButton = 9;
	public static final int stopClimb = 10;
	public static final int rightClimb = 11;
	
	//CONSTANTS
	public static final int elevatorVel = 900;
	public static final int elevatorAccel = 3000;
	
	public static final int elevatorSwitch = -10000;
	public static final int elevatorExchange = -2450;
	public static final int elevatorScaleMid = -23000;
	public static final int elevatorMax = -28300;
	
	public static final double gain = 0.15; //Speed to take off top for arcade drive throttle
	
	//FIELD MEASUREMENTS, in inches
	public static final int distToSwitchSides = 133;
	public static final int distToScaleStraight = 290;
	public static final int distHorizontalScales = 210; //distance between the left and right scale
	public static final int distSwitchSidesFromCenter= 35;
	public static final int distBaseLine = 85;
	
	//DRIVE TRAIN SPEED PROPORTION
	public static final double driveTrainSpeedProportion = 0.85;
	
	//DRIVE TRAIN PIDF VALUES
	public static final double leftkF = 0.3343;
	public static final double leftkP = 0.4;
	public static final double leftkI = 0.0003;
	public static final double leftkD = 20;
	public static final int leftkIZone = 50;
	
	public static final double rightkF = 0.3343;
	public static final double rightkP = 0.4;
	public static final double rightkI = 0.0003;
	public static final double rightkD = 20;
	public static final int rightkIZone = 50;
	
	public static final double kPGyro = 0.009;
	public static final double kIGyro = 0.00002;
	public static final double kDGyro = 0.0025;
	
	//NEW TALON SRX STUFF
	public static int timeout = 10;
	public static int slotIdx = 0;
	public static int pidIdx = 0;
	
	// OTHER, units are inches
	public static final double wheelDiameter = 4;
	public static final double wheelBase = 22.25; //figure out real distance later
	/**
	 * Calculates circumference of wheel given diameter.
	 * 
	 */
    public static final double wheelCircumference = Math.PI * wheelDiameter;
    public static final int encoderTicksPerRotation = 4096;
    
	public static final double inchToEncoder  = wheelCircumference / encoderTicksPerRotation; //Used to convert inches to encoder ticks
	
	public static final int baseTrajPeriodMs = 0;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final double distancePerRotation = wheelBase*Math.PI/4;
	
	public static final int angleTolerance = 2;

}

