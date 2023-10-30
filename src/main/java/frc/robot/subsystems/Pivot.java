// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Intake.IntakeConstants.IntakeIDs;

public class Pivot extends SubsystemBase {

  private CANSparkMax pivotMotor;
  private AbsoluteEncoder pivotEncoder;

  private final double PIVOT_SPEED = 0.2;
  private final double INTAKE_SETPOINT = 0.6;
  private final double HOME_SETPOINT = 0.99;
  //private final double offset = 0.0106969;

  private PIDController pivotController = new PIDController(0.2, 0, 0);

  /** Creates a new Pivot. */
  public Pivot() {

    pivotMotor = new CANSparkMax(IntakeIDs.pivotID, MotorType.kBrushless);
    pivotEncoder = pivotMotor.getAbsoluteEncoder(com.revrobotics.SparkMaxAbsoluteEncoder.Type.kDutyCycle);

    pivotMotor.setIdleMode(IdleMode.kBrake);
  }

  public void pivotUp() { pivotMotor.set(PIVOT_SPEED); }
  public void pivotDown() { pivotMotor.set(-PIVOT_SPEED); }
  public void noPivot() { pivotMotor.set(0); }

  public void pivotToIntake() { pivotMotor.set(pivotController.calculate(INTAKE_SETPOINT)); }
  public void pivotHome() { pivotMotor.set(-pivotController.calculate(HOME_SETPOINT)); }

  public boolean isHome() {
    if (pivotEncoder.getPosition() >= HOME_SETPOINT) {
      return true;
    } else {
      return false;
    }
  }

  public boolean atIntakeSetpoint() {
    if (pivotEncoder.getPosition() <= INTAKE_SETPOINT) {
      return true;
    } else {
      return false;
    }
  }

  public double getPosition() {
    return pivotEncoder.getPosition();
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Position", getPosition());
    SmartDashboard.putBoolean("Home?", isHome());
    SmartDashboard.putBoolean("Intake Position?", atIntakeSetpoint());
  }
}