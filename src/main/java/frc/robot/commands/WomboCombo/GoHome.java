// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WomboCombo;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivot;

public class GoHome extends CommandBase {

  private final Pivot pivot;

  /** Creates a new GoHome. */
  public GoHome(Pivot pivot) { 
    this.pivot = pivot;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pivot.pivotHome();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pivot.noPivot();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pivot.isHome();
  }
}
