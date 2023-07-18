package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.Robot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kong.unirest.Unirest;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;

public class CommandRobot extends Robot {
    private final boolean simEnabled;

    private Drivetrain drivetrain;

    public CommandRobot(boolean simEnabled) {
        this.simEnabled = simEnabled;

        if (simEnabled) {
            this.drivetrain = new Drivetrain();
        }
    }

    /**
     * Takes current state of subsystems and POSTs them to sim
     */
    public void updateSim() {
        if (simEnabled) {
            final ObjectMapper mapper = new ObjectMapper();

            ObjectNode body = mapper.createObjectNode();

            body.putArray("data")
                    .add(drivetrain.updateSimState(mapper));
            // etc.

            Unirest.post("http://localhost:1000/postdata")
                    .header("Content-Type", "application/json")
                    .body(body.toPrettyString());
        }
    }
}