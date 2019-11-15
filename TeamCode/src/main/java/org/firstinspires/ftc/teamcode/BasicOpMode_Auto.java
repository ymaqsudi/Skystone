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
    private double circumference = 3.141592653589793238462643383 * 4;   // PI * diameter

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


        forward(18,.5);
        rotateCounterClockwise(5, 1);
        rotateClockwise(5, 1);
        backward(22, 1);



        // Stop motors after the job is done
        back_left.setPower(0);
        back_right.setPower(0);
        front_right.setPower(0);
        front_left.setPower(0);

    }



    // Methods


    /*
    The following 6 methods only take one parameter (speed). We made this in order to call
    these methods later on when we make better methods using encoders.
     */
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

    /*
    These methods are the encoder methods. They calculate the driving target using the desiredDistance
    parameter value, and call upon the methods listed above in order to invoke the speed method
     */
    public void forward(double desiredDistance, double speed) {

        // Calculations for all the variables we use in this method
        double rotationsNeeded = desiredDistance/circumference;     // You may need to multiply
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        // Resets encoders before use. You need to do this to clear any values the encoders might
        // have stored.
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Tells the motors to drive to the specific target, a variable that was defined in the
        // beginning of the program. It is made using the parameter values given.
        front_left.setTargetPosition(encoderDrivingTarget);
        back_left.setTargetPosition(encoderDrivingTarget);
        front_right.setTargetPosition(encoderDrivingTarget);
        back_right.setTargetPosition(encoderDrivingTarget);

        // calls on one of the primitive methods to move the wheels at the speed of the second parameter
        forward(speed);

        // Built in methods that tell the motor to run to the target position
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // While the motors are moving, don't do anything except telemetry
        while (front_left.isBusy() & front_right.isBusy() & back_left.isBusy() & back_right.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving 18 inches");
            telemetry.update();
        }

    }

    public void backward(double desiredDistance, double speed) {
        double rotationsNeeded = desiredDistance/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        front_left.setTargetPosition(-encoderDrivingTarget);
        back_left.setTargetPosition(-encoderDrivingTarget);
        front_right.setTargetPosition(-encoderDrivingTarget);
        back_right.setTargetPosition(-encoderDrivingTarget);

        backward(speed);

        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (front_left.isBusy() & front_right.isBusy() & back_left.isBusy() & back_right.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving");
            telemetry.update();
        }

    }

    public void strafeRight(double desiredDistance, double speed) {
        double rotationsNeeded = desiredDistance/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        front_left.setTargetPosition(encoderDrivingTarget);
        back_left.setTargetPosition(encoderDrivingTarget);
        front_right.setTargetPosition(encoderDrivingTarget);
        back_right.setTargetPosition(encoderDrivingTarget);

        strafeRight(speed);

        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (front_left.isBusy() & front_right.isBusy() & back_left.isBusy() & back_right.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving 18 inches");
            telemetry.update();
        }

    }

    public void strafeLeft(double desiredDistance, double speed) {
        double rotationsNeeded = desiredDistance/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        front_left.setTargetPosition(encoderDrivingTarget);
        back_left.setTargetPosition(encoderDrivingTarget);
        front_right.setTargetPosition(encoderDrivingTarget);
        back_right.setTargetPosition(encoderDrivingTarget);

        strafeLeft(speed);

        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (front_left.isBusy() & front_right.isBusy() & back_left.isBusy() & back_right.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving 18 inches");
            telemetry.update();
        }

    }

    public void rotateClockwise(double desiredDistance, double speed) {
        double rotationsNeeded = desiredDistance/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        front_left.setTargetPosition(encoderDrivingTarget);
        back_left.setTargetPosition(encoderDrivingTarget);
        front_right.setTargetPosition(-encoderDrivingTarget);
        back_right.setTargetPosition(-encoderDrivingTarget);

        rotateClockwise(speed);

        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (front_left.isBusy() & front_right.isBusy() & back_left.isBusy() & back_right.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving 18 inches");
            telemetry.update();
        }

    }

    public void rotateCounterClockwise(double desiredDistance, double speed) {
        double rotationsNeeded = desiredDistance/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        front_left.setTargetPosition(-encoderDrivingTarget);
        back_left.setTargetPosition(-encoderDrivingTarget);
        front_right.setTargetPosition(encoderDrivingTarget);
        back_right.setTargetPosition(encoderDrivingTarget);

        rotateCounterClockWise(speed);

        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (front_left.isBusy() & front_right.isBusy() & back_left.isBusy() & back_right.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving 18 inches");
            telemetry.update();
        }

    }

}