package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

public class DriveTrain extends Subsystem {
  private CANSparkMax _rightFront = new CANSparkMax(RobotMap.right_front_motor_port, MotorType.kBrushless);
  private CANSparkMax _rightRear = new CANSparkMax(RobotMap.right_back_motor_port, MotorType.kBrushless);
  private CANSparkMax _leftFront = new CANSparkMax(RobotMap.left_front_motor_port, MotorType.kBrushless);
  private CANSparkMax _leftRear = new CANSparkMax(RobotMap.left_back_motor_port, MotorType.kBrushless);

  private DifferentialDrive _diffDrive = new DifferentialDrive(_leftFront, _rightFront);
  private double rightGoverned = 0.0;
  private double leftGoverned = 0.0;
  private static final double kRampRate = 0.75; //sec to full speed

  public DriveTrain() {
    _rightFront.setInverted(false);
    _leftFront.setInverted(false);
    _rightRear.setInverted(false);
    _leftRear.setInverted(false);
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

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
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
  
  /**
   * Get current from all the motors.  Order: leftFront, leftRear,
   *  rightFront, rightRear
   * @return array of current [A]
   */
  public double[] getMotorCurrent() {
    double[] current = new double[4];
    current[0] = _leftFront.getOutputCurrent();
    current[1] = _leftRear.getOutputCurrent();
    current[2] = _rightFront.getOutputCurrent();
    current[3] = _rightRear.getOutputCurrent();

    return current;
  }

  /**
   * Get temperature from all the motors.  Order: leftFront, leftRear,
   *  rightFront, rightRear
   * @return array of temp [C]
   */
  public double[] getMotorTemps() {
    double[] temp = new double[4];
    temp[0] = _leftFront.getMotorTemperature();
    temp[1] = _leftRear.getMotorTemperature();
    temp[2] = _rightFront.getMotorTemperature();
    temp[3] = _rightRear.getMotorTemperature();

    return temp;
  }
}