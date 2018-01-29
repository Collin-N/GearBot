package org.usfirst.frc.team6317.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static SpeedController rightDrive1 = new Victor(0);		//motor controller is set to pin 0
	public static SpeedController rightDrive2 = new Victor(1);		//motor controller is set to pin 1
	public static SpeedController leftDrive1 = new Victor(2);		//motor controller is set to pin 2
	public static SpeedController leftDrive2 = new Victor(3);		//motor controller is set to pin 3
	public static SpeedController intakeMotorBottom = new Victor(4);//lower intake motor using pin 4
	public static SpeedController intakeMotorTop = new Victor(5);	//upper intake motor using pin 5
	public static SpeedController leftMotorOne = new Victor(2);
	public static SpeedController leftMotorTwo = new Victor(3);
	public static SpeedController rightMotorOne = new Victor(0);
	public static SpeedController rightMotorTwo = new Victor(1);
}
