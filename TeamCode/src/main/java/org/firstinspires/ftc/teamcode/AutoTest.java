/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 *
 * Autonomous
 */

@Autonomous(name="Briananomous", group="Linear Opmode")
public class AutoTest extends LinearOpMode {

    // Declare OpMode members.
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;

    private Servo armRight;
    private Servo armLeft;

    private CRServo handRight;
    private CRServo handLeft;

    static final int MOTOR_TICK_COUNTS = 1120;
    private double circumference = 3.141592653589793238462643383 * 4;   // PI * diameter


    @Override
    public void runOpMode() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");

        armRight = hardwareMap.get(Servo.class, "armRight");
        armLeft = hardwareMap.get(Servo.class, "armLeft");
        handRight = hardwareMap.get(CRServo.class, "handRight");
        handLeft = hardwareMap.get(CRServo.class, "handLeft");

        //   colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        // Set motor direction
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);

        // set right motor to run with an encoder.
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Run until the end of the match (driver presses STOP)

        waitForStart();

// WORK IN PROGRESS (WORKS)
        /**Blue Alliance Autonomous**/

        outtake(); // Gets ready for embrace
        sleep(2000);

        driveOrReverse(0.03); // Goes for block
        sleep(2200);
        stopMotors();

        intake(); //Embraces block
        sleep(2000);

        driveOrReverse(-0.04); // Brings block back
        sleep(1000);
        stopMotors();

        counterClockWise(.1); // Rotates
        sleep(700);
        stopMotors();

        driveOrReverse(.03); // Brings block over bridge
        sleep(2500);
        stopMotors();

        outtake(); // Unloads block

        driveOrReverse(-0.03); // Stays under bridge
        sleep(1100);
        stopMotors();

/** GOOD CODE BRIDGE PARK
        driveOrReverse(.025);
        sleep(1100);
        counterClockWise(.030);
        sleep(700);
        driveOrReverse(.025);
        sleep(1200);
**/
    }

    public double convertInchesToCM(double in) {
        return in * 2.54;
    }

    public void stopMotors() {
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        sleep(500);
    }



    /*
    The following 6 methods only take one parameter (speed). We made this in order to call
    these methods later on when we make better methods using encoders.
    */
    public void counterClockWise(double motorSpeed) {
        backLeft.setPower(motorSpeed);
        backRight.setPower(motorSpeed);
        frontLeft.setPower(-motorSpeed);
        frontRight.setPower(-motorSpeed);
    }

    public void clockWise(double motorSpeed) {
        backLeft.setPower(-motorSpeed);
        backRight.setPower(-motorSpeed);
        frontLeft.setPower(motorSpeed);
        frontRight.setPower(motorSpeed);
    }




    public void driveOrReverse(double motorSpeed) {
        backLeft.setPower(-motorSpeed);
        backRight.setPower(motorSpeed);
        frontLeft.setPower(motorSpeed);
        frontRight.setPower(-motorSpeed/1.1);
    }

    public void strafe(double motorSpeed) {
        backLeft.setPower(motorSpeed);
        backRight.setPower(-motorSpeed);
        frontLeft.setPower(-motorSpeed);
        frontRight.setPower(motorSpeed);
    }

    public void strafe2 (double motorSpeed) {
        backLeft.setPower(-motorSpeed);
        backRight.setPower(motorSpeed);
        frontLeft.setPower(motorSpeed);
        frontRight.setPower(-motorSpeed);
    }

    /**
     * Reset encoders
     */
    public void resetEncoders() {
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Run to Target position
     */
    public void runToTargetPosition() {
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Run to Target position
     */
    public void specifyTarget(int encoderDrivingTarget) {
        backLeft.setTargetPosition(encoderDrivingTarget);
        backRight.setTargetPosition(encoderDrivingTarget);
        frontLeft.setTargetPosition(encoderDrivingTarget);
        frontRight.setTargetPosition(encoderDrivingTarget);
    }

    public void allowMotorsToFinish() {
        while (frontLeft.isBusy() & frontRight.isBusy() & backLeft.isBusy() & backRight.isBusy()) {
            // stop doing stuff when the robot is driving
            telemetry.addData("Path", "Driving");
            telemetry.update();
        }
    }
    /*
    These methods are the encoder methods. They calculate the driving target using the desiredDistance
    parameter value, and call upon the methods listed above in order to invoke the speed method
     */
    public void forward(double desiredDistance, double speed) {
        // Calculations for all the variables we use in this method
        double rotationsNeeded = (desiredDistance*0.23778)/circumference;     // You may need to multiply
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count


        // Resets encoders before use. You need to do this to clear any values the encoders might
        // have stored.
        resetEncoders();

        // Tells the motors to drive to the specific target, a variable that was defined in the
        // beginning of the program. It is made using the parameter values given.
        specifyTarget(encoderDrivingTarget);

        // calls on one of the primitive methods to move the wheels at the speed of the second parameter
        driveOrReverse(speed);

        // Built in methods that tell the motor to run to the target position
        runToTargetPosition();

        // While the motors are moving, don't do anything except telemetry
        allowMotorsToFinish();

        stop();


    }

    public void backward(double desiredDistance, double speed) {
        double rotationsNeeded = (desiredDistance*0.23778)/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        resetEncoders();

        specifyTarget(-encoderDrivingTarget);

        driveOrReverse(speed);

        runToTargetPosition();

        allowMotorsToFinish();

    }

    public void strafeRight(double desiredDistance, double speed) {
        double rotationsNeeded = (desiredDistance*0.23778)/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        resetEncoders();

        backLeft.setTargetPosition(encoderDrivingTarget);
        backRight.setTargetPosition(encoderDrivingTarget);
        frontLeft.setTargetPosition(encoderDrivingTarget);
        frontRight.setTargetPosition(encoderDrivingTarget);

        strafe2(speed);

        runToTargetPosition();

        allowMotorsToFinish();

    }

    public void strafeLeft(double desiredDistance, double speed) {
        double rotationsNeeded = (desiredDistance*0.23778)/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        resetEncoders();

        backLeft.setTargetPosition(encoderDrivingTarget);
        backRight.setTargetPosition(encoderDrivingTarget);
        frontLeft.setTargetPosition(encoderDrivingTarget);
        frontRight.setTargetPosition(encoderDrivingTarget);

        strafe(-speed);

        runToTargetPosition();

        allowMotorsToFinish();

    }

    public void rotateClockwise(double desiredDistance, double speed) {
        double rotationsNeeded = (desiredDistance*0.23778)/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        resetEncoders();

        backLeft.setTargetPosition(-encoderDrivingTarget);
        backRight.setTargetPosition(encoderDrivingTarget);
        frontLeft.setTargetPosition(-encoderDrivingTarget);
        frontRight.setTargetPosition(encoderDrivingTarget);

        //rotate(speed);

        runToTargetPosition();

        allowMotorsToFinish();

    }

    public void rotateCounterClockwise(double desiredDistance, double speed) {
        double rotationsNeeded = (desiredDistance*0.23778)/circumference;
        int encoderDrivingTarget = (int)(rotationsNeeded*MOTOR_TICK_COUNTS); // rotations needed * tick count

        resetEncoders();

        backLeft.setTargetPosition(encoderDrivingTarget);
        backRight.setTargetPosition(-encoderDrivingTarget);
        frontLeft.setTargetPosition(encoderDrivingTarget);
        frontRight.setTargetPosition(-encoderDrivingTarget);

      //  rotate(-speed);

        runToTargetPosition();

        allowMotorsToFinish();

    }

    public void intake() {
        armLeft.setPosition(.481000000000002);
        armRight.setPosition(.12799999);
    }

    public void outtake() {
        armLeft.setPosition(0.13099999999999999);
        armRight.setPosition(0.478000000000001);
    }

}
