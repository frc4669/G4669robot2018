
package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.DoNothing;
import org.usfirst.frc.team4669.robot.commands.DriveForward;
import org.usfirst.frc.team4669.robot.commands.LeftGearLiftAuto;
import org.usfirst.frc.team4669.robot.commands.RightGearLiftAuto;
import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4669.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4669.robot.subsystems.FuelDoor;
import org.usfirst.frc.team4669.robot.subsystems.FuelFeeder;
import org.usfirst.frc.team4669.robot.subsystems.FuelIntake;
import org.usfirst.frc.team4669.robot.subsystems.FuelLauncher;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static DriveTrain driveTrain = new DriveTrain();
	public static FuelLauncher fuelLauncher = new FuelLauncher();
	public static FuelIntake fuelIntake = new FuelIntake();
	public static FuelFeeder fuelFeeder = new FuelFeeder();
	public static FuelDoor door = new FuelDoor();
	public static OI oi;
	public static F310 f310;

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
		chooser.addObject("Center Gear Lift", new DriveForward(80));
		chooser.addObject("Left Gear Lift", new LeftGearLiftAuto());
		chooser.addObject("Right Gear Lift", new RightGearLiftAuto());
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
		LiveWindow.run();
	}

	public void updateSmartDashboard() {
//		turn.setDegree(SmartDashboard.getNumber("TurnDegrees", 0));
    	SmartDashboard.putNumber("Gyro", driveTrain.getGyroAngle());
    	SmartDashboard.putNumber("Left Enocder", driveTrain.getLeftEncoder());
    	SmartDashboard.putNumber("Right Encoder", driveTrain.getRightEncoder());
		SmartDashboard.putNumber("Left Y Axis", Robot.oi.leftY());
    	SmartDashboard.putNumber("Right Y Axis", Robot.oi.rightY());
    	SmartDashboard.putNumber("LaunchVel", Robot.fuelLauncher.getEncoderVel());
    	SmartDashboard.putNumber("IntakeVel", Robot.fuelIntake.getEncoderVel());
    	SmartDashboard.putNumber("driveEncVel", Robot.driveTrain.getEnconderVel());
	}
}
