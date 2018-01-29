package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.AllianceColor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBallBinAndShoot extends CommandGroup {
	//this auto command class sets the robot backward toward the ball bin
	//robot will drive backward into the ball bin and then shoot 
	//Robot will need to be set at an angle at the start pointing to ball bin
	public AutoBallBinAndShoot(AllianceColor color) {
		
		//													 time, speed (or left speed, right speed)
		//this.addSequential(new DriftCompensatingDriveCommand(2, -0.5)); //back up
		this.addSequential(new DriveEncoderCommand(104, -0.70));
		this.addSequential(new GyroCalibrateCommand(0.5));
		this.addSequential(new GyroTurnCommand(color == AllianceColor.BLUE ? 45 : -45, 1, 0.35, true, false));//first num degrees, second how many degrees it can be off, third speed
		this.addSequential(new WaitCommand(0.5));
		
		
		
		this.addSequential(new DriveEncoderCommand(8, 0.1)); //drive forward for 24" this will need to be adjusted
		this.addParallel(new KickCommand());
		this.addSequential(new ShooterCommand(false));
	}
}
