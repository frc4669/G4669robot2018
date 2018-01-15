
package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.CenterGearLiftAuto;
import org.usfirst.frc.team4669.robot.commands.DoNothing;
//import org.usfirst.frc.team4669.robot.commands.LaunchAndMoveAuto;
import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;
//import org.usfirst.frc.team4669.robot.subsystems.FuelAgitator;
//import org.usfirst.frc.team4669.robot.subsystems.FuelDoor;
//import org.usfirst.frc.team4669.robot.subsystems.FuelIntakeElevator;
//import org.usfirst.frc.team4669.robot.subsystems.FuelLauncher;
//import org.usfirst.frc.team4669.robot.subsystems.RopeWinch;

import edu.wpi.first.wpilibj.DriverStation;
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
	public static OI oi;
	public static F310 f310;
	public static DriverStation driverStation;

	Command autonomousCommand;
	SendableChooser<Command> chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		f310 = new F310();
		

		driveTrain.calibrateGyro();
		driveTrain.zeroEncoders();
		
		chooser = new SendableChooser<Command>();
		chooser.addDefault("Do Nothing", new DoNothing());
//		chooser.addObject("Launch and Move", new LaunchAndMoveAuto());
		chooser.addObject("Center Gear Lift", new CenterGearLiftAuto());
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
		autonomousCommand = (Command) chooser.getSelected();
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

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
	}

	public void updateSmartDashboard() {
    	SmartDashboard.putNumber("Gyro", driveTrain.getGyroAngle());
    	SmartDashboard.putNumber("Left Enocder", driveTrain.getLeftEncoder());
    	SmartDashboard.putNumber("Right Encoder", driveTrain.getRightEncoder());
		SmartDashboard.putNumber("Left Y Axis", Robot.oi.leftY());
    	SmartDashboard.putNumber("Right Y Axis", Robot.oi.rightY());
//    	SmartDashboard.putNumber("LeftLaunchVel", Robot.fuelLauncher.getLeftEncoderVel());
//    	SmartDashboard.putNumber("RightLaunchVel", Robot.fuelLauncher.getRightEncoderVel());    
//    	SmartDashboard.putNumber("IntakeVel", Robot.fuelIntakeElevator.getEncoderVel());
//    	SmartDashboard.putNumber("LeftLaunchSpeed", Robot.fuelLauncher.getLeftEncoderSpeed());
//    	SmartDashboard.putNumber("RightLaunchSpeed", Robot.fuelLauncher.getRightEncoderSpeed());
    	SmartDashboard.putNumber("driveLeftEncVel", Robot.driveTrain.getLeftEnconderSpeed());
    	SmartDashboard.putNumber("driveRightEncVel", Robot.driveTrain.getRightEnconderSpeed());
//    	SmartDashboard.putNumber("doorPosition", Robot.door.getPosition());
	}
}