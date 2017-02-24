package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.Launch;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FuelLauncher extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon launchMotor;

	public FuelLauncher() {
		super();
		launchMotor = new CANTalon(RobotMap.launchMotor);

		launchMotor.enable();
		launchMotor.reverseSensor(false);
		launchMotor.configEncoderCodesPerRev(360); // if using FeedbackDevice.QuadEncoder
		launchMotor.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot


		/* set the peak and nominal outputs, 12V means full */
		launchMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		launchMotor.configPeakOutputVoltage(+12.0f, -12.0f);

		/* set closed loop gains in slot0 */

		launchMotor.setProfile(0);
		launchMotor.setF(0.027271);
		launchMotor.setP(0.110);
		launchMotor.setI(0.00011); 
		launchMotor.setD(1.1);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Launch());
	}

	public void launch() {
			/* Speed mode */
			double targetSpeed = SmartDashboard.getNumber("LaunchRPM", 0);
			launchMotor.changeControlMode(TalonControlMode.Speed);
			launchMotor.set(targetSpeed); /* 1500 RPM in either direction */
	}

	public void stop() {
		/* Percent voltage mode? */
		launchMotor.set(0);
	}

	public double getEncoder() {
		return launchMotor.getEncPosition();
	}

	public double getEncoderVel() {
		return launchMotor.getEncVelocity();
	}

	public void zeroEncoder(){
		launchMotor.setPosition(0);

	}

}

