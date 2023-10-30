// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.multi;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Intake.IntakeConstants;
import frc.robot.autos.ChargeStation;
import frc.robot.commands.EjectCube;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Swerve;

public class MidCharge extends SequentialCommandGroup {
  /** Creates a new MidCharge. */
  public MidCharge(Intake intake, Pivot pivot, Swerve swerve) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new EjectCube(intake, IntakeConstants.midtakeSpeed).until(intake::outakeAuoDone),
      new ChargeStation(swerve)
    );
  }
}