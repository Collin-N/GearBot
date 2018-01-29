package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.OI;
import org.usfirst.frc.team6317.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopCommand extends Command {

	@Override
	protected void execute() {
		double leftSpeed, rightSpeed;
		leftSpeed = OI.leftStick.getY();
		rightSpeed = OI.rightStick.getY();
		RobotMap.leftMotorOne.set(-leftSpeed);
		RobotMap.leftMotorTwo.set(-leftSpeed);
		RobotMap.rightMotorTwo.set(rightSpeed);
		RobotMap.rightMotorTwo.set(rightSpeed);
		Timer.delay(0.01);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
