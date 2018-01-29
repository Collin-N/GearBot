
package org.usfirst.frc.team6317.robot;

import org.usfirst.frc.team6317.robot.commands.AutoBallBinAndShoot;
import org.usfirst.frc.team6317.robot.commands.BaselineAuto;
import org.usfirst.frc.team6317.robot.commands.GyroTest;
import org.usfirst.frc.team6317.robot.commands.ShootingAuto;
import org.usfirst.frc.team6317.robot.commands.ShootingAutoRed;
import org.usfirst.frc.team6317.robot.sensors.SpatialPhidgetGyroWrapper;
import org.usfirst.frc.team6317.robot.subsystems.*;

import com.phidgets.PhidgetException;
import com.phidgets.SpatialPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
    Command autonomousCommand;
    
	//initializes subsystems
	public static final DriveTrain DriveTrain = new DriveTrain();
	public static final AirSystem AirSystem = new AirSystem();
	public static final IntakeSystem IntakeSystem = new IntakeSystem();
	public static final WinchSystem WinchSystem = new WinchSystem();
	public static final Shifter Shifter = new Shifter();
	public static final Shooter Shooter = new Shooter();
	public static final Distance DistanceU = new Distance();
	public static final Kicker Kicker = new Kicker();
	public static OI oi;
	public static SpatialPhidgetGyroWrapper gyro;
	
	@SuppressWarnings("rawtypes")
	public SendableChooser autoChooser;
	//the following sends information to the smartdash board 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void robotInit() {
    	try {
			final SpatialPhidget sp = new SpatialPhidget();
			sp.addAttachListener(new AttachListener() {
				@Override
				public void attached(AttachEvent e) {
					try {
						gyro = new SpatialPhidgetGyroWrapper(sp);
						sp.removeAttachListener(this);
					} catch (PhidgetException e1) {
						e1.printStackTrace();
					}
				}
			});
			sp.openAny();
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
    	
    	CameraServer.getInstance().startAutomaticCapture();
		oi = new OI();
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Baseline Auto and Gear", new BaselineAuto());
		autoChooser.addObject("Shooting Auto -Blue", new ShootingAuto());
		autoChooser.addObject("Shooting Auto -Red", new ShootingAutoRed());
		autoChooser.addObject("Gyro Test", new GyroTest());
		autoChooser.addObject("Bin and Shoot - BLUE", new AutoBallBinAndShoot(AllianceColor.BLUE));
		autoChooser.addObject("Bin and Shoot - RED", new AutoBallBinAndShoot(AllianceColor.RED));
		SmartDashboard.putData("Auto Mode", autoChooser);
    }
	
    public void disabledInit(){
    	Scheduler.getInstance().run();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	DriveTrain.enc.reset();
		autonomousCommand = (Command) autoChooser.getSelected();
		System.out.println(autonomousCommand.getName());
		autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
		SmartDashboard.putNumber("LeftEncoder", Robot.DriveTrain.enc.getDistance());
    }

    public void teleopInit() {
		//This makes sure that the autonomous stops running when teleop starts running.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
		SmartDashboard.putNumber("LeftEncoder", Robot.DriveTrain.enc.getDistance());
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
