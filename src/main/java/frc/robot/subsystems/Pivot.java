// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.naming.LimitExceededException;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake.IntakeConstants.PivotConstants;

/** Add your docs here. */
public  class Pivot extends SubsystemBase {
    private CANSparkMax pivotMotor;
    private AbsoluteEncoder pivotEncoder;
    private DigitalInput limitSwitch;
    private Joystick joy;

    private PIDController pivotController = new PIDController (PivotConstants.pivotKP, PivotConstants.pivotKI, PivotConstants.pivotkKD);
    public Pivot(){
         pivotMotor = new CANSparkMax(IntakeID.pivotID, MotorType.kBrushless);
         pivotEncoder = pivotMotor.getAbsoluteEncoder(com.revrobotics.SparkMaxAbsoluteEncoder.Type.kDutyCycle);
         limitSwitch = new DigitalInput(0);
         pivotMotor.setIdleMode(IdleMode.kBrake);
         pivotEncoder.setZeroOffset(PivotConstants.pivotOffset);
    }
    public void pivotUp()
        { pivotMotor.set(PivotConstants.pivotSpeed);
    }
    public void pivotDown(){
        pivotMotor.set(-PivotConstants.pivotSpeed);
    }
    public void noPivot() {
        pivotMotor.set(0);
    }
    public void pivotToIntake(){
        pivotMotor.set(pivotMotor.set(pivotController.calculate));
    }
    public void pivotToHybrid (){
        pivotMotor.set(pivotMotor.set(pivotController.calculate(0)));
    }
    public void pivotToMid (){ 
        pivotMotor.set (pivotMotor.set());
    }
    public void pivotToIntake() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), PivotConstants.intakeSetpoint)); }
    public void pivotToHybrid() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), PivotConstants.hybridSetpoint)); }
    public void pivotToMid() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), PivotConstants.midSetpoint)); }
    public void pivotHome() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), PivotConstants.homeSetpoint)); }

    public void pivotToHybrid(){
        if (joy.getRawButton(2)){
            pivotToHybrid();
        }else;
    } 
    public void pivotToMid(){
        if (joy.getRawButton(3)){
            pivotToMid();
        }else;
    }
    public void pivotHome(){
        if (joy.getRawButton(4)){
            pivotHome();
        } else;
    }
    public void pivotToIntake(){
        if (joy.getRawButton(5)){
            pivotToIntake();
        }else;
    }
    public double getPosition() {
        return Double.valueOf(df1.format(pivotEncoder.getPosition()));
      }
    
      public boolean getLimit() { return limitSwitch.get(); }
    
    
    
      @Override
      public void periodic() {
        SmartDashboard.putNumber("Intake Position", getPosition());
        SmartDashboard.putBoolean("Home?", isHome());
        SmartDashboard.putBoolean("Intake Setpoint?", atIntakeSetpoint());
        SmartDashboard.putBoolean("Hybrid Setpoint?", atHybridSetpoint());
      }



    /*
     * PUT THE ENCODER VALUES AND POSITIONS
     */// hi eman

}