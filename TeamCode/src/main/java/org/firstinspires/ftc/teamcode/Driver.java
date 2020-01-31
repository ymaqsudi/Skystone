package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Driver {

    private HardwarePushbot hardware;

    private static final int MOTOR_TICK_COUNTS = 1120;
    private static final double circumference = 3.141592653589793238462643383 * 4;   // PI * diameter

    private double rotationsNeeded;
    private int encoderDrivingTarget;

    public Driver() {
    }

    public void driveOrReverse(double power) {
        hardware.backLeft.setPower(power);
        hardware.backRight.setPower(power);
        hardware.frontLeft.setPower(power);
        hardware.frontRight.setPower(power);
    }

    public void strafeLeft(double power) {
        hardware.backLeft.setPower(power);
        hardware.backRight.setPower(-power);
        hardware.frontLeft.setPower(-power);
        hardware.frontRight.setPower(power);
    }

    public void strafeRight(double power) {
        hardware.backLeft.setPower(-power);
        hardware.backRight.setPower(power);
        hardware.frontLeft.setPower(power);
        hardware.frontRight.setPower(-power);
    }

    public void rotateClockwise(double power) {
        hardware.backLeft.setPower(power);
        hardware.backRight.setPower(-power);
        hardware.frontLeft.setPower(power);
        hardware.frontRight.setPower(-power);
    }

    public void rotateCounterClockwise(double power) {
        hardware.backLeft.setPower(-power);
        hardware.backRight.setPower(power);
        hardware.frontLeft.setPower(-power);
        hardware.frontRight.setPower(power);
    }


    /**
     * Primitive Encoder methods
     */

    public void stopDriving() {
        driveOrReverse(0);
    }

    public void stopAndResetEncoder() {
        hardware.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setTargetPosition (int distance) {
        hardware.backLeft.setTargetPosition(distance);
        hardware.backRight.setTargetPosition(distance);
        hardware.frontLeft.setTargetPosition(distance);
        hardware.frontRight.setTargetPosition(distance);
    }

    public void runToPosition() {
        hardware.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void busy() {
        while (hardware.backLeft.isBusy() && hardware.backRight.isBusy() && hardware.frontRight.isBusy() && hardware.frontLeft.isBusy()) {
        }
    }

    public void runUsingEncoder() {
        hardware.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

    public void rightSkystonePos() {
        strafeLeftEncoder(.01, 6);

        driveOrReverseEncoder(.01, 48);

        //intake();

        driveOrReverseEncoder(-.01, 18);

        strafeLeftEncoder(.01, 96);

        //outtake();
    }

    public void centerSkystonePos() {

        driveOrReverseEncoder(.01, 48);

       // intake();

        driveOrReverseEncoder(-.01, 18);

        strafeLeftEncoder(.01, 96);

        //outtake();
    }

    /*
    public void intake() {
        hardware.armLeft.setPosition(.481000000000002);
        hardware.armRight.setPosition(.12799999);
    }

    public void outtake() {
        hardware.armLeft.setPosition(0.13099999999999999);
        hardware.armRight.setPosition(0.478000000000001);

     */
    }

