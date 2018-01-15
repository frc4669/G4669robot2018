//package org.usfirst.frc.team4669.robot.subsystems;
//
//import org.usfirst.frc.team4669.robot.RobotMap;
//import org.usfirst.frc.team4669.robot.commands.ControlWinch;
//
//import com.ctre.CANTalon;
//import com.ctre.CANTalon.TalonControlMode;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//
///**
// *
// */
//public class RopeWinch extends Subsystem {
//
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//	private CANTalon winchMotor;
//
//	//Initiate intake motor.
//	public RopeWinch() {
//		super();
//		winchMotor = new CANTalon(RobotMap.climbMotor);
//
//		//Motor setup
//		winchMotor.enable();
//		//set the peak and nominal outputs, 12V means full
//		winchMotor.configNominalOutputVoltage(+0.0f, -0.0f);
//		winchMotor.configPeakOutputVoltage(+12.0f, -12.0f);
//		
//		winchMotor.changeControlMode(TalonControlMode.PercentVbus);
//
//	}
//	
//	public void run(double percentVbus) {
//		winchMotor.set(percentVbus);
//	}
//	
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new ControlWinch());
//    }
//}
//
