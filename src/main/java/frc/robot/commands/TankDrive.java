package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TankDrive extends Command {
	private Joystick js = Robot.m_oi.getBaseJoystick();
	private double right = 0.0;
	private double left = 0.0;

	public TankDrive() {
		requires(Robot.m_drive_train);
	}

	@Override protected void execute() {
		right = js.getRawAxis(OI.rightStick);
		left = js.getRawAxis(OI.leftStick);

		Robot.m_drive_train.tankDriveByJoystick(left, right);
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