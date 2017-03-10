package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FuelDoor extends Subsystem {
	
	private CANTalon doorMotor; //change motor to specific motor
	private int direction = 1;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public FuelDoor() {
		super();
		doorMotor = new CANTalon(RobotMap.doorMotor);

		doorMotor.enable();
		doorMotor.reverseSensor(false);
		doorMotor.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
		doorMotor.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot


		/* set the peak and nominal outputs, 12V means full */
		doorMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		doorMotor.configPeakOutputVoltage(+12.0f, -12.0f);

		/* set closed loop gains in slot0 */

		doorMotor.setProfile(0);
		doorMotor.setF(0.027271);
		doorMotor.setP(0.110);
		doorMotor.setI(0.00011); 
		doorMotor.setD(1.1);		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // doorSpeed is negative
    public void open() {
    		doorMotor.changeControlMode(TalonControlMode.Speed);
    		doorMotor.set(-1 * RobotMap.doorSpeed);
    }
    
    // doorSpeed is positive
    public void close(){
    		doorMotor.changeControlMode(TalonControlMode.Speed);
    		doorMotor.set(RobotMap.doorSpeed);
    }
    
    public void openAndClose(){
    	if (direction == 1 && doorMotor.isFwdLimitSwitchClosed()){
    		open();
    	}
    	if (direction ==  -1 && doorMotor.isRevLimitSwitchClosed()){
    		close();
    	}
    }
    
    public boolean isForwardLimitSwitchClosed(){
    	return doorMotor.isFwdLimitSwitchClosed();
    }
    
    public boolean isReverseLimitSwitchClosed(){
    	return doorMotor.isRevLimitSwitchClosed();
    }

	public void stop() {
		doorMotor.changeControlMode(TalonControlMode.Speed);
		doorMotor.set(0);
		
	}
    
}

