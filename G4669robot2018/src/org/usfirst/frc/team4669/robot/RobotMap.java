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
	public static final int midElevatorButton= 6;
	public static final int exchangeElevatorButton= 7;
	public static final int switchElevatorButton= 5;
	public static final int maxElevatorButton = 3;
	public static final int hasCubeElevatorButton = 4;
	public static final int button9Elevator = 9;
	public static final int stopClimb = 10;
	public static final int rightClimb = 11;
	
	//CONSTANTS
	public static final int elevatorVel = 1100;
	public static final int elevatorAccel = 1300;
	
	public static final int elevatorDownVel = 1300;
	public static final int elevatorDownAccel = 2900;
	
	public static final int elevatorSwitch = -10000;
	public static final int elevatorExchange = -2450;
	public static final int elevatorLift = -1200;
	public static final int elevatorScaleMid = -23000;
	public static final int elevatorMax = -27295;
	
	public static final double gain = 0.15; //Speed to take off top for arcade drive throttle
	
	//FIELD MEASUREMENTS, in inches
	public static final double distToSwitchSides = 140;
	public static final double distPastSwitch2Cube = 69+22; // Dist to be parallel to cube = 69, dist to go past to grab = 22.5
	public static final double distToCube2Cube = 22*Math.sqrt(2); // Dist to the cube in 2 cube 
	public static final double distToScaleStraight = 300;
	public static final double distBwtnScales = 264; //Distance between the left and right scale
	public static final double distBtwnSwitch= 42; //Distance to the left or right switch from the center, horizontally
	public static final double distToInFrontSwitch= 220; //Distance to the open area in front of the switch
	public static final double distBaseLine = 85;
	public static final double distSwitchFromCenter = 90;
	
	
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
	
	public static final double kPGyro = 0.025;
	public static final double kIGyro = 0.0;
	public static final double kDGyro = 0.045;
	
	//NEW TALON SRX STUFF
	public static int timeout = 10;
	public static int slotIdx = 0;
	public static int pidIdx = 0;
	
	// OTHER, units are inches
	public static final double wheelDiameter = 4; //in inches
	public static final double wheelBase = 22.25; //figure out real distance later
	/**
	 * Calculates circumference of wheel given diameter.
	 * 
	 */
    public static final double wheelCircumference = Math.PI * wheelDiameter;
    public static final int encoderTicksPerRotation = 4096;
    
	public static final double inchToEncoder  = wheelCircumference / encoderTicksPerRotation; //Divide inches by this to convert to encoder ticks
	public static final double encoderToInch = encoderTicksPerRotation / wheelCircumference; //Divide encoder ticks by this to convert to inches
	
	public static final int baseTrajPeriodMs = 0;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final double distancePerRotation = wheelBase*Math.PI/4;
	
	public static final int angleTolerance = 2;
	public static final double kCollisionThresholdDeltaG = 3;

}

