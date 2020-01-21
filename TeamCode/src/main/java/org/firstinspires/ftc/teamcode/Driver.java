package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Driver {

    HardwarePushbot robot = new HardwarePushbot();

    private static final int MOTOR_TICK_COUNTS = 1120;
    private static final double circumference = 3.141592653589793238462643383 * 4;   // PI * diameter

    private double rotationsNeeded;
    private int encoderDrivingTarget;

    public void driveOrReverse(double power) {
        robot.backLeft.setPower(power);
        robot.backRight.setPower(power);
        robot.frontLeft.setPower(power);
        robot.frontRight.setPower(power);
    }

    public void strafeLeft(double power) {
        robot.backLeft.setPower(power);
        robot.backRight.setPower(-power);
        robot.frontLeft.setPower(-power);
        robot.frontRight.setPower(power);
    }

    public void strafeRight(double power) {
        robot.backLeft.setPower(-power);
        robot.backRight.setPower(power);
        robot.frontLeft.setPower(power);
        robot.frontRight.setPower(-power);
    }

    public void rotateClockwise(double power) {
        robot.backLeft.setPower(power);
        robot.backRight.setPower(-power);
        robot.frontLeft.setPower(power);
        robot.frontRight.setPower(-power);
    }

    public void rotateCounterClockwise(double power) {
        robot.backLeft.setPower(-power);
        robot.backRight.setPower(power);
        robot.frontLeft.setPower(-power);
        robot.frontRight.setPower(power);
    }


    /**
     * Primitive Encoder methods
     */

    public void stopDriving() {
        driveOrReverse(0);
    }

    public void stopAndResetEncoder() {
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setTargetPosition (int distance) {
        robot.backLeft.setTargetPosition(distance);
        robot.backRight.setTargetPosition(distance);
        robot.frontLeft.setTargetPosition(distance);
        robot.frontRight.setTargetPosition(distance);
    }

    public void runToPosition() {
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void busy() {
        while (robot.backLeft.isBusy() && robot.backRight.isBusy() && robot.frontRight.isBusy() && robot.frontLeft.isBusy()) {
        }
    }

    public void runUsingEncoder() {
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void getEncoderDrivingTarget(int distance) {
        rotationsNeeded = distance/circumference;
        encoderDrivingTarget = (int) (rotationsNeeded * MOTOR_TICK_COUNTS);
    }


    /**
     * ENCODER METHODS
     */
    public void driveOrReverseEncoder(double power, int distance) {

        getEncoderDrivingTarget(distance);

        stopAndResetEncoder();

        setTargetPosition(encoderDrivingTarget);

        driveOrReverse(power);

        runToPosition();

        busy();

        stopDriving();

        runUsingEncoder();  // may need to comment out?
    }

    public void strafeLeftEncoder(double power, int distance) {
        getEncoderDrivingTarget(distance);

        stopAndResetEncoder();

        setTargetPosition(encoderDrivingTarget);

        strafeLeft(power);

        runToPosition();

        busy();

        stopDriving();

        runUsingEncoder();
    }

    public void strafeRightEncoder(double power, int distance) {
        getEncoderDrivingTarget(distance);

        stopAndResetEncoder();

        setTargetPosition(encoderDrivingTarget);

        strafeRight(power);

        runToPosition();

        busy();

        stopDriving();

        runUsingEncoder();
    }

    public void rotateClockwiseEncoder(double power, int distance) {
        getEncoderDrivingTarget(distance);

        stopAndResetEncoder();

        setTargetPosition(encoderDrivingTarget);

        rotateClockwise(power);

        runToPosition();

        busy();

        stopDriving();

        runUsingEncoder();
    }

    public void rotateCounterClockwiseEncoder(double power, int distance) {
        getEncoderDrivingTarget(distance);

        stopAndResetEncoder();

        setTargetPosition(encoderDrivingTarget);

        rotateCounterClockwise(power);

        runToPosition();

        busy();

        stopDriving();

        runUsingEncoder();
    }

    public void intake() {
        robot.armLeft.setPosition(.481000000000002);
        robot.armRight.setPosition(.12799999);
    }

    public void outtake() {
        robot.armLeft.setPosition(0.13099999999999999);
        robot.armRight.setPosition(0.478000000000001);
    }

}
