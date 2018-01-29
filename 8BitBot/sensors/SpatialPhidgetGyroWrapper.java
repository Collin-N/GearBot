
package org.usfirst.frc.team6317.robot.sensors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.phidgets.PhidgetException;
import com.phidgets.SpatialEventData;
import com.phidgets.SpatialPhidget;
import com.phidgets.event.SpatialDataEvent;
import com.phidgets.event.SpatialDataListener;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpatialPhidgetGyroWrapper implements SpatialDataListener 
{
	private final SpatialPhidget sp;
	private final int gyroAxes;
	private final double[] integratedGyroAxes;
	private double lastTime = 0;
	private boolean calibrating = false;
	private List<double[]> calibrateData;
	private double[] offsets;
	
	public SpatialPhidgetGyroWrapper(SpatialPhidget spatialPhidget) throws PhidgetException 
	{
		if (!spatialPhidget.isAttached()) throw new IllegalArgumentException("spatial phidget must already be attached");
		this.sp = spatialPhidget;
		this.sp.addSpatialDataListener(this);
		this.gyroAxes = sp.getGyroAxisCount();
		this.integratedGyroAxes = new double[this.gyroAxes];
		this.offsets = new double[this.gyroAxes];
	}
	
	public double getIntegratedAxis(int axis) {
		return this.integratedGyroAxes[axis];
	}
	
	public double getIntegratedXAxis() {
		return this.getIntegratedAxis(0);
	}
	
	public double getIntegratedYAxis() {
		return this.getIntegratedAxis(1);
	}
	
	public double getIntegratedZAxis() {
		return this.getIntegratedAxis(2);
	}
	
	public double getHeading(int axis) {
		double heading = this.integratedGyroAxes[axis] % 360;
		if (heading < 0) heading += 360;
		return heading;
	}
	
	public double getXHeading() {
		return this.getHeading(0);
	}
	
	public double getYHeading() {
		return this.getHeading(1);
	}
	
	public double getZHeading() {
		return this.getHeading(2);
	}

	@Override
	public void data(SpatialDataEvent e) {
		SpatialEventData data = e.getData()[0];
		double time = data.getTime();
		if (this.lastTime == 0) {
			this.lastTime = time;
			return;
		}
		double deltaTime = time - this.lastTime;
		int axes = Math.min(this.gyroAxes, data.getAngularRate().length);
		if (!calibrating) {
			for (int i = 0; i < axes; ++i) {
				this.integratedGyroAxes[i] += (data.getAngularRate()[i] - this.offsets[i]) * deltaTime;
			}
		} else {
			double[] cData = new double[axes + 1];
			cData[0] = deltaTime;
			for (int i = 1; i < cData.length; ++i) {
				cData[i] = data.getAngularRate()[i - 1];
			}
			this.calibrateData.add(cData);
		}
		this.lastTime = time;
	}
	
	public void calibrate() {
		this.calibrateData = new ArrayList<>();
		this.calibrating = true;
	}
	
	public void endCalibrate() {
		if (!calibrating) return;
		this.calibrating = false;
		this.sp.removeSpatialDataListener(this);
		double time = 0;
		for (int i = 0; i < this.gyroAxes; ++i)
			this.offsets[i] = 0;
		for (double[] cData : this.calibrateData)
			time += cData[0];
		for (double[] cData : this.calibrateData)
			for (int i = 1; i < cData.length; ++i)
				this.offsets[i - 1] += cData[i] * cData[0] / time;
		for (int i = 0; i < this.gyroAxes; ++i)
			this.offsets[i] *= 0.99;
		this.lastTime = 0;
		for (int i = 0; i < this.gyroAxes; ++i)
			this.integratedGyroAxes[i] = 0;
		SmartDashboard.putString("Gyro Offsets", Arrays.toString(this.offsets));
		this.sp.addSpatialDataListener(this);
	}
	
	public boolean isCalibrating() {
		return this.calibrating;
	}
}
