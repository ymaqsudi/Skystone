package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Autonomous", group="Linear OpMode")
//@Disabled
public class BasicOpMode_Linear extends LinearOpMode {
    DcMotor back_left;
    DcMotor back_right;
    DcMotor front_left;
    DcMotor front_right;


    @Override
    public void runOpMode() throws InterruptedException
    {
        back_left = hardwareMap.dcMotor.get("backLeft");
        back_right = hardwareMap.dcMotor.get("backRight");
        front_left = hardwareMap.dcMotor.get("frontLeft");
        front_right = hardwareMap.dcMotor.get("frontRight");

        back_left.setDirection(DcMotor.Direction.REVERSE);
        front_left.setDirection(DcMotor.Direction.REVERSE);

        // reset encoder count kept by left motor.
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set left motor to run to target encoder position and stop with brakes on.
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // set right motor to run without regard to an encoder.
        back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set left motor to run to 5000 encoder counts.

        back_left.setTargetPosition(5000);
        front_left.setTargetPosition(5000);
        back_right.setTargetPosition(5000);
        front_right.setTargetPosition(5000);

        // set both motors to 25% power. Movement will start.

        back_left.setPower(0.25);
        back_right.setPower(0.25);
        front_right.setPower(0.25);
        front_left.setPower(0.25);

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && back_right.isBusy() && back_left.isBusy() && front_right.isBusy() && front_left.isBusy())
        {
            telemetry.addData("encoder-fwd", back_right.getCurrentPosition() + "  busy=" + back_right.isBusy());
            telemetry.update();
            idle();
        }

        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.

        back_left.setPower(0.0);
        back_right.setPower(0.0);
        front_left.setPower(0.0);
        front_right.setPower(0.0);

        // wait 5 sec so you can observe the final encoder position.

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-end", front_right.getCurrentPosition() + "  busy=" + front_right.isBusy());
            telemetry.update();
            idle();
        }

        // Now back up to starting point. In this example instead of
        // having the motor monitor the encoder, we will monitor the encoder ourselves.

        back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        back_left.setPower(-0.25);
        back_right.setPower(-0.25);
        front_left.setPower(-0.25);
        front_right.setPower(-0.25);

        while (opModeIsActive() && back_left.getCurrentPosition() > 0)
        {
            telemetry.addData("encoder-back", back_left.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.

        back_left.setPower(0.0);
        back_right.setPower(0.0);
        front_right.setPower(0.0);
        front_left.setPower(0.0);


        // wait 5 sec so you can observe the final encoder position.

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-back-end", front_left.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }
}