package org.usfirst.frc.team6317.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Distance extends Subsystem {
	AnalogInput distanceSensor = new AnalogInput(0);
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void getDistance() {		
		// Analog voltage output. Scaling factor is (Vcc/5120) per mm. A 5V supply yields ~0.977mV per millimeter.
		// TODO - use this with the voltage to determine how many millimeters it's seeing
		
		SmartDashboard.putString("DB/String 0", "Distance: " + distanceSensor.getVoltage());
	}

}
