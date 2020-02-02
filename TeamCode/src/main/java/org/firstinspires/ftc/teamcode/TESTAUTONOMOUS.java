
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.Arrays;

@Autonomous(name="TEST AUTONOMOUS", group="Linear Opmode")

public class TESTAUTONOMOUS extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private double rotationsNeeded;
    private int encoderDrivingTarget;

    private static final int MOTOR_TICK_COUNTS = 1120;
    private static final double circumference = 3.141592653589793238462643383 * 4;   // PI * diameter

    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;



    public Servo intakeXLeft;
    public Servo intakeXRight;

    public Servo intakeYLeft;
    public Servo intakeYRight;

    double intakeXLeftPos;
    double intakeXRightPos;

    double intakeYLeftPos;
    double intakeYRightPos;


    @Override
    public void runOpMode() {
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");


        intakeXLeft = hardwareMap.get(Servo.class, "xLeft");
        intakeXRight = hardwareMap.get(Servo.class, "xRight");

        intakeYLeft = hardwareMap.get(Servo.class, "yLeft");
        intakeYRight = hardwareMap.get(Servo.class, "yRight");

        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);


        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);


        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeYLeftPos = 1;
        intakeYRightPos = 0.168999;

        intakeXLeftPos = .577;
        intakeXRightPos = .571;


        waitForStart();

        intakeXLeft.setPosition(intakeXLeftPos);
        intakeXRight.setPosition(intakeXRightPos);

        intakeYRight.setPosition(intakeYRightPos);
        intakeYLeft.setPosition(intakeYLeftPos);



        driveOrReverseEncoder(.1, 1);


    }

    public void driveOrReverse (double power) {
        backLeft.setPower(-power);
        backRight.setPower(power);
        frontLeft.setPower(-power);
        frontRight.setPower(power);
    }

    public void strafeLeft (double power) {
        backLeft.setPower(power);
        backRight.setPower(-power);
        frontLeft.setPower(-power);
        frontRight.setPower(power);
    }

    public void strafeRight (double power) {
        backLeft.setPower(-power);
        backRight.setPower(power);
        frontRight.setPower(-power);
        frontLeft.setPower(power);
    }

    public void rotateCounterClockwise (double power) {
        frontRight.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(-power);
        frontLeft.setPower(-power);
    }

    public void rotateClockwise (double power) {
        frontRight.setPower(-power);
        backRight.setPower(-power);
        backLeft.setPower(power);
        frontLeft.setPower(power);
    }
//
//    public void driveOrReverse(double power) {
//        backLeft.setPower(-power);
//        backRight.setPower(power);
//        frontLeft.setPower(power);
//        frontRight.setPower(-power);
//    }
//
//    public void strafeLeft(double power) {
//        backLeft.setPower(power);
//        backRight.setPower(-power);
//        frontLeft.setPower(-power);
//        frontRight.setPower(power);
//    }
//
//    public void strafeRight(double power) {
//        backLeft.setPower(-power);
//        backRight.setPower(power);
//        frontLeft.setPower(power);
//        frontRight.setPower(-power);
//    }
//
//    public void rotateClockwise(double power) {
//        backLeft.setPower(power);
//        backRight.setPower(-power);
//        frontLeft.setPower(power);
//        frontRight.setPower(-power);
//    }
//
//    public void rotateCounterClockwise(double power) {
//        backLeft.setPower(-power);
//        backRight.setPower(power);
//        frontLeft.setPower(-power);
//        frontRight.setPower(power);
//    }


    /**
     * Primitive Encoder methods
     */

    public void stopDriving() {
        driveOrReverse(0);
    }

    public void stopAndResetEncoder() {
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setTargetPosition (int distance) {
        backLeft.setTargetPosition(distance);
        backRight.setTargetPosition(distance);
        frontLeft.setTargetPosition(distance);
        frontRight.setTargetPosition(distance);
    }

    public void runToPosition() {
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void busy() {
        while (backLeft.isBusy() && backRight.isBusy() && frontRight.isBusy() && frontLeft.isBusy()) {
        }
    }

    public void runUsingEncoder() {
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void getEncoderDrivingTarget(int distance) {

        encoderDrivingTarget = (int) (distance * 89.17197);
    }


    /**
     * ENCODER METHODS
     */
    public void driveOrReverseEncoder(double power, int distance) {

        getEncoderDrivingTarget(distance);

        stopAndResetEncoder();

        setTargetPosition((int) (distance * 89.17197));

        driveOrReverse(power);

        runToPosition();

        busy();

        stopDriving();

        //runUsingEncoder();  // may need to comment out?
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

    }

}