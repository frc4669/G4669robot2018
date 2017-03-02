package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.Launch;
import org.usfirst.frc.team4669.robot.commands.LaunchAndIntake;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FuelLauncher extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon launchMotorLeft;
	private CANTalon launchMotorRight;
	private CANTalon feederMotorLeft;
	private CANTalon feederMotorRight;

	public FuelLauncher() {
		super();

		launchMotorLeft = new CANTalon(RobotMap.launchMotorLeft);

		launchMotorLeft.enable();
		launchMotorLeft.reverseSensor(false);
		launchMotorLeft.reverseOutput(false);
		launchMotorLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		launchMotorLeft.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
		launchMotorLeft.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
		launchMotorLeft.configNominalOutputVoltage(+0.0f, -0.0f);
		launchMotorLeft.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 */
		launchMotorLeft.setProfile(0);
		launchMotorLeft.setF(0.027271);
		launchMotorLeft.setP(0.110);
		launchMotorLeft.setI(0.00011); 
		launchMotorLeft.setD(1.1);

		launchMotorRight = new CANTalon(RobotMap.launchMotorRight);

		launchMotorRight.enable();
		launchMotorRight.reverseSensor(false);
		launchMotorRight.reverseOutput(true);
		launchMotorRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		launchMotorRight.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
		launchMotorRight.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
		launchMotorRight.configNominalOutputVoltage(+0.0f, -0.0f);
		launchMotorRight.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 */
		launchMotorRight.setProfile(0);
		launchMotorRight.setF(0.027271);
		launchMotorRight.setP(0.110);
		launchMotorRight.setI(0.00011); 
		launchMotorRight.setD(1.1);

		feederMotorLeft = new CANTalon(RobotMap.feederMotorLeft);

		//Motor setup
		feederMotorLeft.enable();
		feederMotorLeft.reverseSensor(false);
		feederMotorLeft.reverseOutput(false);
		feederMotorLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		feederMotorLeft.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
		feederMotorLeft.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot

		//set the peak and nominal outputs, 12V means full
		feederMotorLeft.configNominalOutputVoltage(+0.0f, -0.0f);
		feederMotorLeft.configPeakOutputVoltage(+12.0f, -12.0f);

		//set closed loop gains in slot0
		feederMotorLeft.setProfile(0);
		feederMotorLeft.setF(0.027271);
		feederMotorLeft.setP(0.110);
		feederMotorLeft.setI(0.00011); 
		feederMotorLeft.setD(1.1);

		feederMotorRight = new CANTalon(RobotMap.feederMotorRight);

		//Motor setup
		feederMotorRight.enable();
		feederMotorRight.reverseSensor(false);
		feederMotorRight.reverseOutput(false);
		feederMotorRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		feederMotorRight.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
		feederMotorRight.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot

		//set the peak and nominal outputs, 12V means full
		feederMotorRight.configNominalOutputVoltage(+0.0f, -0.0f);
		feederMotorRight.configPeakOutputVoltage(+12.0f, -12.0f);

		//set closed loop gains in slot0
		feederMotorRight.setProfile(0);
		feederMotorRight.setF(0.027271);
		feederMotorRight.setP(0.110);
		feederMotorRight.setI(0.00011); 
		feederMotorRight.setD(1.1);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new LaunchAndIntake());
	}

	public void launch() {
		/* Speed mode */
		double speedTolerance = SmartDashboard.getNumber("ShooterSpeedTolerance", 0);
		double targetSpeed = SmartDashboard.getNumber("LaunchRPM", 0);
		double feederVbus = 0.5;
		launchMotorLeft.changeControlMode(TalonControlMode.Speed);
		launchMotorLeft.set(targetSpeed); /* 1500 RPM in either direction */
//		if (Math.abs(launchMotorLeft.getEncVelocity()/6.8- targetSpeed) < speedTolerance) {
//			feederMotorLeft.changeControlMode(TalonControlMode.PercentVbus);
//			feederMotorLeft.set(feederVbus);
//		}
//		else {
//			feederMotorLeft.set(0);
//		}
//		launchMotorRight.changeControlMode(TalonControlMode.Speed);
//		launchMotorRight.set(-targetSpeed); /* 1500 RPM in either direction */
//		if (Math.abs(launchMotorRight.getEncVelocity()/2.4 +targetSpeed) < speedTolerance) {
//			feederMotorRight.changeControlMode(TalonControlMode.PercentVbus);
//			feederMotorRight.set(feederVbus);
//		}
//		else {
//			feederMotorRight.set(0);
//		}
	}

	public void stop() {
		launchMotorLeft.set(0);
		launchMotorRight.set(0);
	}

	public double getLeftEncoder() {
		return launchMotorLeft.getEncPosition();
	}

	public double getLeftEncoderVel() {
		return launchMotorLeft.getEncVelocity();
	}
	
	public double getRightEncoder() {
		return launchMotorRight.getEncPosition();
	}

	public double getRightEncoderVel() {
		return launchMotorRight.getEncVelocity();
	}

	public void zeroEncoders(){
		launchMotorLeft.setPosition(0);
		launchMotorRight.setPosition(0);
	}

}

