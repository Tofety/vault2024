package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lift extends SubsystemBase {

    public static Solenoid Left;
    public static Solenoid Right;
    public static Solenoid StopLeft;
    public static Solenoid StopRight;
    public static Compressor compressor;
    public Boolean LongShotFlag = true;
    public Lift(){

    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    Left = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    Right = new Solenoid(PneumaticsModuleType.CTREPCM, 1);

    StopLeft = new Solenoid(PneumaticsModuleType.CTREPCM, 2);
    StopRight = new Solenoid(PneumaticsModuleType.CTREPCM, 3);
    }
    
    public void LiftUp(){
        Left.set(true);
        Right.set(true);
        //StopLeft.set(true);
        //StopRight.set(true);
    }

    public void LiftDown(){
        Left.set(false);
        Right.set(false);
    }

    public void LongShotOut(){
        StopLeft.set(true);
        StopRight.set(true);
        Left.set(true);
        Right.set(true);
    }

    public void LongShotToggle(){
        if(LongShotFlag == true){
            StopLeft.set(true);
            StopRight.set(true);
            Left.set(true);
            Right.set(true);
        }else{
            StopLeft.set(false);
            StopRight.set(false);
            Left.set(false);
            Right.set(false);
        }
        LongShotFlag = !LongShotFlag;
    }

    public Command LongShotToggleCommand(){
        return this.runOnce(this::LongShotToggle);
    }

    public void LongShotIn(){
        StopLeft.set(false);
        StopRight.set(false);
        Left.set(false);
        Right.set(false);
    }
    
    public Command LongShotOutCommand(){
        return this.runOnce(this::LongShotOut);
    }

    public Command LongShotInCommand(){
        return this.runOnce(this::LongShotIn);
    }

    public Command LiftUpCommand(){
        return this.runOnce(this::LiftUp);
    }

    public Command LiftDownCommand(){
        return this.runOnce(this::LiftDown);
    }

        @Override
    public void periodic(){
        SmartDashboard.putBoolean("Left Solenoid", Left.get());
        SmartDashboard.putBoolean("Right Solenoid", Right.get());
    }
}
