package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.RobotMap;
import org.usfirst.frc.team4669.robot.commands.ClimberControl;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 *
 */
public class Climber extends Subsystem {
	
	private WPI_TalonSRX centerClimber;
	private WPI_TalonSRX rightClimber;
	private WPI_TalonSRX leftClimber;
	public Accelerometer accel;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Climber() {
		super();
		centerClimber = new WPI_TalonSRX(RobotMap.centerClimber);
		rightClimber = new WPI_TalonSRX(RobotMap.rightClimber);
		leftClimber = new WPI_TalonSRX(RobotMap.leftClimber);
		
		accel = new BuiltInAccelerometer(Accelerometer.Range.k4G); 

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClimberControl());
    }
    
    public void climbAll() {
    	centerClimber.set(0.7);
    	rightClimber.set(0.7);
    	leftClimber.set(0.7);
    }
    public void climbCenter(boolean climb){
    	if (climb) centerClimber.set(0.7);
    	else centerClimber.set(0);
    }
    
    public void climbRight(boolean climb){
    	if (climb) rightClimber.set(0.7);
    	else rightClimber.set(0);
    }
    
    public void climbLeft(boolean climb){
    	if (climb) leftClimber.set(0.7);
    	else leftClimber.set(0);
    }
    
    public void unwindLeft(boolean unwind){
    	if (unwind) leftClimber.set(-0.3);
    	else leftClimber.set(0);
    }
    
    public void unwindRight(boolean unwind){
    	if (unwind) rightClimber.set(-0.3);
    	else rightClimber.set(0);
    }
    
    public void unwindCenter(boolean unwind){
    	if (unwind) centerClimber.set(-0.3);
    	else centerClimber.set(0);
    }
    
    public void windLeft(boolean unwind){
    	if (unwind) leftClimber.set(0.3);
    	else leftClimber.set(0);
    }
    
    public void windRight(boolean unwind){
    	if (unwind) rightClimber.set(0.3);
    	else rightClimber.set(0);
    }
    
    public void windCenter(boolean unwind){
    	if (unwind) centerClimber.set(0.3);
    	else centerClimber.set(0);
    }
    
    public double getAccelX(){
    	return accel.getX();
    }
    
    public double getAccelY(){
    	return accel.getY();
    }
    
    public double getAccelZ(){
    	return accel.getZ();
    }
    
    public void stop() {
    	centerClimber.set(0);
    	rightClimber.set(0);
    	leftClimber.set(0);
    }
}

