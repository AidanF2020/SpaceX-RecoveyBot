package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.periodictasks.DashboardUpdater;
import frc.robot.subsystems.AngleGrinder;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  public static AngleGrinder m_grinder = new AngleGrinder();
  public static DriveTrain m_drive_train = new DriveTrain();
  public static OI m_oi = new OI();;
  private static DashboardUpdater _dbUpdater = new DashboardUpdater();
  Command m_autonomousCommand;

  @Override
  public void robotInit() {
    //Start updating the dashboard
    _dbUpdater.run();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void testPeriodic() {
  }
}