package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.*;
import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon frontLeftMotor;
	private CANTalon rearLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon rearRightMotor;
	
	private RobotDrive driveTrain;
	
	public DriveTrain() {
		super();
		frontLeftMotor = new CANTalon(RobotMap.driveTrainFrontLeft);
		rearLeftMotor = new CANTalon(RobotMap.driveTrainRearLeft);
		frontRightMotor = new CANTalon(RobotMap.driveTrainFrontRight);
		rearRightMotor = new CANTalon(RobotMap.driveTrainRearRight);
		
		driveTrain = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		
		frontLeftMotor.enable();
		frontLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
		frontLeftMotor.setSafetyEnabled(true);
		frontLeftMotor.enableBrakeMode(true);
		frontLeftMotor.reverseOutput(RobotMap.reverseOutputTrain);
		
		rearLeftMotor.enable();
		rearLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
		rearLeftMotor.setSafetyEnabled(true);
		rearLeftMotor.enableBrakeMode(true);
		rearLeftMotor.reverseOutput(RobotMap.reverseOutputTrain);
		
		frontRightMotor.enable();
		frontRightMotor.changeControlMode(TalonControlMode.PercentVbus);
		frontRightMotor.setSafetyEnabled(true);
		frontRightMotor.enableBrakeMode(true);
		frontRightMotor.reverseOutput(RobotMap.reverseOutputTrain);
		
		rearRightMotor.enable();
		rearRightMotor.changeControlMode(TalonControlMode .PercentVbus );
		rearRightMotor.setSafetyEnabled(true);
		rearRightMotor.enableBrakeMode(true);
		rearRightMotor.reverseOutput(RobotMap.reverseOutputTrain);
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TankDrive());
    }
    
    public void driveForward(double speedLeft, double speedRight) {
    	driveTrain.tankDrive(-1*speedLeft, -1*speedRight, true);
    }
    
    public void stop() {
    	driveTrain.tankDrive(0, 0);
    }
    
    
}

