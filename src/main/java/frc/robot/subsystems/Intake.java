// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.util.LEDStrip;

public class Intake extends SubsystemBase {

  private CANSparkMax pivotMotor;
  private CANSparkMax intakeMotor;
  private AbsoluteEncoder pivotEncoder;

  private final double PIVOT_SPEED = 0.1;
  private final double INTAKE_SPEED = 0.2;
  private final double SLOW_SHOT = 0.3;
  private final double FAST_SHOT = 0.4;
  private final double power = 0.0;

  private LEDStrip leds;
  /** Creates a new Intake. */
  public Intake() {
     pivotMotor = new CANSparkMax(3, MotorType.kBrushless);
     intakeMotor = new CANSparkMax(4, MotorType.kBrushless);
     pivotEncoder = pivotMotor.getAbsoluteEncoder(com.revrobotics.SparkMaxAbsoluteEncoder.Type.kDutyCycle);
     leds = new LEDStrip(0, 500);

     pivotMotor.setIdleMode(IdleMode.kBrake);
     intakeMotor.setIdleMode(IdleMode.kCoast);
  }

  public void pivotUp() { pivotMotor.set(PIVOT_SPEED); }
  public void pivotDown() { pivotMotor.set(-PIVOT_SPEED); }
  public void noPivot() { pivotMotor.set(0); }

  public void intake() { 
    intakeMotor.set(-INTAKE_SPEED); 
    leds.set(0, 255, 0);
  }
  public void slowtake() { 
    intakeMotor.set(SLOW_SHOT); 
    leds.set(255, 0, 0);
  }
  public void fasttake() { 
    intakeMotor.set(FAST_SHOT); 
    leds.set(255, 0, 0);
  }
  public void outtake(double value) { 
    intakeMotor.set(value); 
    leds.set(255, 0, 0);
  }

  public void noIntake() { intakeMotor.set(0); }

  public void noMovie() {
    pivotMotor.set(0);
    intakeMotor.set(0);
    leds.blink(0, 0, 0);
  }

  public void blinkPurple() {
    leds.blink(106, 13, 173);
  }

  public double getPosition() {
    return pivotEncoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}