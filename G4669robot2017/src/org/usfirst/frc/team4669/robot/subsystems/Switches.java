package org.usfirst.frc.team4669.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;
import org.usfirst.frc.team4669.robot.commands.FuelLoader;

// Subsystem that is for digital input of switches

public class Switches extends Subsystem {
	
	public Switches(){
		super();
	}
	
	//DIO 0
	DigitalInput limitSwitch = new DigitalInput(0);
	Counter counter0 = new Counter(limitSwitch);
	
	public boolean isSwitchSet(){
		return counter0.get() > 0;
	}
	
	public void initializeCounter() {
        counter0.reset();
    }
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new FuelLoader());
    }
	
}
