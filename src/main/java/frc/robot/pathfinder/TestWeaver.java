package frc.robot.pathfinder;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

import java.nio.file.Path;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;

public class TestWeaver extends SequentialCommandGroup {

    private final String trajectoryJSON = "D:\\Programming\\FRC\\2023\\Sweet\\PathWeaver\\pathweaver.json"; // for pc
    //private final String trajectoryJSON = "D:\\Programming\\FRC\\2023\\Sweet\\PathWeaver\\pathweaver.json"; // for laptop

    Trajectory trajectory = new Trajectory();

    public TestWeaver(Swerve s_Swerve){
        
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (Exception e) {
            DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, e.getStackTrace());
        }

        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand =
            new SwerveControllerCommand(
                trajectory,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);


        addCommands(
            new InstantCommand(() -> s_Swerve.resetOdometry(trajectory.getInitialPose())),
            swerveControllerCommand
        );
    }
}