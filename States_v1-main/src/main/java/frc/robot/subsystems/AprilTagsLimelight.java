package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.Results;

public class AprilTagsLimelight extends SubsystemBase{
    // NetworkTable tableNote = NetworkTableInstance.getDefault().getTable("limelight-notes");
    // NetworkTableEntry tx = tableNote.getEntry("tx");
    // NetworkTableEntry ty = tableNote.getEntry("ty");
    // NetworkTableEntry ta = tableNote.getEntry("ta");
    
    NetworkTable tableApril = NetworkTableInstance.getDefault().getTable("limelight-april");
    NetworkTableEntry atx = tableApril.getEntry("tx");
    NetworkTableEntry aty = tableApril.getEntry("ty");
    NetworkTableEntry ata = tableApril.getEntry("ta");
    double[] botposeBlue = NetworkTableInstance.getDefault().getTable("limelight-april").getEntry("botpose_wpiblue").getDoubleArray(new double[0]);  

    public AprilTagsLimelight(){
        NetworkTableInstance.getDefault().getTable("limelight-april").getEntry("camMode").setNumber(0);
        //NetworkTableEntry  translationVar = tableApril.getEntry("botpose_wpiblue");
        //double translationVar = botposeBlue[0];
    }
    // NetworkTableEntry Atx = tableApril.getEntry("tx");
    // NetworkTableEntry Aty = tableApril.getEntry("ty");
    // NetworkTableEntry Ata = tableApril.getEntry("ta");

    //Results lastResult = LimelightHelpers.getLatestResults("limelight").targetingResults;
    //Results AprillastResult = LimelightHelpers.getLatestResults("limelight-april").targetingResults;

    // public void StopLimelight(){
    //     Acam = true;
    // }

    // public Command StopLimelightCommand(){
    //     return this.runOnce(this::StopLimelight);
    // }

    public Double LimelightTranslation(){
        return botposeBlue[0];
        //return tx.getDouble(0);
    }

    public Double LimelightStrafe(){
        return botposeBlue[1];
    }

    public Double LimelightRotation(){
        return botposeBlue[2];
    }

    
        @Override
        public void periodic(){
            // double x = tx.getDouble(0.0);
            // double y = ty.getDouble(0.0);
            // double area = ta.getDouble(0.0);
            double Ax = atx.getDouble(0.0);
            double Ay = aty.getDouble(0.0);
            double Aarea = ata.getDouble(0.0);
            SmartDashboard.putNumber("LimelightX", Ax);
            SmartDashboard.putNumber("LimelightY", Ay);
            SmartDashboard.putNumber("LimelightArea", Aarea);        
        }

}
