// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.driveTrain;

public class RobotContainer {
  public driveTrain m_DriveTrain = new driveTrain();

  public CommandJoystick  joy = new CommandJoystick(0);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    Trigger button = joy.button(2);
    Trigger button2 = joy.button(3);

    button.onTrue(new InstantCommand(m_DriveTrain:: ZeroSetpoint, m_DriveTrain));
    button2.onTrue(new InstantCommand(m_DriveTrain:: RestartSetpoint, m_DriveTrain));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
 
}
