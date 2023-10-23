// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.util.LEDStrip;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.Intake.IntakeConstants.IntakeIDs;

public class Intake extends SubsystemBase {

  private CANSparkMax intakeMotor;  

  private final double INTAKE_SPEED = 0.3;
  private final double SLOW_SHOT = 0.3;
  private final double FAST_SHOT = 0.4;

  private LEDStrip leftLEDs;

  /** Creates a new Intake. */
  public Intake() {
     intakeMotor = new CANSparkMax(IntakeIDs.intakeID, MotorType.kBrushless);
     leftLEDs = new LEDStrip(0, 100);

     intakeMotor.setIdleMode(IdleMode.kCoast);
  }

  public void intake() { 
    intakeMotor.set(-INTAKE_SPEED); 
    leftLEDs.set(0, 255, 0);
  }
  public void slowtake() { 
    intakeMotor.set(SLOW_SHOT); 
    leftLEDs.set(255, 0, 0);
  }
  public void fasttake() { 
    intakeMotor.set(FAST_SHOT); 
    leftLEDs.set(255, 0, 0);
  }
  public void outake(double value) { 
    intakeMotor.set(value); 
    leftLEDs.set(255, 0, 0);
  }

  public void noIntake() { intakeMotor.set(0); }

  public void noMovie() {
    intakeMotor.set(0);
    leftLEDs.blink(0, 0, 0);
  }

  public boolean intakeAuoDone() {
    if (intakeMotor.getEncoder().getPosition() == AutoConstants.midIntakeSetpoint) {
      return true;
    }
    return false;
  }

  public boolean outakeAuoDone() {
    if (intakeMotor.getEncoder().getPosition() == -AutoConstants.midIntakeSetpoint) {
      return true;
    }
    return false;
  }

  public void blinkPurple() {
    leftLEDs.blink(106, 13, 173);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}