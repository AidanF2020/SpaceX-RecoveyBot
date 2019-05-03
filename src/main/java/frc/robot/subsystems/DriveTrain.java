package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

public class DriveTrain extends Subsystem {
  private CANSparkMax _rightFront = new CANSparkMax(RobotMap.right_front_motor_port, MotorType.kBrushless);
  private CANSparkMax _rightRear = new CANSparkMax(RobotMap.right_back_motor_port, MotorType.kBrushless);
  private CANSparkMax _leftFront = new CANSparkMax(RobotMap.left_front_motor_port, MotorType.kBrushless);
  private CANSparkMax _leftRear = new CANSparkMax(RobotMap.left_back_motor_port, MotorType.kBrushless);

  private DifferentialDrive _diffDrive = new DifferentialDrive(_leftFront, _rightFront);
  private double rightGoverned = 0.0;
  private double leftGoverned = 0.0;
  private double motor_gain = .5; //TODO: Set Motor Gain
  private static final double kRampRate = 0.75; //sec to full speed

  public DriveTrain() {
    _rightFront.setInverted(false); //TODO: Confirm Inversion
    _leftFront.setInverted(false); //TODO: Confirm Inversion
    _rightRear.setInverted(false); //TODO: Confirm Inversion
    _leftRear.setInverted(false); //TODO: Confirm Inversion
    _rightRear.follow(_rightFront);
    _leftRear.follow(_leftFront);

    _rightFront.setSmartCurrentLimit(60, 80);
    _rightRear.setSmartCurrentLimit(60, 80);
    _leftFront.setSmartCurrentLimit(60, 80);
    _leftRear.setSmartCurrentLimit(60, 80);

    _rightFront.setOpenLoopRampRate(kRampRate);
    _rightRear.setOpenLoopRampRate(kRampRate);
    _leftFront.setOpenLoopRampRate(kRampRate);
    _leftRear.setOpenLoopRampRate(kRampRate);

    _rightFront.setIdleMode(IdleMode.kBrake);
    _rightRear.setIdleMode(IdleMode.kBrake);
    _leftFront.setIdleMode(IdleMode.kBrake);
    _leftRear.setIdleMode(IdleMode.kBrake);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TankDrive());
  }

  public void drivetrainStop() {
    _rightFront.set(0);
    _leftFront.set(0);
  }
  
  public void tankDriveByJoystick(double left, double right, double motor_gain) {
    //NOTE: motor gain should be on (0,1]

    if (left < 0)
      leftGoverned = (left * left) * -motor_gain;
    else 
      leftGoverned = (left * left) * motor_gain;

    if (right < 0)
      rightGoverned = (right * right) * -motor_gain;
    else 
      rightGoverned = (right * right) * motor_gain;

    _diffDrive.tankDrive(leftGoverned, rightGoverned);
	}
}