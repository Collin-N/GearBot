package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;
import org.usfirst.frc.team6317.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class BreakInCommand extends Command {
	public BreakInCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.DriveSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		RobotMap.leftMotorOne.set(0.5);
		RobotMap.leftMotorTwo.set(0.5);
		RobotMap.rightMotorOne.set(0.5);
		RobotMap.rightMotorTwo.set(0.5);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
