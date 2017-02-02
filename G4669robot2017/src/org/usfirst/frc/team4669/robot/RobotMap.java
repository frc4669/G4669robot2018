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
			public static final int driveTrainFrontRight = 7;
			public static final int driveTrainRearRight = 4;
		
		//Other subsystem motors
			public static final int launchMotor = 1;
			public static final int intakeMotor = 2;

	//DRIVER STATION	
		// Reverse output of motors if moving backwords
			public static final boolean reverseOutputTrain = true;
			public static final int leftJoystick = 1;
			public static final int rightJoystick = 0;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

