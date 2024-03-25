package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Swerve;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;


public class TeleopLimelight extends Command {    
    private Swerve s_Swerve;
    private Limelight s_Limelight;    
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;
    //private BooleanSupplier limelightTurnOn;

    // private PIDController RotationPID = new PIDController(0.006, 0, 0.008);
    private PIDController RotationPID = new PIDController(0.0065, 0, 0.000);

    public TeleopLimelight(Swerve s_Swerve, Limelight s_Limelight, DoubleSupplier translationSup, DoubleSupplier strafeSup, DoubleSupplier rotationSup, BooleanSupplier robotCentricSup) {
        this.s_Swerve = s_Swerve;
        this.s_Limelight = s_Limelight;
        addRequirements(s_Swerve);
        addRequirements(s_Limelight);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
        //this.limelightTurnOn = limelightTurnOn;

        RotationPID.setTolerance(5);
        RotationPID.setSetpoint(0);
    }

    @Override
    public void execute() {
        /* Get Values, Deadband*/
        double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.stickDeadband);
        double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.stickDeadband);
        //double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.stickDeadband);
        //double rotationVal = -1*Math.signum(s_Limelight.LimelightX())*Math.pow(Math.abs(s_Limelight.LimelightX()/-120), 2);
        double rotationVal = RotationPID.calculate(s_Limelight.LimelightX());



        if(RotationPID.atSetpoint() == true){
            rotationVal = 0;
        }
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