package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.team5430.math.pid;


public class driveTrain extends SubsystemBase{
    public final static  WPI_TalonFX backLeftMotor = new WPI_TalonFX(Constants.CANid.kBackLeftFX);
    
    PIDController pid = new PIDController(.5, .3, .0);
    

  public void ZeroSetpoint(){
    Constants.setpoint = 0;
    }
  public void RestartSetpoint(){
    Constants.setpoint = 10;
  }
  public void input(double x){
    pid.setSetpoint(0);
    if(x > 0){
    backLeftMotor.set(ControlMode.PercentOutput, Math.sin(x) * Math.sin(x) * .5);
  }else{
    backLeftMotor.set(ControlMode.PercentOutput, Math.sin(x) * Math.sin(-x) * .5);
  }
}
}
