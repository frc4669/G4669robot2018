package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.Feed;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FuelFeeder extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	//SETUP
	//Variable for feeder motor.
	private CANTalon feederMotor;

	//Initiate feeder motor.
	public FuelFeeder() {
		super();
		feederMotor = new CANTalon(RobotMap.feederMotor);

		//Motor setup
		feederMotor.enable();
		feederMotor.reverseSensor(false);
		feederMotor.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
		feederMotor.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot

		//set the peak and nominal outputs, 12V means full
		feederMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		feederMotor.configPeakOutputVoltage(+12.0f, -12.0f);

		//set closed loop gains in slot0
		feederMotor.setProfile(0);
		feederMotor.setF(0.027271);
		feederMotor.setP(0.110);
		feederMotor.setI(0.00011); 
		feederMotor.setD(1.1);
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Feed());
	}

	//Feeder command
	public void feed() {
		if(Robot.oi.getRightRawButton(RobotMap.feedButton)){
//			double targetSpeed = SmartDashboard.getNumber("FeedRPM", 0);
//			feederMotor.changeControlMode(TalonControlMode.Speed);
//			feederMotor.set(targetSpeed);
			/* Percent voltage mode */
			feederMotor.changeControlMode(TalonControlMode.PercentVbus);
			feederMotor.set(0.30);
		} 
		else {
			/* Percent voltage mode */
			feederMotor.changeControlMode(TalonControlMode.PercentVbus);
			feederMotor.set(0);
		}
	}

	//Get encoder pos.
	public double getEncoder() {
		return feederMotor.getEncPosition();
	}
	
	public double getEncoderVel() {
		return feederMotor.getEncVelocity();
	}

	//Zero encoder
	public void zeroEncoder(){
		feederMotor.setPosition(0);

	}
}

