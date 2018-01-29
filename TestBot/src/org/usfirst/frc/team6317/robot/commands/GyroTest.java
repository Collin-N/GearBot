package org.usfirst.frc.team6317.robot.commands;

import org.usfirst.frc.team6317.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroTest extends Command {
    private boolean doCalibrate = true;
    private long endTime;
    
	public GyroTest() {
		super("GyroTest");
		this.requires(Robot.DriveTrain);
	}
	
	@Override
	protected void execute() {
		if (Robot.gyro != null) {
			if (doCalibrate) {
				Robot.gyro.calibrate();
				this.endTime = System.currentTimeMillis() + 5000;
				this.doCalibrate = false;
				return;
			} else if (Robot.gyro.isCalibrating()) {
				if (System.currentTimeMillis() >= endTime) Robot.gyro.endCalibrate();
				return;
			}
			double angle = Robot.gyro.getZHeading();

			SmartDashboard.putNumber("Gyro angle", angle);
		} else {
			SmartDashboard.putNumber("Gyro angle", Double.NaN);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
