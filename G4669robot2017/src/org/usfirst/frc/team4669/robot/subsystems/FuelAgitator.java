package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.ControlAgitator;
import org.usfirst.frc.team4669.robot.commands.ControlWinch;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FuelAgitator extends Subsystem {

	 // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon agitatorMotor;

	//Initiate intake motor.
	public FuelAgitator() {
		super();
		agitatorMotor = new CANTalon(RobotMap.agitatorMotor);

		//Motor setup
		agitatorMotor.enable();
		//set the peak and nominal outputs, 12V means full
		agitatorMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		agitatorMotor.configPeakOutputVoltage(+12.0f, -12.0f);
		
		agitatorMotor.changeControlMode(TalonControlMode.PercentVbus);

	}
	
	public void run(double percentVbus) {
		agitatorMotor.set(percentVbus);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ControlAgitator());
    }
}

