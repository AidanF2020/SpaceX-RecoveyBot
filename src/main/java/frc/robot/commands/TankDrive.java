package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TankDrive extends Command {
	private Joystick js = null;
	private double right = 0.0;
	private double left = 0.0;
	private boolean boost = false;

	public TankDrive() {
		requires(Robot.m_drive_train);
	}

	@Override
	protected void initialize() {

		js = Robot.m_oi.getBaseJoystick();
	}

	@Override protected void execute() {

		right = js.getRawAxis(OI.rightStick);
		left = js.getRawAxis(OI.leftStick);
		boost = js.getRawButton(OI.rightBumper);

		Robot.m_drive_train.tankDriveByJoystick(left, right, boost);
	}

	@Override protected boolean isFinished() {
		return false;
	}

	@Override protected void end() {
		Robot.m_drive_train.drivetrainStop();
	}

	@Override protected void interrupted() {
		Robot.m_drive_train.drivetrainStop();
	}
}