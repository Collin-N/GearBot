package org.usfirst.frc.team6317.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team6317.robot.commands.SolenoidShift;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick controlStick = new Joystick(2);
	Button topLeft = new JoystickButton(controlStick, 3); //bottom left, take in
	Button topRight = new JoystickButton(controlStick, 5); //top right, take out TEST: Forward
	Button stopIntakeButton = new JoystickButton(controlStick, 2); // bottom right, stop intake
	Button midLeft = new JoystickButton(controlStick, 3); //top left, kick TEST: Backwards
	Button printDistance = new JoystickButton(controlStick, 6);
	Button controlTrigger = new JoystickButton(controlStick, 1); // TESTNG: stop solinode
	public OI() {
		topLeft.whenPressed(new SolenoidShift(0));
		topRight.whenPressed(new SolenoidShift(1));
		controlTrigger.whenPressed(new SolenoidShift(2));
	}
}
