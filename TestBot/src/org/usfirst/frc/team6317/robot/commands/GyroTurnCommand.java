package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;
import org.usfirst.frc.team6317.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroTurnCommand extends Command {
	private final int target, threshold;
	private final double speed;
	private boolean done = false;
	private final boolean left, right;
	
	public GyroTurnCommand(int targetHeading, int threshold, double speed, boolean useLeft, boolean useRight) {
		if (!useLeft && !useRight) throw new IllegalArgumentException("Must use at least one side");
		this.target = targetHeading;
		this.threshold = threshold;
		this.speed = speed;
		this.left = useLeft;
		this.right = useRight;
		this.requires(Robot.DriveTrain);
	}
	
	public GyroTurnCommand(int targetHeading, int threshold, double speed) {
		this(targetHeading, threshold, speed, true, true);
	}

	@Override
	protected void execute() {
		if (Math.abs(target - Robot.gyro.getIntegratedZAxis()) > threshold) {
			double s = Robot.gyro.getIntegratedZAxis() < target ? speed : -speed;
			if (this.left) {
				RobotMap.leftDrive1.set(s);
				RobotMap.leftDrive2.set(s);
			}
			if (this.right) {
				RobotMap.rightDrive1.set(-s);
				RobotMap.rightDrive2.set(-s);
			}
		} else this.done = true;
		SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getIntegratedZAxis());
	}
	
	@Override
	protected boolean isFinished() {
		return this.done;
	}

}
