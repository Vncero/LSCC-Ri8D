package org.firstinspires.ftc.teamcode;

import android.widget.SimpleAdapter;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.simulation.SimulableDrivetrain;
import org.firstinspires.ftc.teamcode.subsystems.intake.Positioner;
import org.firstinspires.ftc.teamcode.subsystems.intake.RollerIntake;
import org.firstinspires.ftc.teamcode.subsystems.intake.simulation.SimulableIntake;
import org.firstinspires.ftc.teamcode.util.Direction;

public class CommandRobot extends SimulableRobot {

    private final SimulableDrivetrain drivetrain;
    private final SimulableIntake intake;

    public CommandRobot(HardwareMap hwMap) {
        super("http://localhost:1000/postdata");

        this.drivetrain = new SimulableDrivetrain(
                hwMap.get(DcMotorEx.class, "drivetrainLeftMotor"),
                hwMap.get(DcMotorEx.class, "drivetrainRightMotor"),
                "drivetrain");

        this.intake = new SimulableIntake(
                new RollerIntake(hwMap.get(ServoEx.class, "intakePositioner"), hwMap.get(DcMotorEx.class, "intakeMotor")),
                new Positioner(hwMap.get(DcMotorEx.class, "slidePivotMotor"), hwMap.get(DcMotorEx.class, "slideMotor")),
                new String[] {"ls1", "ls2", "ls3", "ls4"}, Direction.Z,
                "lsPivot", Direction.X,
                "intake", Direction.X
        );

        registerSimulable(intake);
    }
}