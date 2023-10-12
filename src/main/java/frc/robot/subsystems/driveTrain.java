package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class driveTrain extends SubsystemBase{
    final static  WPI_TalonFX backRightMotor = new WPI_TalonFX(Constants.CANid.kBackRightFX);
    final static  WPI_TalonFX backLeftMotor = new WPI_TalonFX(Constants.CANid.kBackLeftFX);
    final static  WPI_TalonFX frontRightMotor = new WPI_TalonFX(Constants.CANid.kFrontRightFX);
    final static  WPI_TalonFX frontLeftMotor = new WPI_TalonFX(Constants.CANid.kFrontLeftFX);
       //organizes motor conrollers into groups, left and right respectively
     final static MotorControllerGroup leftGroup = new MotorControllerGroup(backLeftMotor, frontLeftMotor);
     final static MotorControllerGroup rightGroup = new MotorControllerGroup(backRightMotor, frontRightMotor);

     //how many times the motor spinns (motor rotation is equvialent to 2048 ticks) to get the wheels to spin once(thanks to the gearbox this is needed).
     private final double kRevolutionsPerWheel = 2048 * Constants.kRatio;
     //circumference of the wheels
     private final double kCircumference = Constants.kDiameter * Math.PI;
     // kCircumference and KRevolutions are equal in the sense that they travel the same distance, one wheel rotation
     private final double kTicksPerFoot = kRevolutionsPerWheel/kCircumference;

    double setpoint = 10;

    double error = setpoint - Constants.currentPos;

    double dt = Timer.getFPGATimestamp() - lastTimeStamp;

    double errorSum = 0;
    double lastTimeStamp;

    errorSum += error + dt;

    double output = Constants.kP * error + Constants.kI * errorSum;
     
 
     @Override
     public void periodic(){
      Constants.currentPos = backLeftMotor.getSelectedSensorPosition();
      lastTimeStamp = Timer.getFPGATimestamp();

     }
}
