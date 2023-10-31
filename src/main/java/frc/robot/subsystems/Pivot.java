// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.text.DecimalFormat;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake.IntakeConstants.IntakeIDs;

public class Pivot extends SubsystemBase {

  private CANSparkMax pivotMotor;
  private AbsoluteEncoder pivotEncoder;
  private DigitalInput limitSwitch;
  private DecimalFormat df1 = new DecimalFormat("0.##");

  private final double PIVOT_SPEED = 0.2;
  private final double INTAKE_SETPOINT = 0.57;
  private final double HOME_SETPOINT = 0.965;
  private final double offset = 0.0106969;

  private PIDController pivotController = new PIDController(1.5, 0, 0);

  /** Creates a new Pivot. */
  public Pivot() {

    pivotMotor = new CANSparkMax(IntakeIDs.pivotID, MotorType.kBrushless);
    pivotEncoder = pivotMotor.getAbsoluteEncoder(com.revrobotics.SparkMaxAbsoluteEncoder.Type.kDutyCycle);
    limitSwitch = new DigitalInput(0);

    pivotMotor.setIdleMode(IdleMode.kBrake);
    pivotEncoder.setZeroOffset(offset);
  }

  public void pivotUp() { pivotMotor.set(PIVOT_SPEED); }
  public void pivotDown() { pivotMotor.set(-PIVOT_SPEED); }
  public void noPivot() { pivotMotor.set(0); }

  public void pivotToIntake() { 
    pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(),INTAKE_SETPOINT)); 
  }

  public void pivotHome() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), HOME_SETPOINT)); }

  public boolean isHome() {
    if (pivotEncoder.getPosition() >= HOME_SETPOINT) {
      return true;
    } else {
      return false;
    }
  }

  public boolean getLimit() { return limitSwitch.get(); }

  public boolean atIntakeSetpoint() {
    if (pivotEncoder.getPosition() <= INTAKE_SETPOINT) {
      return true;
    } else {
      return false;
    }
  }

  public double getPosition() {
    return Double.valueOf(df1.format(pivotEncoder.getPosition()));
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Position", getPosition());
    SmartDashboard.putBoolean("Home?", isHome());
    SmartDashboard.putBoolean("Intake Position?", atIntakeSetpoint());
  }
}