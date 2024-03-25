package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.Results;

public class Limelight extends SubsystemBase{
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    
    NetworkTable AprilTable = NetworkTableInstance.getDefault().getTable("limelight-april");

    public Limelight(){
        NetworkTableInstance.getDefault().getTable("limelight-april").getEntry("camMode").setNumber(1);   

    }
    // NetworkTableEntry Atx = AprilTable.getEntry("tx");
    // NetworkTableEntry Aty = AprilTable.getEntry("ty");
    // NetworkTableEntry Ata = AprilTable.getEntry("ta");

    //Results lastResult = LimelightHelpers.getLatestResults("limelight").targetingResults;
    //Results AprillastResult = LimelightHelpers.getLatestResults("limelight-april").targetingResults;

    // public void StopLimelight(){
    //     Acam = true;
    // }

    // public Command StopLimelightCommand(){
    //     return this.runOnce(this::StopLimelight);
    // }

    public Double LimelightX(){
        return tx.getDouble(0);
    }

    
        @Override
        public void periodic(){
            double x = tx.getDouble(0.0);
            double y = ty.getDouble(0.0);
            double area = ta.getDouble(0.0);
            // double Ax = Atx.getDouble(0.0);
            // double Ay = Aty.getDouble(0.0);
            // double Aarea = Ata.getDouble(0.0);
            SmartDashboard.putNumber("LimelightX", x);
            SmartDashboard.putNumber("LimelightY", y);
            SmartDashboard.putNumber("LimelightArea", area);        
        }

}
