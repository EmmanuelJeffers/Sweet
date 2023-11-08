// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.fasterxml.jackson.core.io.OutputDecorator;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.util.LEDStrip;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.Intake.IntakeConstants;
import frc.robot.Constants.Intake.IntakeConstants.IntakeIDs;

public class Intake extends SubsystemBase{
    private CANSparkMax intakeMotor;
    private LEDStrip led;
    private Joystick joy;
    public Intake(){
        intakeMotor = new CANSparkMax(IntakeIDs.intakeID, MotorType.kBrushed);
        led = new LEDStrip(0, 100);
        joy = new Joystick(1);
    }
    public void intake() { 
        intakeMotor.set(-IntakeConstants.intakeSpeed); 
        led.set(255,192,200);
      }
      public void outake() { 
        intakeMotor.set(IntakeConstants.outtakeSpeed); 
        led.set(100, 100, 100);
      }

      public boolean intakeAuoDone() {
        if 
        (
            intakeMotor.getEncoder().getPosition() == -AutoConstants.midIntakeSetpoint
            )
     {
          return true;
        }
//lol
        return false; }
    
      public boolean outakeAuoDone() {
        if (intakeMotor.getEncoder().getPosition() >= AutoConstants.midIntakeSetpoint) {
          return true;
        }
        return false;
      }
    
      public void resetIntakeEncoder() {
        intakeMotor.getEncoder().setPosition(0);
      }

      
      if (joy.getButton(0)){
        intake();
      } else;
      if (joy.getButton(3)){
        outake();
      } else;

      @Override //wtf
      public void periodic() {   //im not sure if these two are the once to override and why is it red
        intake();
        outake();
        SmartDashboard.putBoolean(intakeAuoDone(), true); //wtf
        SmartDashboard.putBoolean(outakeAuoDone(), true);
    }
}