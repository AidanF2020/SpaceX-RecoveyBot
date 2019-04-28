/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.commands.Grind;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class AngleGrinder extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //commanding Phoenix ICE2 HV120 as a Servo
  private Servo m_grinderESC = new Servo(RobotMap.PWM_GRINDER);
  private double m_lastCmd = 0; //memory state for cmd ramping
  private static double CMD_INC = 0.02; // max positive change per cycle


  public void AngleGrinder() {

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Grind());
  }

  public void setServo(double output) {
    //Limit the rise rate to CMD_INC/cycle
    double max_cmd = m_lastCmd + CMD_INC;
    if (output > max_cmd) {
      output = max_cmd;
    }
    //update the motor controller
    m_grinderESC.set(output);
    //remember the output for the next pass
    m_lastCmd = output;

  }

  public void stopGrinding() {
    m_grinderESC.set(0);
  }
}
