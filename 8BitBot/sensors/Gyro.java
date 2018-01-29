package org.usfirst.frc.team6317.robot.sensors;

import com.phidgets.PhidgetException;
import com.phidgets.SpatialPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro extends SampleRobot {

	    private RobotDrive myRobot; // robot drive system
	    private SpatialPhidgetGyroWrapper gyro;

	    double Kp = 0.03;

	    public Gyro() 
	    {
	        myRobot = new RobotDrive(1,2);  // Drive train jaguars on PWM 1 and 2
	        myRobot.setExpiration(0.1);
			SmartDashboard.putBoolean("Phidget Attached", false);
	        try {
				final SpatialPhidget sp = new SpatialPhidget();
				sp.addAttachListener(new AttachListener() {
					@Override
					public void attached(AttachEvent e) {
						try {
							gyro = new SpatialPhidgetGyroWrapper(sp);
							sp.removeAttachListener(this);
							SmartDashboard.putBoolean("Phidget Attached", true);
						} catch (PhidgetException e1) {
							e1.printStackTrace();
						}
					}
				});
				sp.openAny();
			} catch (PhidgetException e) {
				e.printStackTrace();
			}
	    }

	    public void autonomous() {
	        while (isAutonomous()) {
	        	double angle = gyro.getZHeading();

		        SmartDashboard.putNumber("Gyro angle", angle);
	            
	            myRobot.drive(-1.0, -angle*Kp); // drive towards heading 0
	            Timer.delay(0.004);
	        }
	        myRobot.drive(0.0, 0.0);
	  }
	}
//new test