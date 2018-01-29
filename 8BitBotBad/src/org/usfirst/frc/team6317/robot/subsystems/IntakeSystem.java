package org.usfirst.frc.team6317.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6317.robot.*;

public class IntakeSystem extends Subsystem{
	public void initDefaultCommand() {
	}
	
	public void takingIn(){ //makes the intake motors take in fuel
		RobotMap.intakeMotorTop.set(1);
		RobotMap.intakeMotorBottom.set(1);
	}
	
	public void stopIntake(){ //makes the intake motors stop
		RobotMap.intakeMotorTop.set(0);
		RobotMap.intakeMotorBottom.set(0);
	}
	
	public void pushingOut(){ //makes the intake motors push fuel away
		RobotMap.intakeMotorTop.set(-1);
		RobotMap.intakeMotorBottom.set(-1);
	}
}
