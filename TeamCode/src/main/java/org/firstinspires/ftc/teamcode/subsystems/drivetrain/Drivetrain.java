package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.firstinspires.ftc.teamcode.util.Pose3d;

public class Drivetrain {
    // internally stored state for sim
    private Pose3d pose;

    // may or may not be worth it to make a `SimulableSubsystem` to provide this for every subsystem?
    public ObjectNode updateSimState(ObjectMapper mapper) {
        final ObjectNode position = mapper.createObjectNode()
                .put("x", pose.getX())
                .put("y", pose.getY())
                .put("z", pose.getZ());
        
        final ObjectNode rotation = mapper.createObjectNode()
                .put("x", pose.getXRot())
                .put("y", pose.getYRot())
                .put("z", pose.getZRot());

        final ObjectNode state = mapper.createObjectNode()
                .put("name", "Drivetrain");

        state.set("position", position);
        state.set("rotation", rotation);

        return state;
    }
}