package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.DriveStraightOnly;
import org.usfirst.frc.team4669.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import com.ctre.*;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.PigeonImu.CalibrationMode;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private static final double kAngleSetpoint = 0.0;
	private static final double kP = 0.005;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon frontLeftMotor;
	private CANTalon rearLeftMotor;
	private CANTalon frontRightMotor;
	private CANTalon rearRightMotor;
	
	private RobotDrive driveTrain;
	
	private Gyro analogGyro;
	
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
		frontLeftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontLeftMotor.configEncoderCodesPerRev(360);
		
		rearLeftMotor.enable();
		rearLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
		rearLeftMotor.setSafetyEnabled(true);
		rearLeftMotor.enableBrakeMode(true);
		rearLeftMotor.reverseOutput(RobotMap.reverseOutputTrain);
		rearLeftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearLeftMotor.configEncoderCodesPerRev(360);
		
		frontRightMotor.enable();
		frontRightMotor.changeControlMode(TalonControlMode.PercentVbus);
		frontRightMotor.setSafetyEnabled(true);
		frontRightMotor.enableBrakeMode(true);
		frontRightMotor.reverseOutput(RobotMap.reverseOutputTrain);
		frontRightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRightMotor.configEncoderCodesPerRev(360);
		
		rearRightMotor.enable();
		rearRightMotor.changeControlMode(TalonControlMode .PercentVbus );
		rearRightMotor.setSafetyEnabled(true);
		rearRightMotor.enableBrakeMode(true);
		rearRightMotor.reverseOutput(RobotMap.reverseOutputTrain);
		rearRightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearRightMotor.configEncoderCodesPerRev(360);
		
		analogGyro = new ADXRS450_Gyro();
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TankDrive());
    }
    
    public void driveForward(double speedLeft, double speedRight) {
    	driveTrain.tankDrive(-1*speedLeft, -1*speedRight, false);
    }
    
    public void drive(double outputMag, double outputCurv) {
    	driveTrain.drive(outputMag, outputCurv);
    }
    
    public void stop() {
    	driveTrain.tankDrive(0, 0);
    }
    
    public void calibrateGyro() {
    	analogGyro.calibrate();
    }
    
    public double getGyroAngle() {
    	return analogGyro.getAngle();
    }
    
    public double calculateTurningValue(double direction) {
    	double turningValue = kAngleSetpoint - analogGyro.getAngle() * kP;
    	turningValue = Math.copySign(turningValue, direction);
    	return turningValue;
    }
    
    public double getLeftEncoder() {
    	return frontLeftMotor.getEncPosition();
    }
    
    public double getRightEncoder() {
    	return frontRightMotor.getEncPosition();
    }
    
    public void zeroEncoders() {
    	frontLeftMotor.setPosition(0);
    	frontRightMotor.setPosition(0);
    }
    
}

