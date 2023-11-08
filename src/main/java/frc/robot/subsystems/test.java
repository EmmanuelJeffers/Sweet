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
import frc.robot.Constants.Intake.IntakeConstants.PivotConstants;

public class test extends SubsystemBase {

  private CANSparkMax pivotMotor;
  private AbsoluteEncoder pivotEncoder;
  private DigitalInput limitSwitch;
  private DecimalFormat df1 = new DecimalFormat("0.##"); // what is a decimal form and why not to use float

  private PIDController pivotController = new PIDController(PivotConstants.pivotKP, PivotConstants.pivotKI, PivotConstants.pivotkKD);

  /** Creates a new Pivot. */
  public test() {

    pivotMotor = new CANSparkMax(IntakeIDs.pivotID, MotorType.kBrushless);
    pivotEncoder = pivotMotor.getAbsoluteEncoder(com.revrobotics.SparkMaxAbsoluteEncoder.Type.kDutyCycle);
    limitSwitch = new DigitalInput(0);

    pivotMotor.setIdleMode(IdleMode.kBrake);
    pivotEncoder.setZeroOffset(PivotConstants.pivotOffset);
  }

  public void pivotUp() { pivotMotor.set(PivotConstants.pivotSpeed); }
  public void pivotDown() { pivotMotor.set(-PivotConstants.pivotSpeed); }
  public void noPivot() { pivotMotor.set(0); }

  public void pivotToIntake() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), PivotConstants.intakeSetpoint)); }
  public void pivotToHybrid() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), PivotConstants.hybridSetpoint)); }
  public void pivotToMid() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), PivotConstants.midSetpoint)); }
  public void pivotHome() { pivotMotor.set(pivotController.calculate(pivotEncoder.getPosition(), PivotConstants.homeSetpoint)); }

  // TODO: find a better implementation for the boolean methods (switch?)
  public boolean isHome() {
    if (pivotEncoder.getPosition() >= PivotConstants.homeSetpoint) {
      return true;
    } else {
      return false;
    }
  }

  public boolean atIntakeSetpoint() {
    if (pivotEncoder.getPosition() <= PivotConstants.intakeSetpoint) {
      return true;
    } else {
      return false;
    }
  }

  public boolean atHybridSetpoint() {
    if (pivotEncoder.getPosition() <= PivotConstants.hybridSetpoint) {
      return true;
    } else {
      return false;
    }
  }

  public boolean atMidSetpoint() {
    if (pivotEncoder.getPosition() <= PivotConstants.midSetpoint && pivotEncoder.getPosition() > PivotConstants.homeSetpoint) {
      return true;
    } else {
      return false;
    }
  }

  public double getPosition() {
    return Double.valueOf(df1.format(pivotEncoder.getPosition()));
  }

  public boolean getLimit() { return limitSwitch.get(); }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Position", getPosition());
    SmartDashboard.putBoolean("Home?", isHome());
    SmartDashboard.putBoolean("Intake Setpoint?", atIntakeSetpoint());
    SmartDashboard.putBoolean("Hybrid Setpoint?", atHybridSetpoint());
  }
}