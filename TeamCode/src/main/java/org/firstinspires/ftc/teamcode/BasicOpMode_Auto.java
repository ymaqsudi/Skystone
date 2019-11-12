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
        // Hardware initiation
        back_left = hardwareMap.dcMotor.get("backLeft");
        back_right = hardwareMap.dcMotor.get("backRight");
        front_left = hardwareMap.dcMotor.get("frontLeft");
        front_right = hardwareMap.dcMotor.get("frontRight");

        // Set motor direction
        back_left.setDirection(DcMotor.Direction.REVERSE);
        back_right.setDirection(DcMotor.Direction.REVERSE);
        front_left.setDirection(DcMotor.Direction.FORWARD);
        front_right.setDirection(DcMotor.Direction.FORWARD);


        // set right motor to run with an encoder.
        back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // wait for start button.
        waitForStart();

        // Reset encoders
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Rpm for 18 inches - circumference of wheel
        double desiredDistance = 18;
        double circumference = 3.141592653589793238462643383 * 4;   // PI * diameter
        double rotationsNeeded = desiredDistance/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        // Sets the target position to the rotations needed * motor tick count
        front_left.setTargetPosition(encoderDrivingTarget);
        back_left.setTargetPosition(encoderDrivingTarget);
        front_right.setTargetPosition(encoderDrivingTarget);
        back_right.setTargetPosition(encoderDrivingTarget);

        // set the power desired for the motors. 1 is very fast
        forward(.5);

        // tell motors to run to position
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // While all motors are running, do nothing and display info to the phone
        while (front_left.isBusy() & front_right.isBusy() & back_left.isBusy() & back_right.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving 18 inches");
            telemetry.update();
        }


        // sleep for 2 seconds so that the hardware has time to catch up
        sleep(2000);

        // new desired distance and rotations needed
        desiredDistance = 10;
        rotationsNeeded = desiredDistance/circumference;
        encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        // Sets the target position to the rotations needed * motor tick count
        front_right.setTargetPosition(encoderDrivingTarget);
        front_left.setTargetPosition(encoderDrivingTarget);
        back_right.setTargetPosition(encoderDrivingTarget);
        back_left.setTargetPosition(encoderDrivingTarget);

        // Method that sets the motor value to the -(parameter)
        backward(.5);

        // Tells motor to run to position specified by encoder value
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        // Stop motors after the job is done
        back_left.setPower(0);
        back_right.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);

    }

    // Methods
    public void rotateClockwise(double motorSpeed) {
        back_left.setPower(motorSpeed);
        back_right.setPower(-motorSpeed);
        front_left.setPower(motorSpeed);
        front_right.setPower(-motorSpeed);
    }

    public void rotateCounterClockWise(double motorSpeed) {
        back_left.setPower(-motorSpeed);
        back_right.setPower(motorSpeed);
        front_left.setPower(-motorSpeed);
        front_right.setPower(motorSpeed);
    }

    public void forward(double motorSpeed) {
        back_left.setPower(motorSpeed);
        back_right.setPower(motorSpeed);
        front_left.setPower(motorSpeed);
        front_right.setPower(motorSpeed);
    }

    public void backward(double motorSpeed) {
        back_left.setPower(-motorSpeed);
        back_right.setPower(-motorSpeed);
        front_left.setPower(-motorSpeed);
        front_right.setPower(-motorSpeed);
    }

    public void strafeLeft(double motorSpeed) {
        back_left.setPower(motorSpeed);
        back_right.setPower(-motorSpeed);
        front_left.setPower(-motorSpeed);
        front_right.setPower(motorSpeed);
    }

    public void strafeRight(double motorSpeed) {
        back_left.setPower(-motorSpeed);
        back_right.setPower(motorSpeed);
        front_left.setPower(motorSpeed);
        front_right.setPower(-motorSpeed);
    }
}