package org.usfirst.frc.team4669.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDOutputWrapper implements PIDOutput {

	private double output;
	private PIDOutput pidOutput;
	public void setPIDOutput(PIDOutput pidOutput){
		this.pidOutput=pidOutput;
	}

	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		this.output=output;
		pidOutput.pidWrite(output);
	}
	
	public double getOutput(){
		return this.output;
	}

}
