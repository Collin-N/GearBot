package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.OI;
import org.usfirst.frc.team6317.robot.Robot;
import org.usfirst.frc.team6317.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DefaultCommand extends Command {
	
	public DefaultCommand() {
		this.requires(Robot.DriveTrain);
	}
	
	@Override
	protected void initialize() {
		Robot.DriveTrain.stop();
	}

	@Override
	protected void execute() {
		double leftSpeed, rightSpeed;
		leftSpeed = OI.leftStick.getY();
		rightSpeed = OI.rightStick.getY();
		RobotMap.leftDrive1.set(-leftSpeed);
		RobotMap.leftDrive2.set(-leftSpeed);
		RobotMap.rightDrive1.set(rightSpeed);
		RobotMap.rightDrive2.set(rightSpeed);
		Timer.delay(0.01);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}	
}