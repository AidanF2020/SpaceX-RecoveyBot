package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
  public static int baseJoystickPort = 0;
  public static int LXAxis = 0;
  public static int leftStick = 1; //aka LYAxis
  public static int RXAxis = 2;
  public static int rightStick = 3; //aka RYAxis
  public static int rightBumper = 6;
  public static int BButton = 3;
  public Joystick base = new Joystick(baseJoystickPort);
	
	public OI() {
  }
	
	public Joystick getBaseJoystick() {
		return base;
  }
}