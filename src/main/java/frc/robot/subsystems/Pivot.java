// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pivot extends SubsystemBase {

  private CANSparkMax pivotMotor;
  private AbsoluteEncoder pivotEncoder;

  private final double PIVOT_SPEED = 0.15;

  private PIDController pivotController = new PIDController(0, 0, 0);

  /** Creates a new Pivot. */
  public Pivot() {

    pivotMotor = new CANSparkMax(3, MotorType.kBrushless);
    pivotEncoder = pivotMotor.getAbsoluteEncoder(com.revrobotics.SparkMaxAbsoluteEncoder.Type.kDutyCycle);

    pivotMotor.setIdleMode(IdleMode.kBrake);
  }

  public void pivotUp() { pivotMotor.set(PIVOT_SPEED); }
  public void pivotDown() { pivotMotor.set(-PIVOT_SPEED); }
  public void noPivot() { pivotMotor.set(0); }

  public double getPosition() {
    return pivotEncoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
