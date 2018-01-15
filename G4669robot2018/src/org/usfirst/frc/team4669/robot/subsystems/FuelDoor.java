//package org.usfirst.frc.team4669.robot.subsystems;
//
//import org.usfirst.frc.team4669.robot.RobotMap;
//import org.usfirst.frc.team4669.robot.commands.OpenAndCloseDoor;
//
//import com.ctre.CANTalon;
//import com.ctre.CANTalon.TalonControlMode;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//
///**
// *
// */
//public class FuelDoor extends Subsystem {
//	
//	private CANTalon doorMotor; //change motor to specific motor
//
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//	
//	public FuelDoor() {
//		super();
//		doorMotor = new CANTalon(RobotMap.doorMotor);
//
////		doorMotor.enable();
//		doorMotor.reverseSensor(false);
//		doorMotor.configEncoderCodesPerRev(4096); // if using FeedbackDevice.QuadEncoder
//		doorMotor.setPosition(0.0);
//		//launcherMotor.configPotentiometerTurns(XXX);, // if using FeedbackDevice.AnalogEncoder or AnalogPot
//
//
//		/* set the peak and nominal outputs, 12V means full */
//		doorMotor.configNominalOutputVoltage(+0.0f, -0.0f);
//		doorMotor.configPeakOutputVoltage(+12.0f, -12.0f);
//
//		/* set closed loop gains in slot0 */
//
//		doorMotor.setProfile(0);
//		doorMotor.setF(0.027271);
//		doorMotor.setP(0.110);
//		doorMotor.setI(0.00011); 
//		doorMotor.setD(1.1);		
//	}
//
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new OpenAndCloseDoor());
//    }
//    
//    // doorSpeed is negative
//    public void open() {
//    		doorMotor.changeControlMode(TalonControlMode.PercentVbus);
//    		doorMotor.set(RobotMap.doorSpeed);
//    }
//    
//    // doorSpeed is positive
//    public void close(){
//    		doorMotor.changeControlMode(TalonControlMode.PercentVbus);
//    		doorMotor.set(-0.5*RobotMap.doorSpeed);
//    }
//    
//    public boolean isDoorClosed(){
//    	return doorMotor.isRevLimitSwitchClosed();
//    }
//    
//    public boolean isDoorOpen(){
//    	return doorMotor.isFwdLimitSwitchClosed();
//    }
//
//	public void stop() {
//		doorMotor.changeControlMode(TalonControlMode.PercentVbus);
//		doorMotor.set(0);
//		
//	}
//
//	public void zeroDoor() {
//		doorMotor.setPosition(0);
//	}
//	
//	public double getPosition() {
//		return doorMotor.getPosition();
//	}
//    
//}
//
