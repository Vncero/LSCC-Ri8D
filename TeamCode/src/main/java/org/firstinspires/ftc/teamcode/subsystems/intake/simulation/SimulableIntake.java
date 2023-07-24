package org.firstinspires.ftc.teamcode.subsystems.intake.simulation;

import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.intake.Positioner;
import org.firstinspires.ftc.teamcode.subsystems.intake.RollerIntake;
import org.firstinspires.ftc.teamcode.util.Direction;
import org.firstinspires.ftc.teamcode.util.Pose3d;
import org.firstinspires.ftc.teamcode.util.SimOutput;
import org.firstinspires.ftc.teamcode.util.Simulable;

import java.util.ArrayList;
import java.util.List;

public class SimulableIntake extends Intake implements Simulable {

    private final SimulableLinearSlide positionerLSSim;
    private final SimulableRollerIntake rollerIntakeSim;

    private final String pivotName;
    private final Direction slideRotationDirection;

    public SimulableIntake(RollerIntake intake, Positioner positioner,
                           String[] slideStageNames, Direction slideTranslationDirection,
                           String pivotName, Direction slideRotationDirection,
                           String rollerIntakeName, Direction intakeRotationDirection) {
        super(intake, positioner);

        positionerLSSim = new SimulableLinearSlide(Positioner.SLIDE_INITIAL_POSITION_INCHES, slideStageNames, slideTranslationDirection);
        rollerIntakeSim = new SimulableRollerIntake(intake, rollerIntakeName, intakeRotationDirection);
        this.pivotName = pivotName;
        this.slideRotationDirection = slideRotationDirection;
    }

    @Override
    public List<SimOutput> getOutput() {
        List<SimOutput> outputs = new ArrayList<>();

        positionerLSSim.getStagePoses(getSlidePosition()).forEach((name, pos) -> outputs.add(new SimOutput(name, pos)));
        outputs.add(new SimOutput(pivotName, new Pose3d().setRot(slideRotationDirection, getAngle())));

        return outputs;
    }
}
