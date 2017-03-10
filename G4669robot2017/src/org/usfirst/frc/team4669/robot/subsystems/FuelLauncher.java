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
		launchMotorLeft.setF(0.03);
		launchMotorLeft.setP(0.17);
		launchMotorLeft.setI(0); 
		launchMotorLeft.setD(20);
		launchMotorLeft.changeControlMode(TalonControlMode.Speed);

		launchMotorRight = new CANTalon(RobotMap.launchMotorRight);

		launchMotorRight.enable();
		launchMotorRight.reverseSensor(false);
		launchMotorRight.reverseOutput(false);
		launchMotorRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		launchMotorRight.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
		launchMotorRight.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
		launchMotorRight.configNominalOutputVoltage(+0.0f, -0.0f);
		launchMotorRight.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 */
		launchMotorRight.setProfile(0);
		launchMotorRight.setF(0.03);
		launchMotorRight.setP(0.17);
		launchMotorRight.setI(0); 
		launchMotorRight.setD(20);
		launchMotorRight.changeControlMode(TalonControlMode.Speed);

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
		feederMotorLeft.changeControlMode(TalonControlMode.Speed);
		feederMotorLeft.setProfile(0);
		feederMotorLeft.setF(0);
		feederMotorLeft.setP(0.110);
		feederMotorLeft.setI(0); 
		feederMotorLeft.setD(0);

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
		feederMotorRight.changeControlMode(TalonControlMode.Speed);
		feederMotorRight.setProfile(0);
		feederMotorRight.setF(0);
		feederMotorRight.setP(0.110);
		feederMotorRight.setI(0); 
		feederMotorRight.setD(0);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Launch());
	}
	
	public void startLaunch() {
		double targetSpeed = SmartDashboard.getNumber("LaunchRPM", 0);
		launchMotorLeft.set(-targetSpeed); /* 1500 RPM in either direction */
		launchMotorRight.set(targetSpeed); /* 1500 RPM in either direction */
	}

	public void launch() {
		/* Speed mode */
		double speedTolerance = SmartDashboard.getNumber("ShooterSpeedTolerance", 0);
		double targetSpeed = SmartDashboard.getNumber("LaunchRPM", 0);
		double feederSpeed = 5;
		if (Math.abs(launchMotorLeft.getSpeed()+ targetSpeed) < speedTolerance) {
			feederMotorLeft.set(feederSpeed);
		}
		else {
			feederMotorLeft.set(0);
		}
		if (Math.abs(launchMotorRight.getSpeed() -targetSpeed) < speedTolerance) {
			feederMotorRight.set(-feederSpeed);
		}
		else {
			feederMotorRight.set(0);
		}
	}

	public void stop() {
		launchMotorLeft.set(0);
		launchMotorRight.set(0);
		feederMotorLeft.set(0);
		feederMotorRight.set(0);
	}

	public double getLeftEncoder() {
		return launchMotorLeft.getEncPosition();
	}

	public double getLeftEncoderVel() {
		return launchMotorLeft.getEncVelocity();
	}
	
	public double getLeftEncoderSpeed() {
		return launchMotorLeft.getSpeed();
	}
	
	public double getRightEncoder() {
		return launchMotorRight.getEncPosition();
	}

	public double getRightEncoderVel() {
		System.out.println(launchMotorRight.getEncVelocity());
		return launchMotorRight.getEncVelocity();
	}
	public double getRightEncoderSpeed() {
		return launchMotorRight.getSpeed();
	}
	public void zeroEncoders(){
		launchMotorLeft.setPosition(0);
		launchMotorRight.setPosition(0);
	}
	
	public double getLeftError() {
		return launchMotorLeft.getError();
	}

}

