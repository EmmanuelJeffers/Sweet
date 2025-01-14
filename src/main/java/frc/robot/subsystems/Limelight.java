// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  public Limelight() {
    LimelightHelpers.setLEDMode_PipelineControl("");
    LimelightHelpers.setLEDMode_ForceOn("");
    LimelightHelpers.setCropWindow("",-1,1,-1,1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("TX", LimelightHelpers.getTX("limelight"));
    SmartDashboard.putNumber("TY", LimelightHelpers.getTY("limelight"));
    SmartDashboard.putNumber("TA", LimelightHelpers.getTA("limelight"));
  }
}
