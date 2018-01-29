package org.usfirst.frc.team6317.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootingAutoRed extends CommandGroup {
	public ShootingAutoRed() {
		this.addParallel(new KickCommand(), 9);
		this.addSequential(new ShooterCommand(false), 9);
		//													 time, speed (or left speed, right speed)
		this.addSequential(new DriftCompensatingDriveCommand(0.6, -1.0,- 0.2));
		this.addSequential(new DriftCompensatingDriveCommand(1.0, 1.0, 1.0));
		//this.addSequential(new DriftCompensatingDriveCommand(0.5, -0.30, 0.0)); //turn to the left
		//this.addSequential(new DriftCompensatingDriveCommand(0.6, .50, 0.50)); //straight
		//this.addSequential(new DriftCompensatingDriveCommand(0.5, 1.0, -1.0)); //turn right around center
		//this.addSequential(new DriftCompensatingDriveCommand(3.5, 0.3)); //drive forward
	}
}
