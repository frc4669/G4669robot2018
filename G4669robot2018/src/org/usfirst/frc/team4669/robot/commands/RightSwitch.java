package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitch extends CommandGroup {

    public RightSwitch() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	if(Robot.gameData.length()>0){
	    	if(Robot.gameData.charAt(0)=='R'){
	    		addSequential(new ReleaseArms());
	    		addSequential(new DriveMotionMagic(RobotMap.distToSwitchSides));
	    		addSequential(new AutoIntake());
	    		addSequential(new AutoElevator(RobotMap.elevatorSwitch));
	    		addSequential(new TurnTo(270));
	    		addSequential(new DriveMotionMagic(29,2));
	    		addSequential(new AutoRelease());
	    		addSequential(new DriveMotionMagic(-29,2));
	    		addSequential(new AutoElevator(0));
	    	} else{
	    		addSequential(new DriveForward());
	    	}
    	}
    }
}
