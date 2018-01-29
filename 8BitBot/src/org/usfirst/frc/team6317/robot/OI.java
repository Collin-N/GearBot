package org.usfirst.frc.team6317.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team6317.robot.commands.*;


public class OI {
	// toggle intake
	// winch drive
	
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick controlStick = new Joystick(2); // mine
	//Button leftTrigger = new JoystickButton(leftStick, 1);
	Button controlTrigger = new JoystickButton(controlStick, 1); // TESTNG: stop solinode
	/*Button topLeft = new JoystickButton(leftStick, 3);
	Button topRight = new JoystickButton(leftStick, 4);
	Button midLeft = new JoystickButton(leftStick, 5);*/
	//Button topLeft = new JoystickButton(controlStick, 3); //bottom left, take in
	Button topRight = new JoystickButton(controlStick, 5); //top right, take out TEST: Forward
	//Button stopIntakeButton = new JoystickButton(controlStick, 2); // bottom right, stop intake
	Button midLeft = new JoystickButton(controlStick, 3); //top left, kick TEST: Backwards
	//Button printDistance = new JoystickButton(controlStick, 6);
	
	public OI() {
		//midLeft.toggleWhenPressed(new KickCommand());
		//controlTrigger.toggleWhenPressed(new ShooterCommand()); //command will be started first time pressed, then stopped next press, then started... etc.
		midLeft.whenPressed(new SolenoidShift(0));
		topRight.whenPressed(new SolenoidShift(1));
		controlTrigger.whenPressed(new SolenoidShift(2));
		
	}
}