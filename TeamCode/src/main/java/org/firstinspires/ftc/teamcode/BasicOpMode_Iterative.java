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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Teleop
 *
 *
 * If you make a change to this file, type the following code into terminal
 * git add *
 * git commit -m "type a message explaining what changes you made"
 * git push
 *
 *
 */

@TeleOp(name="TeleOp", group="Iterative Opmode")

public class BasicOpMode_Iterative extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor armDrive;



    // Setup a variable for each drive wheel to save power level for telemetry
    private double backLeftPower = 0;
    private double backRightPower = 0;
    private double frontLeftPower = 0;
    private double frontRightPower = 0;

    private double armPower = 0;



    @Override
    public void init() {


        telemetry.addData("Status", "Initialized");

        backLeftDrive  = hardwareMap.get(DcMotor.class, "backLeft");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");
        armDrive = hardwareMap.get(DcMotor.class, "arm");

        // Robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        armDrive.setDirection(DcMotor.Direction.FORWARD);




        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }


     /* Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

       if (gamepad1.right_trigger > .5) {
           armDrive.setPower(1);
       } else if (gamepad1.right_trigger < .1) {
           armDrive.setPower(0);
       }

       //gamepad1.right_trigger > .5 ? armDrive.setPower(1) : armDrive.setPower(0);


       if (gamepad1.left_stick_x > .2) {
           rotateClockwise(1);
       } else if (gamepad1.left_stick_x < -.2) {
            rotateCounterClockWise(1);
       }

       if (gamepad1.right_stick_y < 0) {
            forward(1);
       } else if (gamepad1.right_stick_y > 0) {
           backward(1);
       }

       if (gamepad1.right_stick_x < -.2) {
           strafeLeft(1);

       } else if (gamepad1.right_stick_x > .2) {
           strafeRight(1);
       }







        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight(%.2f) ", frontLeftPower, frontRightPower, backLeftPower, backRightPower, armPower);
        telemetry.addData("right stick y", " : " + gamepad1.right_stick_y);
        telemetry.addData("right stick x", " : " + gamepad1.right_stick_x);
        telemetry.addData("left stick x", " : " + gamepad1.left_stick_x);
        telemetry.addData("left stick y", " : " + gamepad1.left_stick_y);


        telemetry.update();
    }


    public void rotateClockwise(double motorSpeed) {
        backLeftDrive.setPower(motorSpeed);
        backRightDrive.setPower(-motorSpeed);
        frontLeftDrive.setPower(motorSpeed);
        frontRightDrive.setPower(-motorSpeed);
    }

    public void rotateCounterClockWise(double motorSpeed) {
        backLeftDrive.setPower(-motorSpeed);
        backRightDrive.setPower(motorSpeed);
        frontLeftDrive.setPower(-motorSpeed);
        frontRightDrive.setPower(motorSpeed);
    }

    public void forward(double motorSpeed) {
        backLeftDrive.setPower(motorSpeed);
        backRightDrive.setPower(motorSpeed);
        frontLeftDrive.setPower(motorSpeed);
        frontRightDrive.setPower(motorSpeed);
    }

    public void backward(double motorSpeed) {
        backLeftDrive.setPower(-motorSpeed);
        backRightDrive.setPower(-motorSpeed);
        frontLeftDrive.setPower(-motorSpeed);
        frontRightDrive.setPower(-motorSpeed);
    }

    public void strafeLeft(double motorSpeed) {
        backLeftDrive.setPower(motorSpeed);
        backRightDrive.setPower(-motorSpeed);
        frontLeftDrive.setPower(-motorSpeed);
        frontRightDrive.setPower(motorSpeed);
    }

    public void strafeRight(double motorSpeed) {
        backLeftDrive.setPower(-motorSpeed);
        backRightDrive.setPower(motorSpeed);
        frontLeftDrive.setPower(motorSpeed);
        frontRightDrive.setPower(-motorSpeed);
    }

    @Override
    public void stop() {
    }
}