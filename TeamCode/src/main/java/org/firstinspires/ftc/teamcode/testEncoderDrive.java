
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RUN THIS ONE", group="Linear Opmode")

public class testEncoderDrive extends LinearOpMode {

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
    

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

//        intakeServo = hwMap.get(Servo.class, "intakeServo");


        intakeXLeft = hardwareMap.get(Servo.class, "xLeft");
        intakeXRight = hardwareMap.get(Servo.class, "xRight");

        intakeYLeft = hardwareMap.get(Servo.class, "yLeft");
        intakeYRight = hardwareMap.get(Servo.class, "yRight");

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);


        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);


        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();

        runtime.reset();



        waitForStart();

        telemetry.addData("Mode", "Start");


        intakeYLeftPos = 1;

        intakeYRightPos = 0.168999;

        intakeXLeftPos = .577;
        intakeXRightPos = .571;

        intakeYRight.setPosition(intakeYRightPos);
        intakeYLeft.setPosition(intakeYLeftPos);
        intakeXRight.setPosition(intakeXRightPos);
        intakeXLeft.setPosition(intakeXLeftPos);

        sleep(3000);

        driveOrReverse(.25);    // 72 inches forward
        sleep(2000);
        stopDriving();

        intake();
        sleep(500);

        driveOrReverse(-.25);
        sleep(333);

        rotateClockwise(.33);
        sleep(2000);
        stopDriving();

        driveOrReverse(.25);    // 168 inches forward
        sleep(4567);
        stopDriving();

        outtake();
        sleep(500);


//
//        outtake();  // sets arms to outtake "starting" position
//
//        driveOrReverse(.25);   // goes forward for 48 inches
//        sleep(706);
//        stopDriving();
//
//        intake();       // intakes center block
//        sleep(500);
//
//        intakeYLeft.setPosition(0.89599);
//        intakeYRight.setPosition(0.2610000);
//
//        driveOrReverse(-.125);  // goes backward for 12 inches
//        sleep(800);
//        stopDriving();

//
//        rotateClockwise(.33);
//        sleep(1900);
//        stopDriving();
//
//        driveOrReverse(.125);
//        sleep(11407);
//        // sleep(13145);
//        stopDriving();
//
//        rotateCounterClockwise(.25);
//        sleep(2000);
//        stopDriving();
//
//        outtake();
//        sleep(1000);

        //outtake();


    }

    public void up() {
        intakeYRightPos = 0.2610000;
        intakeYLeftPos = 0.89599;

        intakeYRight.setPosition(intakeYRightPos);
        intakeYLeft.setPosition(intakeYLeftPos);
    }

    public void outtake() {
        intakeYLeftPos = 1;
        intakeYRightPos = 0.168999;

        intakeXLeftPos = .577;
        intakeXRightPos = .571;

        intakeXLeft.setPosition(intakeXLeftPos);
        intakeXRight.setPosition(intakeXRightPos);

        intakeYLeft.setPosition(intakeYLeftPos);
        intakeYRight.setPosition(intakeYRightPos);
    }

    public void intake() {
        intakeXLeftPos = 0.973;
        intakeXRightPos = 0.45999;
        
        intakeYLeftPos = 1.0;
        intakeYRightPos = 0.1570;


        intakeXLeft.setPosition(intakeXLeftPos);
        intakeXRight.setPosition(intakeXRightPos);

        intakeYLeft.setPosition(intakeYLeftPos);
        intakeYRight.setPosition(intakeYRightPos);
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

        public void stopDriving() {
        driveOrReverse(0);
    }


}