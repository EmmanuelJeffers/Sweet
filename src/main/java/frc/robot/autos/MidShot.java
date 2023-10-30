// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Intake.IntakeConstants;
import frc.robot.subsystems.Intake;

public class MidShot extends CommandBase {

  private final Intake intake;

  /** Creates a new MidShot. */
  public MidShot(Intake intake) {
    this.intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.resetIntakeEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.outake(IntakeConstants.midtakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.notake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return intake.outakeAuoDone();
  }
}
