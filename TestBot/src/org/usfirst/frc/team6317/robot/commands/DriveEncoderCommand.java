package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;
import org.usfirst.frc.team6317.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveEncoderCommand extends Command {
	private double inches;
	private double speed;
	
	public DriveEncoderCommand(double inches, double speed) {
		this.requires(Robot.DriveTrain);
		this.inches = inches;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		Robot.DriveTrain.enc.setReverseDirection(true);
		Robot.DriveTrain.enc.setDistancePerPulse(128 / 2743.0);//6 * Math.PI / (1440.0 * (5/6.0))); // 6in diameter wheels, 1440 pulses per revolution 
		Robot.DriveTrain.enc.reset();
	}
	
	@Override
	protected void execute() {
		//Robot.DriveTrain.forward(speed);
		RobotMap.leftDrive1.set(speed);
		RobotMap.leftDrive2.set(speed);
		RobotMap.rightDrive1.set(-speed * 0.85);//this sets the callibration for drift compensation for encoder auto
		RobotMap.rightDrive2.set(-speed * 0.85);//this sets the callibration for drift compensation for encoder auto
		SmartDashboard.putNumber("LeftEncoder", Robot.DriveTrain.enc.getDistance());
	}

	@Override
	protected boolean isFinished() {
		if (speed < 0)
			return Robot.DriveTrain.enc.getDistance() <= -inches;
		return Robot.DriveTrain.enc.getDistance() >= inches;
	}
	
	@Override
	protected void end() {
		Robot.DriveTrain.stop();
	}

}
