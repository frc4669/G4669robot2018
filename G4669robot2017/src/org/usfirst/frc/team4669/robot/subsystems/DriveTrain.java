package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.OI;
import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import com.ctre.*;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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
		
		setupLeftMotor(frontLeftMotor);
		setupLeftMotor(rearLeftMotor);
		setupRightMotor(frontRightMotor);
		setupRightMotor(rearRightMotor);
		
		analogGyro = new ADXRS450_Gyro();
		
		
		
	}
	
	public void setPID(double pLeft, double iLeft, double dLeft, double pRight, double iRight, double dRight) {
		frontLeftMotor.setP(pLeft);
		rearLeftMotor.setP(pLeft);
		frontRightMotor.setP(pRight);
		rearRightMotor.setP(pRight);
		frontLeftMotor.setI(iLeft);
		rearLeftMotor.setI(iLeft);
		frontRightMotor.setI(iRight);
		rearRightMotor.setI(iRight);
		frontLeftMotor.setD(dLeft);
		rearLeftMotor.setD(dLeft);
		frontRightMotor.setD(dRight);
		rearRightMotor.setD(dRight);
	}
	
	public void setupLeftMotor(CANTalon talon) {
		talon.enable();
		talon.changeControlMode(TalonControlMode.PercentVbus);
		talon.setSafetyEnabled(false);
		talon.enableBrakeMode(true);
		talon.reverseOutput(!RobotMap.reverseOutputTrain);
		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talon.configEncoderCodesPerRev(360);
		talon.configNominalOutputVoltage(+0.0f, -0.0f);
		talon.configPeakOutputVoltage(+12.0f, -12.0f);
		talon.setProfile(0);
		talon.setF(0.25);
		talon.setP(OI.TALON_P_LEFT.get());
		talon.setI(OI.TALON_I_LEFT.get());
		talon.setD(OI.TALON_D_LEFT.get());
		talon.setMotionMagicCruiseVelocity(300);
		talon.setMotionMagicAcceleration(300);
	}
	
	public void setupRightMotor(CANTalon talon) {
		talon.enable();
		talon.changeControlMode(TalonControlMode.PercentVbus);
		talon.setSafetyEnabled(false);
		talon.enableBrakeMode(true);
		talon.reverseOutput(RobotMap.reverseOutputTrain);
		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talon.configEncoderCodesPerRev(360);
		talon.configNominalOutputVoltage(+0.0f, -0.0f);
		talon.configPeakOutputVoltage(+12.0f, -12.0f);
		talon.setProfile(0);
		talon.setF(0.25);
		talon.setP(OI.TALON_P_RIGHT.get());
		talon.setI(OI.TALON_I_RIGHT.get());
		talon.setD(OI.TALON_D_RIGHT.get());
		talon.setMotionMagicCruiseVelocity(300);
		talon.setMotionMagicAcceleration(300);
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
    
    public void setDrive(double speed, double turnrate) {
    	driveTrain.drive(speed, turnrate);
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
    	turningValue = Math  .copySign(turningValue, direction);
    	return turningValue;
    }
    
    public double getLeftEncoder() {
    	return frontLeftMotor.getEncPosition();
    }
    
    public double getRightEncoder() {
    	return frontRightMotor.getEncPosition();
    }
    
    public double getLeftEnconderVel() {
    	return frontLeftMotor.getEncVelocity();
    }
    
    public double getRightEnconderVel() {
    	return frontRightMotor.getEncVelocity();
    }
    
    public void zeroEncoders() {
    	frontLeftMotor.setPosition(0);
    	frontRightMotor.setPosition(0);
    }
    
    public void changeControlMode(TalonControlMode mode) {
    	frontLeftMotor.changeControlMode(mode);
    	rearLeftMotor.changeControlMode(mode);
    	frontRightMotor.changeControlMode(mode);
    	rearRightMotor.changeControlMode(mode);
    }
    
    public void driveMotionMagic(double targetEncPosition) {
    	changeControlMode(TalonControlMode.MotionMagic);
    	frontLeftMotor.set(-targetEncPosition);
    	rearLeftMotor.set(-targetEncPosition);
    	frontRightMotor.set(targetEncPosition);
    	rearRightMotor.set(targetEncPosition);
    }
	
}
    
    


