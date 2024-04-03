package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.AprilTagsLimelight;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Swerve;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;


public class TeleopAprilTags extends Command {    
    private Swerve s_Swerve;
    private AprilTagsLimelight s_AprilTagsLimelight;    
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;
    //private BooleanSupplier limelightTurnOn;

    // private PIDController RotationPID = new PIDController(0.006, 0, 0.008);
    private PIDController RotationPID = new PIDController(0.0055, 0, 0.000);//4
    // private PIDController TranslationPID = new PIDController(0.0065, 0, 0);
    // private PIDController StrafePID = new PIDController(0.0065, 0, 0);

    public TeleopAprilTags(Swerve s_Swerve, AprilTagsLimelight s_AprilTagsLimelight, DoubleSupplier translationSup, DoubleSupplier strafeSup, DoubleSupplier rotationSup, BooleanSupplier robotCentricSup) {
        this.s_Swerve = s_Swerve;
        this.s_AprilTagsLimelight = s_AprilTagsLimelight;
        addRequirements(s_Swerve);
        addRequirements(s_AprilTagsLimelight);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
        //this.limelightTurnOn = limelightTurnOn;

        RotationPID.setTolerance(1);
        RotationPID.setSetpoint(0);
        // TranslationPID.setSetpoint(0);
        // StrafePID.setSetpoint(0);
    }

    @Override
    public void execute() {
        /* Get Values, Deadband*/

        double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.stickDeadband);
        double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.stickDeadband);
        // double translationVal = TranslationPID.calculate(s_AprilTagsLimelight.LimelightTranslation());
        // double strafeVal = StrafePID.calculate(s_AprilTagsLimelight.LimelightStrafe());
        //double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.stickDeadband);
        //double rotationVal = -1*Math.signum(s_Limelight.LimelightX())*Math.pow(Math.abs(s_Limelight.LimelightX()/-120), 2);
        double rotationVal = RotationPID.calculate(s_AprilTagsLimelight.LimelightRotation());

        if(RotationPID.atSetpoint() == true){
            rotationVal = 0;
        }

        if(RotationPID.atSetpoint() == true){
            rotationVal = 0;
        }

        // if(TranslationPID.atSetpoint() == true){
        //     translationVal = 0;
        // }

        // if(StrafePID.atSetpoint() == true){
        //     strafeVal = 0;
        // }
        
        /* Drive */
        s_Swerve.drive(
            new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed), 
            rotationVal * Constants.Swerve.maxAngularVelocity, 
            !robotCentricSup.getAsBoolean(), 
            false //true
        );
        SmartDashboard.putNumber("RotationaVal", rotationVal);
        SmartDashboard.putNumber("PID V Error", RotationPID.getVelocityError());
        SmartDashboard.putNumber("PID P Error", RotationPID.getPositionError());
    }
}