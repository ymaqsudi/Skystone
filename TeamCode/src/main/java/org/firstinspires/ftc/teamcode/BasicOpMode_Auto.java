package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Autonomous", group="Linear OpMode")
//@Disabled
public class BasicOpMode_Auto extends LinearOpMode {
    DcMotor back_left;
    DcMotor back_right;
    DcMotor front_left;
    DcMotor front_right;
    static final int MOTOR_TICK_COUNTS = 1120;

    @Override
    public void runOpMode() throws InterruptedException
    {
        back_left = hardwareMap.dcMotor.get("backLeft");
        back_right = hardwareMap.dcMotor.get("backRight");
        front_left = hardwareMap.dcMotor.get("frontLeft");
        front_right = hardwareMap.dcMotor.get("frontRight");

        back_left.setDirection(DcMotor.Direction.REVERSE);
        front_left.setDirection(DcMotor.Direction.REVERSE);


        // set right motor to run without regard to an encoder.
        back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // wait for start button.
        waitForStart();

        // Drive forward 18 inches

        // Reset encoders
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Rpm for 18 inches - circumference of wheel
        double circumference = 3.14 * 4;
        double rotationsNeeded = 18/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*1120); // rotations needed * tick count

        front_left.setTargetPosition(encoderDrivingTarget);
        back_left.setTargetPosition(encoderDrivingTarget);
        front_right.setTargetPosition(encoderDrivingTarget);
        back_right.setTargetPosition(encoderDrivingTarget);

        // set the power desired for the motors. 1 is very fast
        back_left.setPower(1);
        back_right.setPower(1);
        front_right.setPower(1);
        front_left.setPower(1);

        // tell motors to run to position
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (front_left.isBusy() & front_right.isBusy() & back_left.isBusy() & back_right.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving 18 inches");
            telemetry.update();
        }

        // Stop motors after the job is done
        back_left.setPower(0);
        back_right.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);

    }
}