package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAllScale extends CommandGroup {
	
	
    public LeftAllScale() {
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
    	addSequential(new ReleaseArms());
    	if(Robot.gameData.length()>0){
	    	if (Robot.gameData.charAt(1)=='L'){
	    		addSequential(new DriveMotionMagic(RobotMap.distToScaleStraight));
	    		addSequential(new TurnTo(90));
	    		addSequential(new AutoIntake());
	    		addSequential(new AutoElevator(RobotMap.elevatorScaleMid));
	    		addSequential(new DriveMotionMagic(10));
	    		addSequential(new ShootCube());
	    		addSequential(new DriveMotionMagic(-10));
	    		addSequential(new AutoElevator(0));
	    	}
	    	else if(Robot.gameData.charAt(1)=='R'){
	    		addSequential(new DriveMotionMagic(RobotMap.distToScaleStraight-80));
	    		addSequential(new TurnTo(90));
	    		addSequential(new DriveMotionMagic(RobotMap.distHorizontalScales));
	    		addSequential(new TurnTo(0));
	    		addSequential(new AutoIntake());
	    		addSequential(new AutoElevator(RobotMap.elevatorScaleMid));
	    		addSequential(new DriveMotionMagic(80));
	    		addSequential(new ShootCube());
	    		addSequential(new AutoElevator(0));
	    	}
	    	else{
	    		addSequential(new DriveForward());
	    	}
    	}
    }
}
