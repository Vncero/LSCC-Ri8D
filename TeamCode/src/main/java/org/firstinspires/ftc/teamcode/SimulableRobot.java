package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.Robot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qualcomm.robotcore.eventloop.EventLoopManager;
import kong.unirest.Unirest;
import org.firstinspires.ftc.teamcode.util.SimOutput;
import org.firstinspires.ftc.teamcode.util.Simulable;

import java.util.ArrayList;
import java.util.List;

public abstract class SimulableRobot extends Robot {

    private final String simOutputURL;
    private final List<Simulable> simulables = new ArrayList<>();

    public SimulableRobot(String simOutputURL) {
        this.simOutputURL = simOutputURL;
    }

    public void registerSimulable(Simulable simulable) {
        this.simulables.add(simulable);
    }

    public void updateSim() {
            final ObjectMapper mapper = new ObjectMapper();

            ObjectNode body = mapper.createObjectNode();

            ArrayNode data = body.putArray("data");
            for (Simulable sim : simulables) {
                for (SimOutput output : sim.getOutput()) {
                    data.add(output.toObjectNode());
                }
            }


            Unirest.post(simOutputURL)
                    .header("Content-Type", "application/json")
                    .body(body.toPrettyString());
    }
}
