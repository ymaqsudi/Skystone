
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * this autonomous is based on motor tick counts and encoder values. this makes it incredibly
 * more precise as opposed to time based counts. using this op mode we can successfully program
 * auto code without having the field, as it can be done using inch values
 */

@Autonomous(name="Encoder Test 1", group="Linear Opmode")

public class Auto2 extends LinearOpMode {


    static final int MOTOR_TICK_COUNTS = 579;
    double circumference = 3.14*4;
    double rotationsNeeded;

    int encoderDrivingTarget;



    HardwarePushbot hardware = new HardwarePushbot();
    Methods robot = new Methods(hardware);

    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);


        waitForStart();

        driveForward(48, 0.5);  // in front of stones

        // intake

        turnCounterClockwise90(0.2);

        driveForward(96, 0.5);  // forward until next to foundation

        turnClockwise90(0.2);           // turn facing foundation

        // outtake

        turnClockwise90(0.2);           // turn back to drive to next set of stones

        driveForward(96, 0.5);  // forward until next to stones

        turnCounterClockwise90(0.2);       // turn to prepare for intake

        //intake

        turnCounterClockwise90(0.2);        // turn to go back to foundation

        driveForward(105, 0.5);     // drive to foundation

        turnClockwise90(0.2);           // turn to prepare for outtake

        //outtake

        turnClockwise90(0.2);           // turn to prepare to park

        driveForward(65, 0.5);      // park
    }


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

        sleep(500);

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

        sleep(500);
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

        sleep(500);
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

        sleep(500);
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

        sleep(500);
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

        sleep(500);
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