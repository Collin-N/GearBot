package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;
import org.usfirst.frc.team6317.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriftCompensatingDriveCommand extends Command {
	private double time, leftSpeed, rightSpeed;
	private long startTime;
	
	public DriftCompensatingDriveCommand(double time, double leftSpeed, double rightSpeed) {
		this.time = time;
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
	}
	
	public DriftCompensatingDriveCommand(double time, double speed) {
		this(time, speed, speed);
	}
	
	@Override
	protected void initialize() {
		Robot.DriveTrain.stop();
		startTime = System.currentTimeMillis();
	}
	
	@Override
	protected void execute() {
		RobotMap.leftDrive1.set(leftSpeed);
		RobotMap.leftDrive2.set(leftSpeed);
		RobotMap.rightDrive1.set(-rightSpeed * 1.178);//
		RobotMap.rightDrive2.set(-rightSpeed * 1.178);//
	}

	@Override
	protected boolean isFinished() {
		return (System.currentTimeMillis() - startTime) / 1000.0 >= time;
	}

	@Override
	protected void end() {
		Robot.DriveTrain.stop();
	}
}
