
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="TEST ENCODER DRIVE", group="Linear Opmode")

public class testEncoderDrive extends LinearOpMode {

    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;

    public Servo intake;

    public DcMotor linearLift1, linearLift2;

    private static final int MOTOR_TICK_COUNTS = 1120;
    private static final double circumference = 3.141592653589793238462643383 * 4;   // PI * diameter

    private double rotationsNeeded;
    private int encoderDrivingTarget;
    

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

//        intakeServo = hwMap.get(Servo.class, "intakeServo");

        linearLift1 = hardwareMap.get (DcMotor.class, "linearLift1");
        linearLift2 = hardwareMap.get (DcMotor.class, "linearLift2");

        intake = hardwareMap.get (Servo.class, "intake");



        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

//        intakeServo.setDirection(Servo.Direction.FORWARD);

        linearLift1.setDirection(DcMotor.Direction.REVERSE);
        linearLift2.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);


        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        linearLift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        runtime.reset();


        waitForStart();

        linearLiftMotor2Forward(.01, 6);
    }

    public void driveOrReverse(double power) {
        backLeft.setPower(power);
        backRight.setPower(power);
        frontLeft.setPower(power);
        frontRight.setPower(power);
    }

    public void strafeLeft(double power) {
        backLeft.setPower(power);
        backRight.setPower(-power);
        frontLeft.setPower(-power);
        frontRight.setPower(power);
    }

    public void strafeRight(double power) {
        backLeft.setPower(-power);
        backRight.setPower(power);
        frontLeft.setPower(power);
        frontRight.setPower(-power);
    }

    public void rotateClockwise(double power) {
        backLeft.setPower(power);
        backRight.setPower(-power);
        frontLeft.setPower(power);
        frontRight.setPower(-power);
    }

    public void rotateCounterClockwise(double power) {
        backLeft.setPower(-power);
        backRight.setPower(power);
        frontLeft.setPower(-power);
        frontRight.setPower(power);
    }


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

    public void linearLiftMotor2Forward(double power, int distance) {

        getEncoderDrivingTarget(distance);

        stopAndResetEncoder();

        setTargetPosition(encoderDrivingTarget);

        linearLift2.setPower(power);

        runToPosition();

        busy();

        stopDriving();

        runUsingEncoder();
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

}