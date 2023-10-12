package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class driveTrain extends SubsystemBase{
    public final static  WPI_TalonFX backLeftMotor = new WPI_TalonFX(Constants.CANid.kBackLeftFX);

  
  public void ZeroSetpoint(){
    Constants.setpoint = 0;
    }
  public void RestartSetpoint(){
    Constants.setpoint = 10;
  }
}
