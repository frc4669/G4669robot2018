package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.ElevatorCommand;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	private WPI_TalonSRX elevatorMotor;
	private DigitalInput forwardLimitSwitch;
	private DigitalInput reverseLimitSwitch;
	
	private int timeout = RobotMap.timeout;
	private int slotIdx = RobotMap.slotIdx;
	private int pidIdx = RobotMap.pidIdx;


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Elevator(){
		super();
		elevatorMotor = new WPI_TalonSRX(RobotMap.elevator);
		
		forwardLimitSwitch = new DigitalInput(RobotMap.forwardLimitSwitch);
		reverseLimitSwitch = new DigitalInput(RobotMap.reverseLimitSwitch);
		
		//Set relevant frame periods to be at least as fast as periodic rate 
		elevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, timeout);
		elevatorMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, timeout);
		
		elevatorMotor.configNominalOutputForward(0, timeout);
		elevatorMotor.configNominalOutputReverse(0, timeout);
		elevatorMotor.configPeakOutputForward(1, timeout);
		elevatorMotor.configPeakOutputReverse(-1, timeout);
		
		//Configuring PID Values
		elevatorMotor.selectProfileSlot(slotIdx,pidIdx);
		elevatorMotor.config_kF(slotIdx,0.977,timeout);
		elevatorMotor.config_kP(slotIdx,1.056,timeout);
		elevatorMotor.config_kI(slotIdx,0.006,timeout);
		elevatorMotor.config_kD(slotIdx,21.12,timeout);
		elevatorMotor.config_IntegralZone(slotIdx, 50, timeout);
		
		//Sets up sensor
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,pidIdx,timeout);
		elevatorMotor.setSelectedSensorPosition(0,pidIdx,timeout);
		elevatorMotor.setSensorPhase(false);
		elevatorMotor.setInverted(false);
		
		elevatorMotor.configMotionCruiseVelocity(RobotMap.elevatorVel, RobotMap.timeout);
		elevatorMotor.configMotionAcceleration(RobotMap.elevatorAccel, RobotMap.timeout);
		
		//Sets current limits
		elevatorMotor.configContinuousCurrentLimit(12, timeout);
		elevatorMotor.configPeakCurrentLimit(15, timeout);
		elevatorMotor.configPeakCurrentDuration(100, timeout);
		elevatorMotor.enableCurrentLimit(true);
		
		//Zero encoders on fwd limit
		elevatorMotor.configSetParameter(ParamEnum.eClearPositionOnLimitF, 1, 0, 0, 10);

	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorCommand());
    }
    
    public void customHeight(double percent)  {
    	elevatorMotor.set(ControlMode.PercentOutput, percent);
    }
    
    public void setHeight(double height)  {
    	elevatorMotor.set(ControlMode.MotionMagic, height);
    }
    
    public double getClosedLoopError(){
    	return elevatorMotor.getClosedLoopError(0);
    }
    
    public void stop()  {
    	elevatorMotor.set(0);
    }
    
    public double getEncoderPos(){
    	return elevatorMotor.getSensorCollection().getQuadraturePosition();
    }
    
    public double getEncoderVel(){
    	return elevatorMotor.getSensorCollection().getQuadratureVelocity();
    }
    
    public double getMotorOutput(){
    	return elevatorMotor.getMotorOutputPercent();
    }
    
    public void zeroEncoders(){
    	elevatorMotor.setSelectedSensorPosition(0,pidIdx,timeout);
    }
    
    public boolean getForwardLimit(){
    	return elevatorMotor.getSensorCollection().isFwdLimitSwitchClosed();
    }
    
    public boolean getReverseLimit(){
    	return elevatorMotor.getSensorCollection().isRevLimitSwitchClosed();
    }
}

