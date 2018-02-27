package org.usfirst.frc.team4669.robot.motionProfile;
public class Profile {

	// Position (rotations) Velocity (RPM) Duration (ms)
	double[][]pointsL;
	double[][]pointsR;
	
	
	public Profile(double [][] pointsL,double[][] pointsR){
		this.pointsL = pointsL;
		this.pointsR = pointsR;
	}
	
	public double[][] getLeft(){
		return pointsL;
	}
	public double[][] getRight(){
		return pointsR;
	}
	public int kNumPointsL(){
		return pointsL.length;
	}
	public int kNumPointsR(){
		return pointsR.length;
	}
}
