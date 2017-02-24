package org.usfirst.frc.team4669.robot.subsystems;
import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.Intake;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FuelIntakeElevator extends Subsystem {
	//SETUP
	//Variable for intake motor.
	private CANTalon intakeMotor;

	//Initiate intake motor.
	public FuelIntakeElevator() {
		super();
		intakeMotor = new CANTalon(RobotMap.intakeMotor);

		//Motor setup
		intakeMotor.enable();
		intakeMotor.reverseSensor(false);
		intakeMotor.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
		intakeMotor.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot

		//set the peak and nominal outputs, 12V means full
		intakeMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		intakeMotor.configPeakOutputVoltage(+12.0f, -12.0f);

		//set closed loop gains in slot0
		intakeMotor.setProfile(0);
		intakeMotor.setF(0.027271);
		intakeMotor.setP(0.110);
		intakeMotor.setI(0.00011); 
		intakeMotor.setD(1.1);
	}

	// METHODS
	//Default command
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Intake());
	}

	//Intake command
	public void intake() {
		if(Robot.oi.getRightRawButton(RobotMap.intakeButton)){
			double targetSpeed = SmartDashboard.getNumber("IntakeRPM", 0);
			intakeMotor.changeControlMode(TalonControlMode.Speed);
			intakeMotor.set(targetSpeed);
		} 
		else {
			/* Percent voltage mode */
			intakeMotor.set(0);
		}
	}

	//Get encoder pos.
	public double getEncoder() {
		return intakeMotor.getEncPosition();
	}

	public double getEncoderVel() {
		return intakeMotor.getEncVelocity();
	}

	//Zero encoder
	public void zeroEncoder(){
		intakeMotor.setPosition(0);

	}
}


