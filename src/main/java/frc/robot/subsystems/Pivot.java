// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake.IntakeConstants.PivotConstants;
import frc.robot.Constants.Intake.IntakeConstants.IntakeIDs;

/** Add your docs here. */
public  class Pivot extends SubsystemBase {
    private CANSparkMax pivotMotor;

    public Pivot(){
         pivotMotor = new CANSparkMax(IntakeIDs.pivotID, MotorType.kBrushless);
         pivotMotor.setIdleMode(IdleMode.kBrake);
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
        
    /*
     * PUT THE ENCODER VALUES AND POSITIONS
     */// hi eman
     @Override
      public void periodic() {
      }
}