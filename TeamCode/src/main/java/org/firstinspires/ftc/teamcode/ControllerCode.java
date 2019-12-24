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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.ColorSensor;
import java.util.Arrays;



@TeleOp(name="Controller", group="Linear Opmode")

public class ControllerCode extends LinearOpMode {

    // Declare OpMode members.
    HardwarePushbot robot = new HardwarePushbot();

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;

    private Servo armRight;
    private Servo armLeft;

    private CRServo handRight;
    private CRServo handLeft;

    private ColorSensor colorSensor;

    double armServoPos, handServoSpeed;

    public void rotate(double motorSpeed) {
        backLeft.setPower(motorSpeed);
        backRight.setPower(motorSpeed);
        frontLeft.setPower(-motorSpeed);
        frontRight.setPower(-motorSpeed);
    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");



        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        armRight = hardwareMap.get(Servo.class, "armRight");
        armLeft = hardwareMap.get(Servo.class, "armLeft");
        handRight = hardwareMap.get(CRServo.class, "handRight");
        handLeft = hardwareMap.get(CRServo.class, "handLeft");

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");


        // Robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        runtime.reset();

        armServoPos = .5;
        handServoSpeed = 0;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {



            double FrontLeftVal =  -gamepad1.left_stick_y - (-gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
            double FrontRightVal =  -gamepad1.left_stick_y  + (-gamepad1.left_stick_x) - -gamepad1.right_stick_x;
            double BackLeftVal = -gamepad1.left_stick_y  + (-gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
            double BackRightVal = -gamepad1.left_stick_y - (-gamepad1.left_stick_x) - -gamepad1.right_stick_x;


            double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
            Arrays.sort(wheelPowers);
            if (wheelPowers[3] > 1) {
                FrontLeftVal /= wheelPowers[3];
                FrontRightVal /= wheelPowers[3];
                BackLeftVal /= wheelPowers[3];
                BackRightVal /= wheelPowers[3];
            }
            frontLeft.setPower(FrontLeftVal);
            frontRight.setPower(FrontRightVal);
            backLeft.setPower(BackLeftVal);
            backRight.setPower(BackRightVal);

            if((gamepad1.right_stick_x > 0 || gamepad1.right_stick_x < 0) & gamepad1.left_stick_x == 0 & gamepad1.left_stick_y == 0) {
                rotate(gamepad1.right_stick_x);
            }




            if (gamepad1.left_trigger > .5) {
                armServoPos += .1;
                handServoSpeed = 1;
            }

            else
                handServoSpeed = 0;

            if (gamepad1.right_trigger > .5)
                armServoPos -= 0.1;


            armRight.setPosition(Range.clip(armServoPos, 0, 1));
            armLeft.setPosition(Range.clip(-armServoPos, 0, 1));

            handRight.setPower(handServoSpeed);
            handLeft.setPower(-handServoSpeed);



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Right Arm: ", + armRight.getPosition());
            telemetry.addData("Left Arm: ", + armLeft.getPosition());

            telemetry.addData("Red:", colorSensor.red());
            telemetry.addData("Green:", colorSensor.green());
            telemetry.addData("Blue:", colorSensor.blue());
            telemetry.addData("Alpha", colorSensor.alpha()); // total lumosity
            telemetry.addData("Argb:", colorSensor.argb()); // combined color value

            telemetry.update();
        }


    }
}
