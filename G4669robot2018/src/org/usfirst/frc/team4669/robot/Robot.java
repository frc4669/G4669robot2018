package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.CenterSwitch;
import org.usfirst.frc.team4669.robot.commands.DoNothing;
import org.usfirst.frc.team4669.robot.commands.DriveForward;
import org.usfirst.frc.team4669.robot.commands.DriveMotionMagic;
import org.usfirst.frc.team4669.robot.commands.DriveSpeedControl;
import org.usfirst.frc.team4669.robot.commands.FiveFeetMotion;
import org.usfirst.frc.team4669.robot.commands.LeftAllScale;
//import org.usfirst.frc.team4669.robot.commands.LeftSideToRightScalePath;
import org.usfirst.frc.team4669.robot.commands.LeftSwitch;
import org.usfirst.frc.team4669.robot.commands.LeftSwitchLeftScale;
import org.usfirst.frc.team4669.robot.commands.RightAllScale;
import org.usfirst.frc.team4669.robot.commands.RightSwitch;
import org.usfirst.frc.team4669.robot.commands.RightSwitchRightScale;
import org.usfirst.frc.team4669.robot.commands.StopClimber;
import org.usfirst.frc.team4669.robot.commands.TurnTo;
import org.usfirst.frc.team4669.robot.commands.UnwindCenter;
import org.usfirst.frc.team4669.robot.commands.UnwindLeft;
import org.usfirst.frc.team4669.robot.commands.UnwindRight;
import org.usfirst.frc.team4669.robot.commands.TurnMotionMagic;
import org.usfirst.frc.team4669.robot.subsystems.Climber;
import org.usfirst.frc.team4669.robot.subsystems.CubeIntake;
//import org.usfirst.frc.team4669.robot.commands.LaunchAndMoveAuto;
import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;
//import org.usfirst.frc.team4669.robot.subsystems.FuelAgitator;
//import org.usfirst.frc.team4669.robot.subsystems.FuelDoor;
//import org.usfirst.frc.team4669.robot.subsystems.FuelIntakeElevator;
//import org.usfirst.frc.team4669.robot.subsystems.FuelLauncher;
//import org.usfirst.frc.team4669.robot.subsystems.RopeWinch;
import org.usfirst.frc.team4669.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends TimedRobot {

	public static DriveTrain driveTrain = new DriveTrain();
//	public static FuelLauncher fuelLauncher = new FuelLauncher();
//	public static FuelIntakeElevator fuelIntakeElevator = new FuelIntakeElevator();
//	public static FuelDoor door = new FuelDoor();
//	public static RopeWinch ropeWinch = new RopeWinch();
//	public static FuelAgitator fuelAgitator = new FuelAgitator();
	public static DriverStation driverStation;
	public static CubeIntake cubeIntake =  new CubeIntake();
	public static Elevator elevator = new Elevator();
	public static Climber climber = new Climber();
	public static OI oi = new OI();
	public static F310 f310 = new F310();
	public static String gameData = "";

	Command autonomousCommand;
	SendableChooser<String> chooser;
	String autonomousString;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		driveTrain.zeroEncoders();
		driveTrain.resetGyro();
		driveTrain.calibrateGyro();
		chooser = new SendableChooser<String>();
		chooser.addDefault("Do Nothing", "Do Nothing");
		chooser.addObject("Drive Forward", "Drive Forward");
		chooser.addObject("Left Both Scales", "Left Both Scales");
		chooser.addObject("Left Switch or Scale", "Left Switch or Scale");
		chooser.addObject("Left Switch", "Left Switch");
		chooser.addObject("Center Switch", "Center Switch");
		chooser.addObject("Right Both Scales", "Right Both Scales");
		chooser.addObject("Right Switch or Scale", "Right Switch or Scale");
		chooser.addObject("Right Switch", "Right Switch");
		chooser.addObject("Left To Right Scale", "Left To Right Scale");
		chooser.addObject("5 Feets", "Five Feet Motion");
		
		SmartDashboard.putData("Auto mode", chooser);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit(){

	}

	public void disabledPeriodic() {
		driverStation = DriverStation.getInstance();
		updateSmartDashboard();
		Scheduler.getInstance().run();
		if (autonomousCommand != null) autonomousCommand.cancel();
		Robot.driveTrain.stop();
		Robot.cubeIntake.stopIntake();
		Robot.cubeIntake.stopServo();
		Robot.elevator.stop();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		gameData = driverStation.getGameSpecificMessage();
		autonomousString = (String) chooser.getSelected();
		driveTrain.zeroEncoders();
		driveTrain.resetGyro();
		if(autonomousString.equals("Do Nothing")){
			autonomousCommand = new DoNothing();
		}
		else if(autonomousString.equals("Drive Forward")){
			autonomousCommand = new DriveForward();
		}
		else if(autonomousString.equals("Left Both Scales")){
			autonomousCommand = new LeftAllScale();
		}
		else if(autonomousString.equals("Left Switch or Scale")){
			autonomousCommand = new LeftSwitchLeftScale();
		}
		else if(autonomousString.equals("Left Switch")){
			autonomousCommand = new LeftSwitch();
		}
		else if(autonomousString.equals("Center Switch")){
			autonomousCommand = new CenterSwitch();
		}
		else if(autonomousString.equals("Right Both Scales")){
			autonomousCommand = new RightAllScale();
		}
		else if(autonomousString.equals("Right Switch or Scale")){
			autonomousCommand = new RightSwitchRightScale();
		}
		else if(autonomousString.equals("Right Switch")){
			autonomousCommand = new RightSwitch();
		}
		else if(autonomousString.equals("Left To Right Scale")){
//			autonomousCommand = new LeftSideToRightScalePath();
		}
		else if(autonomousString.equals("Five Feet Motion")){
			autonomousCommand = new FiveFeetMotion();
		}
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		updateSmartDashboard();
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		updateSmartDashboard();
		Scheduler.getInstance().run();
	}

	public void testInit(){
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

	public void updateSmartDashboard() {
    	SmartDashboard.putData("Gyro",(Sendable) driveTrain.analogGyro);
    	SmartDashboard.putBoolean("Has Cube", cubeIntake.hasCube());
//    	SmartDashboard.putData(new TurnMotionMagic(SmartDashboard.getNumber("TurnAngle", 90)));
//    	SmartDashboard.putData(new TurnTo(SmartDashboard.getNumber("TurnAngle", 90)));
//    	SmartDashboard.putData(new DriveMotionMagic(SmartDashboard.getNumber("MotionMagicDistance", 0)));
//    	SmartDashboard.putData(new DriveSpeedControl(SmartDashboard.getNumber("Drive Left Speed", 0),SmartDashboard.getNumber("Drive Right Speed", 0)));
//    	SmartDashboard.putNumber("Left Position", driveTrain.getLeftEncoder());
//    	SmartDashboard.putNumber("Right Position", driveTrain.getRightEncoder());
//    	SmartDashboard.putNumber("Left Velocity", Robot.driveTrain.getLeftEncoderSpeed());
//    	SmartDashboard.putNumber("Right Velocity", Robot.driveTrain.getRightEncoderSpeed());
    	SmartDashboard.putData("Accelerometer",(Sendable) climber.accel);
//    	SmartDashboard.putNumber("POV Angle", Robot.f310.getDPadPOV());
//    	SmartDashboard.putNumber("Intake Left Enc", Robot.cubeIntake.getLeftEncoder());
//    	SmartDashboard.putNumber("Intake Right Enc", Robot.cubeIntake.getRightEncoder());
//    	SmartDashboard.putNumber("intakeLeftEncVel", Robot.cubeIntake.getLeftEncoderSpeed());
//    	SmartDashboard.putNumber("intakeRightEncVel", Robot.cubeIntake.getRightEncoderSpeed());
//    	SmartDashboard.putNumber("Right Current", Robot.driveTrain.getRightCurrent());
//    	SmartDashboard.putNumber("Left Current", Robot.driveTrain.getLeftCurrent());
//		SmartDashboard.putNumber("Elevator Vel", Robot.elevator.getEncoderVel());
//		SmartDashboard.putNumber("Elevator Pos", Robot.elevator.getEncoderPos());
	}
}
