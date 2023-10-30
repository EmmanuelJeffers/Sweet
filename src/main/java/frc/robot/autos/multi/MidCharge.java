// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos.multi;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.ChargeStation;
import frc.robot.autos.MidShot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Swerve;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MidCharge extends SequentialCommandGroup {
  /** Creates a new MidPlusMobility. */
  public MidCharge(Intake intake, Pivot pivot, Swerve swerve) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new MidShot(intake),
      new ChargeStation(swerve)
    );
  }
}
