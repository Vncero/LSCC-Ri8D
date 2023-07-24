package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.util.Pose3d;

public class Drivetrain {

    // motor constants
    private final double TREAD_LENGTH_PER_MOTOR_TICK = 0.01;

    private final DcMotorEx leftMotor;
    private final DcMotorEx rightMotor;

    public Drivetrain(DcMotorEx leftMotor, DcMotorEx rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }

    private double motorTicksToTreadLength(double ticks) {
        return ticks * TREAD_LENGTH_PER_MOTOR_TICK;
    }
}