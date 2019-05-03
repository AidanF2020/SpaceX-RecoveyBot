package frc.robot.periodictasks;

import frc.robot.PeriodicTask;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardUpdater extends PeriodicTask{

	public DashboardUpdater() {
		super(100);
	}
	public DashboardUpdater(int dt) {
		super(dt);
	}
	@Override
	public void run() {
		double[] driveCurrent = Robot.m_drive_train.getMotorCurrent();
		SmartDashboard.putNumber("_leftFront Current [A]", driveCurrent[0]);
		SmartDashboard.putNumber("_leftRear Current [A]", driveCurrent[1]);
		SmartDashboard.putNumber("_rightFront Current [A]", driveCurrent[2]);
		SmartDashboard.putNumber("_leftRear Current [A]", driveCurrent[3]);
		
		double[] driveTemp = Robot.m_drive_train.getMotorTemps();
		SmartDashboard.putNumber("_leftFront Temp [C]", driveTemp[0]);
		SmartDashboard.putNumber("_leftRear Temp [C]", driveTemp[1]);
		SmartDashboard.putNumber("_rightFront Temp [C]", driveTemp[2]);
		SmartDashboard.putNumber("_leftRear Temp [C]", driveTemp[3]);
		
	}

}
