package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Methods {

    HardwarePushbot hardware;

    public Methods(HardwarePushbot hw) {
        hardware = hw;
    }

    static final int MOTOR_TICK_COUNTS = 579;
    double circumference = 3.14*4;
    double rotationsNeeded;

    int encoderDrivingTarget;

    public void resetEncoder() {
        hardware.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void getEncoderDrivingTarget(int distance) {
        rotationsNeeded = distance/circumference;
        encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS);
    }

    public void driveOrReverse(double power) {
        hardware.backLeft.setPower(power);
        hardware.frontLeft.setPower(power);
        hardware.backRight.setPower(power);
        hardware.frontRight.setPower(power);
    }

    public void turnRight(double power) {
        hardware.backLeft.setPower(-power);
        hardware.frontLeft.setPower(-power);
        hardware.backRight.setPower(power);
        hardware.frontRight.setPower(power);
    }

    public void turnLeft(double power) {
        hardware.backLeft.setPower(power);
        hardware.frontLeft.setPower(power);
        hardware.backRight.setPower(-power);
        hardware.frontRight.setPower(-power);
    }

    public void runToPosition() {
        hardware.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void busy() {
        while(hardware.frontLeft.isBusy() & hardware.frontRight.isBusy() & hardware.backLeft.isBusy() & hardware.backRight.isBusy()) {
        }
    }

    public void stopMotors() {
        hardware.frontRight.setPower(0);
        hardware.backRight.setPower(0);
        hardware.frontLeft.setPower(0);
        hardware.backLeft.setPower(0);
    }




    public void driveForward(int distance, double power) {
        resetEncoder();

        getEncoderDrivingTarget(distance);

        hardware.backRight.setTargetPosition(encoderDrivingTarget);
        hardware.backLeft.setTargetPosition(-encoderDrivingTarget);
        hardware.frontRight.setTargetPosition(encoderDrivingTarget);
        hardware.frontLeft.setTargetPosition(-encoderDrivingTarget);

        driveOrReverse(power);

        runToPosition();

        busy();

        stopMotors();


    }

    public void driveBackward (int distance, double power) {
        resetEncoder();

        getEncoderDrivingTarget(distance);

        hardware.backRight.setTargetPosition(-encoderDrivingTarget);
        hardware.backLeft.setTargetPosition(encoderDrivingTarget);
        hardware.frontRight.setTargetPosition(-encoderDrivingTarget);
        hardware.frontLeft.setTargetPosition(encoderDrivingTarget);

        driveOrReverse(power);

        runToPosition();

        busy();

        stopMotors();

    }

    public void turnCounterClockwise(int distance, double power) {
        resetEncoder();

        getEncoderDrivingTarget(distance);

        hardware.backRight.setTargetPosition(-encoderDrivingTarget);
        hardware.backLeft.setTargetPosition(-encoderDrivingTarget);
        hardware.frontRight.setTargetPosition(-encoderDrivingTarget);
        hardware.frontLeft.setTargetPosition(-encoderDrivingTarget);

        driveOrReverse(power);

        runToPosition();

        busy();

        stopMotors();

    }

    public void turnClockwise (int distance, double power) {
        resetEncoder();

        getEncoderDrivingTarget(distance);

        hardware.backRight.setTargetPosition(encoderDrivingTarget);
        hardware.backLeft.setTargetPosition(encoderDrivingTarget);
        hardware.frontRight.setTargetPosition(encoderDrivingTarget);
        hardware.frontLeft.setTargetPosition(encoderDrivingTarget);

        driveOrReverse(power);

        runToPosition();

        busy();

        stopMotors();

    }

    public void turnCounterClockwise90(double power) {
        resetEncoder();

        getEncoderDrivingTarget(20);

        hardware.backRight.setTargetPosition(-encoderDrivingTarget);
        hardware.backLeft.setTargetPosition(-encoderDrivingTarget);
        hardware.frontRight.setTargetPosition(-encoderDrivingTarget);
        hardware.frontLeft.setTargetPosition(-encoderDrivingTarget);

        driveOrReverse(power);

        runToPosition();

        busy();

        stopMotors();

    }

    public void turnClockwise90(double power) {
        resetEncoder();

        getEncoderDrivingTarget(20);

        hardware.backRight.setTargetPosition(encoderDrivingTarget);
        hardware.backLeft.setTargetPosition(encoderDrivingTarget);
        hardware.frontRight.setTargetPosition(encoderDrivingTarget);
        hardware.frontLeft.setTargetPosition(encoderDrivingTarget);

        driveOrReverse(power);

        runToPosition();

        busy();

        stopMotors();

    }

    public void intake() {
        // go forward slighly
        // grab block
        // backup slightly

        driveForward(4, 0.2);

        // grab block

        driveBackward(6, 0.2);
    }

    public void outtake() {
        // raise block
        driveForward(6, 0.2);
        // drop block
        driveBackward(4, 0.2);
        // put lift back down
    }







}
