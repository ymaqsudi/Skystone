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

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
 * git commit -m "type a message explaining what the change you made did"
 * git push
 */

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")

public class BasicOpMode_Iterative extends OpMode
{
 //   Test_Hardware_Old_Bot robot       = new Test_Hardware_Old_Bot();
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor armDrive = null;


    // Setup a variable for each drive wheel to save power level for telemetry
    private double backLeftPower = 0;
    private double backRightPower = 0;
    private double frontLeftPower = 0;
    private double frontRightPower = 0;

    private double armPower = 0;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        backLeftDrive  = hardwareMap.get(DcMotor.class, "backLeft");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");
        armDrive = hardwareMap.get(DcMotor.class, "arm");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
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



        // Show the elapsed game time and wheel power.

       double right_joystickx = gamepad1.right_stick_x;
       double right_joysticky = gamepad1.right_stick_y;

       double left_joystickx = gamepad1.left_stick_x;

       double right_trigger = gamepad1.right_trigger;
       double left_trigger = gamepad1.left_trigger;

       if (right_trigger > .5) {
           armDrive.setPower(1);
       } else if (right_trigger < .1) {
           armDrive.setPower(0);
       }

       if (left_trigger > .5) {
           armDrive.setPower(-1);
       } else if (left_trigger < .1) {
           armDrive.setPower(0);
       }


       if(left_joystickx > .2) {
           backLeftDrive.setPower(-1);
           backRightDrive.setPower(-2);
           frontLeftDrive.setPower(1);
           frontRightDrive.setPower(-2);
       } else if (left_joystickx < -.2) {
           backLeftDrive.setPower(1);
           backRightDrive.setPower(2);
           frontLeftDrive.setPower(-1);
           frontRightDrive.setPower(-2);
       }

       if (right_joysticky < 0) {
           backLeftDrive.setPower(-1);
           backRightDrive.setPower(2);
           frontLeftDrive.setPower(1);
           frontRightDrive.setPower(-2);
       } else if (right_joysticky > 0) {
           backLeftDrive.setPower(1);
           backRightDrive.setPower(-2);
           frontLeftDrive.setPower(-1);
           frontRightDrive.setPower(2);
       }

       if (right_joystickx < -.2) {
           backLeftDrive.setPower(-1);
           backRightDrive.setPower(-2);
           frontLeftDrive.setPower(-1);
           frontRightDrive.setPower(-2);

       } else if (right_joystickx > .2) {
           backLeftDrive.setPower(1);
           backRightDrive.setPower(2);
           frontLeftDrive.setPower(1);
           frontRightDrive.setPower(2);
       }

        setDrivePower(backLeftPower, backRightPower, frontLeftPower, frontRightPower, armPower);




        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight(%.2f) ", frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        telemetry.addData("right stick y", " : " + gamepad1.right_stick_y);
        telemetry.addData("right stick x", " : " + gamepad1.left_stick_x);

        telemetry.update();
    }

    /**
     * @param backLeftPower
     * @param backRightPower
     * @param frontLeftPower
     * @param frontRightPower
     */
    public void setDrivePower(double backLeftPower, double backRightPower, double frontLeftPower, double frontRightPower, double armPower) {
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
        frontRightDrive.setPower(frontRightPower);
        frontLeftDrive.setPower(frontLeftPower);
        armDrive.setPower(armPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}

