
package org.firstinspires.ftc.teamcode;

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

@Autonomous(name="Auto Encoder", group="Linear Opmode")

public class Auto2 extends LinearOpMode {


    double intakeXLeftPos;
    double intakeXRightPos;

    double intakeYLeftPos;
    double intakeYRightPos;

    static final int MOTOR_TICK_COUNTS = 1120;
    double circumference = 3.14*4;
    double rotationsNeeded;

    int encoderDrivingTarget;


    HardwarePushbot hardware = new HardwarePushbot();
    Methods robot = new Methods();



    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);

        waitForStart();

        // SHOULD GO FORWARD FOR 10 INCHES

        telemetry.addData("Mode", "Start");
        telemetry.update();

        // sets servo positions to "starting position"
        intakeYLeftPos = 1;
        intakeYRightPos = 0.168999;
        intakeXLeftPos = .577;
        intakeXRightPos = .571;

        // sets servos to the servoPos
        hardware.intakeYRight.setPosition(intakeYRightPos);
        hardware.intakeYLeft.setPosition(intakeYLeftPos);
        hardware.intakeXRight.setPosition(intakeXRightPos);
        hardware.intakeXLeft.setPosition(intakeXLeftPos);

        // waits 2 seconds before going
        sleep(2000);

        hardware.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rotationsNeeded = 10/circumference;
        encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS);

        hardware.backLeft.setTargetPosition(encoderDrivingTarget);
        hardware.backRight.setTargetPosition(encoderDrivingTarget);
        hardware.frontLeft.setTargetPosition(encoderDrivingTarget);
        hardware.frontRight.setTargetPosition(encoderDrivingTarget);

        hardware.backLeft.setPower(0.5);
        hardware.backRight.setPower(0.5);
        hardware.frontLeft.setPower(0.5);
        hardware.frontRight.setPower(0.5);

        hardware.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (hardware.backLeft.isBusy() && hardware.backRight.isBusy()) {

        }

        robot.stopDriving();


    }

}