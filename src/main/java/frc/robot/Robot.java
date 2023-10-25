// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.driveTrain;

public class Robot extends TimedRobot {

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    driveTrain.backLeftMotor.setSelectedSensorPosition(0);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("encoder ticks", driveTrain.backLeftMotor.getSelectedSensorPosition() );
  }


  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {

    //resets variable at the start
    driveTrain.backLeftMotor.setSelectedSensorPosition(0);
    Constants.lastTimeStamp = Timer.getFPGATimestamp();
    Constants.lastError = 0;
    Constants.errorRate = 0;

  }

  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("encoder ticks", driveTrain.backLeftMotor.getSelectedSensorPosition() );
    SmartDashboard.putNumber("setpoint", Constants.setpoint);
    SmartDashboard.putNumber("currentpos", Constants.currentPos);
    
    Constants.currentPos = driveTrain.backLeftMotor.getSelectedSensorPosition()/2048;
    //how far robot is from the "setpoint"
    double error = Constants.setpoint - Constants.currentPos;

    //dt = delta T, or in otherwords "change in Time" where delta is "change in", and T is "time"
    double dt = Timer.getFPGATimestamp() - Constants.lastTimeStamp;
    //accumaltion overtime to tweak the output by a tiny bit
    Constants.errorSum += error * dt;
    //the rate that error is decreasing; a slope to adjust output
    double errorRate = (error - Constants.lastError)/ dt;
    //the only thing that is to be changed is the constants kP, kI, kD, though some tweaking may be needed to kConversion
    double mOutput = Constants.kP * error + Constants.kI * Constants.errorSum + Constants.kD * errorRate;

    //ouput to motor 
    driveTrain.backLeftMotor.set(ControlMode.PercentOutput, mOutput);
    //update variables
  
    Constants.lastTimeStamp = Timer.getFPGATimestamp();

    Constants.lastError = error;}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}
//ethaniscool
  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
