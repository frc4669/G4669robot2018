package org.usfirst.frc.team4669.robot;

/**
 * @author Ken Fujimoto
 *
 */
public class MotionProfileGenerator {
	
	/**
	 * generate
	 * 
	 * Creates either a triangular or trapezoidal motion profile depending on
	 * distance required and maximum velocity
	 * 
	 * @param acc - acceleration in rotations/sec^2
	 * @param dec - deceleration in rotations/sec^2
	 * @param maxV - maximum velocity in rotations/sec
	 * @param beginPos - begin position in rotations
	 * @param endPos - end position in rotations
	 * @param interval - profile interval in sec
	 * @return array of double[3] with 
	 * 		the position in rotations
	 * 		the velocity in RPM
	 * 		the duration in ms
	 */
	
	public static double[][] generate(double acc, double dec, double maxV, double beginPos, double endPos, double interval) {
		double accDuration = maxV /acc;
		double decDuration = maxV/ dec;
		double accLength = acc * accDuration * accDuration /2;
		double decLength = dec * decDuration * decDuration /2;
		double positionChange = endPos - beginPos;
		double ad = acc * 60;
		double dd = dec * 60;
		double ad2 = acc /2;
		double dd2 = dec /2;
		double intervalMs = interval * 1000;
		double direction;
		double maxVDir;
		if (positionChange < 0) {
			direction = -1;
			ad2 = -ad2;
			dd2 = -dd2;
			ad = -ad;
			dd = -dd;
			maxVDir = -maxV * 60;
		} else {
			direction = 1;
			maxVDir = maxV * 60;
		}
		double maxVDuration; 
		if (positionChange*direction < accLength+decLength) { // triangular profile
			accDuration = Math.sqrt(positionChange*direction * 2 /(acc + acc*acc /dec));
			decDuration = accDuration * acc /dec;
			accLength = acc * accDuration * accDuration /2;
			decLength = dec * decDuration * decDuration /2;
			maxVDuration = 0;
		} else { // trapezoidal profile
			maxVDuration = (positionChange*direction - accLength - decLength) / maxV;
		}	
		double totalTime = accDuration+decDuration+maxVDuration;
		double decTime = totalTime - decDuration;
		int size = (int) Math.floor(totalTime / interval);
		double time = interval;
		double[][] profile = new double[size][3];
		double maxPos = beginPos+accLength * direction;
		for (int x = 0; x < size-1; ++x) {
			if (time < accDuration) {
				profile[x][0] = ad2 * time * time + beginPos;
				profile[x][1] = ad * time;
				profile[x][2] = intervalMs;
			} else if (time > decTime) {
				double t = totalTime - time;
				profile[x][0] = endPos - dd2 * t * t;
				profile[x][1] = dd * t;
				profile[x][2] = intervalMs;
			} else {
				double t = time - accDuration;
				profile[x][0] = maxPos +maxVDir * t;
				profile[x][1] = maxVDir;
				profile[x][2] = intervalMs;
			}
			time += interval;
		}
		profile[size-1][0] = endPos;
		profile[size-1][1] = 0;
		profile[size-1][2] = (totalTime - size * interval )*1000;
		return profile;
	}
	
	public static void mainExample(String[] args) {
		double profile[][] = MotionProfileGenerator.generate(2,1,10,0,6,.01);
		for (int x = 0; x < profile.length; ++x) {
			System.out.println(Double.toString(profile[x][0])+" "+Double.toString(profile[x][1])+" "+Double.toString(profile[x][2]));
		}
	}
}