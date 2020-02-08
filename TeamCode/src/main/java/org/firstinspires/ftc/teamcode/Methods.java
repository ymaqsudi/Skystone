package org.firstinspires.ftc.teamcode;

public class Methods {

    HardwarePushbot hardware;

    public Methods(HardwarePushbot hw) {
        hardware = hw;
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

    public void stopDriving() {
        driveOrReverse(0);
    }

    public void leftPower(double leftPower) {
        hardware.backLeft.setPower(leftPower);
        hardware.frontLeft.setPower(leftPower);
    }

    public void rightPower(double rightPower) {
        hardware.backRight.setPower(rightPower);
        hardware.frontRight.setPower(rightPower);
    }
}
