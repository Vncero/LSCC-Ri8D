package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Positioner {

    // ANGLE
    private static final double ANGLE_DEGREE_PER_TICK = 1;
    private static final double ANGLE_ERROR_THRESHOLD_DEGREES = 1; // 1 degree

    // SLIDE
    private static final double SLIDE_INITIAL_POSITION_INCHES = 13.2283;
    private static final double SLIDE_INCHES_PER_TICK = 1;
    private static final double SLIDE_ERROR_THRESHOLD_INCHES = 0.5; // eyeballed

    private final DcMotorEx angleMotor;
    private final DcMotorEx slideMotor;

    public Positioner(DcMotorEx pivotMotor, DcMotorEx slideMotor) {
        this.angleMotor = pivotMotor;
        this.slideMotor = slideMotor;
    }

    public void setupMotors() {
        // angle
        angleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        angleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        angleMotor.setTargetPositionTolerance((int) (ANGLE_ERROR_THRESHOLD_DEGREES / ANGLE_DEGREE_PER_TICK));

        // slide
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setTargetPositionTolerance((int) (SLIDE_ERROR_THRESHOLD_INCHES / SLIDE_INCHES_PER_TICK));
    }

    // ANGLE

    public double getAngle() {
        return angleMotor.getCurrentPosition() * ANGLE_DEGREE_PER_TICK;
    }

    public double getTargetAngle() {
        return angleMotor.getTargetPosition() * ANGLE_DEGREE_PER_TICK;
    }

    public boolean angleAtPosition() {
        return Math.abs(angleMotor.getTargetPosition() - angleMotor.getCurrentPosition()) * ANGLE_DEGREE_PER_TICK <= ANGLE_ERROR_THRESHOLD_DEGREES;
    }

    public void setTargetAngle(double targetAngle) {
        angleMotor.setTargetPosition((int) (targetAngle / ANGLE_DEGREE_PER_TICK));
    }

    // SLIDE
    public double getSlidePosition() {
        return slideMotor.getCurrentPosition() * SLIDE_INCHES_PER_TICK + SLIDE_INITIAL_POSITION_INCHES;
    }

    public double getMinimumSlidePosition() {
        return SLIDE_INITIAL_POSITION_INCHES;
    }

    public double getTargetSlidePosition() {
        return slideMotor.getTargetPosition() * SLIDE_INCHES_PER_TICK + SLIDE_INITIAL_POSITION_INCHES;
    }

    public boolean slideAtPosition() {
        return Math.abs(slideMotor.getTargetPosition() - slideMotor.getCurrentPosition()) * SLIDE_INCHES_PER_TICK <= SLIDE_ERROR_THRESHOLD_INCHES;
    }

    /**
     * sets slide position in inches
     * */
    public void setSlidePosition(double position) {
        slideMotor.setTargetPosition((int) (Math.max(position - SLIDE_INITIAL_POSITION_INCHES, 0) / SLIDE_INCHES_PER_TICK));
    }


}
