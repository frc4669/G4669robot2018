/**
 * Since this example focuses on Motion Control, lets print everything related to MP in a clean 
 * format.  Expect to see something like......
 * 
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * outputEnable    topBufferRem    topBufferCnt    btmBufferCnt    IsValid     HasUnderrun      IsUnderrun          IsLast         VelOnly         targPos         targVel
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * outputEnable    topBufferRem    topBufferCnt    btmBufferCnt    IsValid     HasUnderrun      IsUnderrun          IsLast         VelOnly         targPos         targVel
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * Hold            2048            0               0               1                                                                               5.0             0.0
 * 
 * ...where the columns are reprinted occasionally so you know whats up.
 * 
 * 
 * 
 */
package org.usfirst.frc.team4669.robot.motionProfile;

import org.usfirst.frc.team4669.robot.Robot;

import com.ctre.phoenix.motion.*;

public class Instrumentation {

	static double timeout = 0;
	static int count = 0;

	private static final String[] _table = {" Dis ", " En  ", "Hold "};

	public static void OnUnderrun() {
		System.out.format("%s\n", "UNDERRUN");
	}

	public static void OnNoProgress() {
		System.out.format("%s\n", "NOPROGRESS");
	}

	static private String StrOutputEnable(SetValueMotionProfile sv) {
		/* convert sv to string equiv */
		if (sv == null)
			return "null";
		if (sv.value > 3)
			return "Inval";
		return _table[sv.value];
	}

	public static void process(MotionProfileStatus statusL, double posL,
			double velL, double headingL, MotionProfileStatus statusR, double posR,
			double velR, double headingR) {
		double now = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();

		if ((now - timeout) > 0.2) {
			timeout = now;
			/* fire a loop every 200ms */

			if (--count <= 0) {
				count = 8;
				/* every 8 loops, print our columns */
				System.out.format("%-9s\t", "side");
				System.out.format("%-9s\t", "outEn");
				System.out.format("%-9s\t", "topCnt");
				System.out.format("%-9s\t", "topRem");
				System.out.format("%-9s\t", "btmCnt");
				System.out.format("%-9s\t", "IsValid");
				System.out.format("%-9s\t", "HasUnder");
				System.out.format("%-9s\t", "IsUnder");
				System.out.format("%-9s\t", "IsLast");
				System.out.format("%-9s\t", "targPos");
				System.out.format("%-9s\t", "readPos");
				System.out.format("%-9s\t", "targVel");
				System.out.format("%-9s\t", "readVel");
				System.out.format("%-9s\t", "sentVlt");
				System.out.format("%-9s\t", "SlotSel0");
				System.out.format("%-9s\t\t", "timeDurMs");
				System.out.format("\n");
			}
			/* every loop, print our values */
			System.out.format("%-9s\t\t", "left");
			System.out.format("%-9s\t\t", StrOutputEnable(statusL.outputEnable));
			System.out.format("%-9s\t\t", statusL.topBufferCnt);
			System.out.format("%-9s\t\t", statusL.topBufferRem);
			System.out.format("%-9s\t\t\t", statusL.btmBufferCnt);
			System.out.format("%-9s\t", (statusL.activePointValid ? "true" : "false"));
			System.out.format("%-9s\t\t", (statusL.hasUnderrun ? "true" : "false"));
			System.out.format("%-9s\t", (statusL.isUnderrun ? "true" : "false"));
			System.out.format("%-9s\t", (statusL.isLast ? "true" : "false"));
			System.out.format("%-9s\t\t", posL);
			System.out.format("%-9s\t\t", Robot.driveTrain.getLeftEncoder());
			System.out.format("%-9s\t\t", velL);
			System.out.format("%-9s\t\t", Robot.driveTrain.getLeftEncoderSpeed());
			System.out.format("%-9s\t", Robot.driveTrain.getLeftCurrent());
			//TODO left side is getting a constant 1.0 output percent despite not seeming to move at all
			//TODO left is receiving a consistently higher voltage than right but doesn't move as much
			System.out.format("%-9s\t\t", statusL.profileSlotSelect);
			System.out.format("%-9s\t\t", statusL.timeDurMs);

			System.out.format("\n");
			
			System.out.format("%-9s\t\t", "right");
			System.out.format("%-9s\t\t", StrOutputEnable(statusR.outputEnable));
			System.out.format("%-9s\t\t", statusR.topBufferCnt);
			System.out.format("%-9s\t\t", statusR.topBufferRem);
			System.out.format("%-9s\t\t\t", statusR.btmBufferCnt);
			System.out.format("%-9s\t", (statusR.activePointValid ? "true" : "false"));
			System.out.format("%-9s\t\t", (statusR.hasUnderrun ? "true" : "false"));
			System.out.format("%-9s\t", (statusR.isUnderrun ? "true" : "false"));
			System.out.format("%-9s\t", (statusR.isLast ? "true" : "false"));
			System.out.format("%-9s\t\t", posR);
			System.out.format("%-9s\t\t", Robot.driveTrain.getRightEncoder());
			System.out.format("%-9s\t\t", velR);
			System.out.format("%-9s\t\t", Robot.driveTrain.getRightEncoderSpeed());
			System.out.format("%-9s\t", Robot.driveTrain.getRightCurrent());
			System.out.format("%-9s\t\t", statusR.profileSlotSelect);
			System.out.format("%-9s\t\t\t", statusR.timeDurMs);
			
			System.out.format("\n\n");
		}
	}
}
