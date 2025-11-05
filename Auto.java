package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDFController {
  //Gains 
  public double kP, kI, kD, KF;

  //Limits
  public double iMin = -1.0, iMax = 1.0;     // integral clamp
  public double outMin = -1.0, outMax = 1.0;
