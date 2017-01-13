package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	TalonSRX leftMotor1;
	TalonSRX leftMotor2;
	TalonSRX rightMotor1;
	TalonSRX rightMotor2;
	
	RobotDrive drive;
	
	public DriveTrain() {
		super();
		leftMotor1 = new TalonSRX(RobotMap.driveTrainLeft1);
		leftMotor2 = new TalonSRX(RobotMap.driveTrainLeft2);
		rightMotor1 = new TalonSRX(RobotMap.driveTrainRight1);
		rightMotor2 = new TalonSRX(RobotMap.driveTrainRight2);
		
		drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDrive());
    }
    
    public void driveForward(double speedLeft, double speedRight) {
    	drive.tankDrive(speedLeft, speedRight);
    }
    
//    public void leftDrive(double speed) {
//    	leftMotor1.set(speed);
//    	leftMotor2.set(speed);
//
//    }
//    
//    public void rightDrive(double speed) {
//    	rightMotor1.set(speed);
//    	rightMotor2.set(speed);
//    }
    
    
}

