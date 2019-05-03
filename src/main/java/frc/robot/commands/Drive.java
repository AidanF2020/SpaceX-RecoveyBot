package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class Drive extends Command {
	private Joystick js = null;
	private double right = 0.0;
	private double left = 0.0;

	public Drive() {
		requires(Robot.m_drive_train);
	}

	@Override
	protected void initialize() {

		js = Robot.m_oi.getBaseJoystick();
	}

	@Override protected void execute() {

		right = js.getRawAxis(OI.rightStick);
		left = js.getRawAxis(OI.leftStick);
		boolean turbo = js.getRawButton(OI.rightBumper);
		boolean boost = js.getRawButton(OI.leftBumper);
		double motor_gain = 0.5; //50% power
		if (turbo)
			motor_gain = 1; //turbo = full power
		else if (boost)
			motor_gain = 0.75; // 0.75 power

		Robot.m_drive_train.tankDriveByJoystick(left, right, motor_gain);
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