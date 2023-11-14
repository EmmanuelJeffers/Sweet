// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.GoHome;
import frc.robot.commands.GoIntake;
import frc.robot.commands.IntakeCube;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Intake;

public class TestingAutons extends SequentialCommandGroup {
  /** Creates a new TestingAutons. */
  public TestingAutons(Swerve swerve, Pivot pivot, Intake intake) {
    addCommands(
      new Mobility(swerve),
      new GoIntake(pivot),
      new IntakeCube(intake).until(intake::intakeAutoDone),
      new GoHome(pivot)
    );
  }
}
