package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	TalonSRX leftMotor1 = new TalonSRX(RobotMap.driveTrainLeft1);
	TalonSRX leftMotor2 = new TalonSRX(RobotMap.driveTrainLeft2);
	TalonSRX rightMotor1 = new TalonSRX(RobotMap.driveTrainRight1);
	TalonSRX rightMotor2 = new TalonSRX(RobotMap.driveTrainRight2);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveForward(int speed) {
    	leftDrive(speed);
    	rightDrive(speed);
    }
    
    public void leftDrive(int speed) {
    	leftMotor1.set(speed);
    	leftMotor2.set(speed);

    }
    
    public void rightDrive(int speed) {
    	rightMotor1.set(speed);
    	rightMotor2.set(speed);
    }
    
    
}

