package org.usfirst.frc.team4669.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.commands.ServoMove;
import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.Servo;

/**
 *
 */
public class ServoControl extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Servo servo1 = new Servo(1);
	private Servo servo2 = new Servo(2);
	
	private Timer contServo = new Timer();
	
	public ServoControl(){
		super();
		getServo1().setAngle(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ServoMove());
    }
    
    public void buttonControl(){
    	//servo1
    	if(Robot.oi.getServoStick().getRawButton(5)) servoMove(getServo1(), 170); //moves servo to max with 5 push
    	if(Robot.oi.getServoStick().getRawButton(3)) servoMove(getServo1(), 0); //moves servo to min with 3 push
    	
    	//servo2 
    	
    }
    
    //moves servo position to min angle
    public void servoMove(Servo rest, int angle){
		rest.setAngle(angle);
	}
    
    public void thirdSpin(Servo cont){
    	cont.set(1.0);
    	contServo.schedule(new TimerTask() {
    		@Override
    		public void run(){
    			cont.set(0.5);
    		}
    	}, 333);
    }
    
    //moves continuous servo to 120 degree increments.
    //Create timer task to rotate the servo about 120 degrees every 100 ms
	public Servo getServo1() {
		return servo1;
	}

	public void setServo1(Servo servo1) {
		this.servo1 = servo1;
	}

	public Servo getServo2() {
		return servo2;
	}

	public void setServo2(Servo servo2) {
		this.servo2 = servo2;
	}

   
}
