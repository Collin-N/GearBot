
package org.usfirst.frc.team6317.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

//The setup for shooting in auto is 80 inches from the 80 x 20 on top of the shooter to the bottom left corner of 
// the low pressure boiler.
//The bottom left corner of the robot to the wall is 13 inches
public class ShootingAuto extends CommandGroup {
	public ShootingAuto() {
		this.addParallel(new KickCommand(), 9);
		this.addSequential(new ShooterCommand(false), 9);
		//													 time, speed (or left speed, right speed)
		this.addSequential(new DriftCompensatingDriveCommand(0.5, -0.25)); //back up
		this.addSequential(new DriftCompensatingDriveCommand(0.6, 1.0, 0.2)); //turn right
		this.addSequential(new DriftCompensatingDriveCommand(0.5, 1.0, -1.0)); //turn right around center
		this.addSequential(new DriftCompensatingDriveCommand(3.75, 0.3)); //drive forward
	}
}
