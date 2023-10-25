package frc.robot;

public class Constants {

    //constants
    public static final double kRatio = 10.71;
    public static final double kDiameter = 3;
    public static final double kP = 0.015;  
    public static final double kI = 0.05;
    public static final double kD = 0.01;

    public static double currentPos = 0;
     //how many times the motor spinns (motor rotation is equvialent to 2048 ticks) to get the wheels to spin once(thanks to the gearbox this is needed).
     public static final double kRevolutionsPerWheel = 2048 * Constants.kRatio; 
     //circumference of the wheels
     public static final double kCircumference = Constants.kDiameter * Math.PI;
     // kCircumference and KRevolutions are equal in the sense that they travel the same distance, one wheel rotation
     public static final double kTicksPerFoot = kRevolutionsPerWheel/kCircumference;
     public static final double kTicksPerInches = kTicksPerFoot/12;
    public static final double kConversion  = 1/2048 * kCircumference * kRatio / 12;

    public static double setpoint = 10;
    public static double errorSum = 0;
    public static double lastTimeStamp = 0;
    public static double errorRate = 0;
    public static double lastError = 0;



    public static class CANid{
        public static final int kBackLeftFX = 1;
    }

    
}
