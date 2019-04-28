/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import java.lang.Thread;
import frc.robot.OI;
import frc.robot.RobotMap;


public class Grind extends Command {

  private Joystick js = null;
  
public Grind() {
    requires(Robot.m_grinder);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.print("Initialize Grinder\n");

    js = Robot.m_oi.getBaseJoystick();

    //We think it needs a 20ms on pulse to enable
    Robot.m_grinder.setServo(1.0);
    
    try {
        Thread.sleep(20);

    } catch (Exception e) {
        //TODO: handle exception
    }
    //Off-pulse after 20ms
    Robot.m_grinder.setServo(0);
    System.out.print("Done Initialize Grinder\n");


  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   /*
        double left = js.getRawAxis(OI.leftStick);
        Robot.m_grinder.setServo(left);
        System.out.println("cmd:" + left);
        */

    if (js.getRawButton(OI.BButton)){
        Robot.m_grinder.setServo(1.0);
        //System.out.print("Servo On\n");
    }else{
        Robot.m_grinder.setServo(0);
        //System.out.print("Servo off\n");
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return false;
  }


}
