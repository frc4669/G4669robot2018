

package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.Shoot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;


public class FuelLauncher extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon launcherMotor;
	
	public FuelLauncher() {
		super();
		launcherMotor = new CANTalon(RobotMap.singleLaunchMotor);
		
		launcherMotor.enable();
		launcherMotor.reverseSensor(false);
		launcherMotor.configEncoderCodesPerRev(360); // if using FeedbackDevice.QuadEncoder
		launcherMotor.setPosition(0.0);
		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot
		
		
		/* set the peak and nominal outputs, 12V means full */
        launcherMotor.configNominalOutputVoltage(+0.0f, -0.0f);
        launcherMotor.configPeakOutputVoltage(+12.0f, -12.0f);

        /* set closed loop gains in slot0 */

        launcherMotor.setProfile(0);
        launcherMotor.setF(0.027271);
        launcherMotor.setP(0.110);
        launcherMotor.setI(0.00011); 
        launcherMotor.setD(1.1);
        
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Shoot());
    }
    
    public void shoot() {
    	if(Robot.oi.getRawButton(1)){
        	/* Speed mode */
        	double targetSpeed = 11722;
        	launcherMotor.changeControlMode(TalonControlMode.Speed);
        	launcherMotor.set(targetSpeed); /* 1500 RPM in either direction */
//        	_sb.append("\tMode:");
//        	_sb.append(launcherMotor.getControlMode());
//        	/* append more signals to print when in speed mode. */
//            _sb.append("\terr:");
//            _sb.append(launcherMotor.getClosedLoopError());
//            _sb.append("\ttrg:");
//            _sb.append(targetSpeed);
       
    	} else {
        /* Percent voltage mode */
     	launcherMotor.set(0);
    	}
    }
 
    
    public double getEncoder() {
    	return launcherMotor.getEncPosition();
    }
    
    public void zeroEncoder(){
    	launcherMotor.setPosition(0);
    	
    }
    
}

