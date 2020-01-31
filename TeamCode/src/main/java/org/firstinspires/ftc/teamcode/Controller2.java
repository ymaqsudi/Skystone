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
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.Arrays;


@TeleOp(name="Competition", group="Linear Opmode")
public class Controller2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
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

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        intakeXLeft = hardwareMap.get(Servo.class, "xLeft");
        intakeXRight = hardwareMap.get(Servo.class, "xRight");

        intakeYLeft = hardwareMap.get(Servo.class, "yLeft");
        intakeYRight = hardwareMap.get(Servo.class, "yRight");

        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("left servo pos: ", intakeXLeft.getPosition());


        intakeYLeftPos = 1;
        intakeYRightPos = 0;

        intakeXLeftPos = .5;
        intakeXRightPos = .6;

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double FrontLeftVal =  gamepad1.left_stick_y - (gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
            double FrontRightVal =  gamepad1.left_stick_y  + (gamepad1.left_stick_x) - -gamepad1.right_stick_x;
            double BackLeftVal = gamepad1.left_stick_y  + (gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
            double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - -gamepad1.right_stick_x;




            double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
            Arrays.sort(wheelPowers);
            if (wheelPowers[3] > 1) {
                FrontLeftVal /= wheelPowers[3];
                FrontRightVal /= wheelPowers[3];
                BackLeftVal /= wheelPowers[3];
                BackRightVal /= wheelPowers[3];

            }

            frontLeft.setPower(FrontLeftVal/3);
            frontRight.setPower(FrontRightVal/3);
            backLeft.setPower(BackLeftVal/3);
            backRight.setPower(BackRightVal/3);


            // pressing b button will lift the servos to the appropriate height of a 1 block stack
            if (gamepad1.b) {
                intakeYRightPos = 0.35;
                intakeYLeftPos = 0.8;
            }

            // pressing dpad up will intake a block
            if (gamepad1.dpad_up) {
                intakeXRightPos = 0.4;
                intakeXLeftPos = 1;
            }

            // pressing dpad down will return the servos to their original x position
            if (gamepad1.dpad_down) {
                intakeXRightPos = 0.6;
                intakeXLeftPos = 0.5;
            }

            // pressing a button will return servos to their original y position
            if (gamepad1.a) {
                intakeYLeftPos = 1;
                intakeYRightPos = 0;
            }

            intakeXLeft.setPosition(intakeXLeftPos);
            intakeXRight.setPosition(intakeXRightPos);

            intakeYLeft.setPosition(intakeYLeftPos);
            intakeYRight.setPosition(intakeYRightPos);







            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left X Pos: ", intakeXLeft.getPosition());
            telemetry.addData("Right X Pos: ", intakeXRight.getPosition());
            telemetry.addData("Left Y Pos: ", intakeYLeft.getPosition());
            telemetry.addData("Right Y Pos: ", intakeYRight.getPosition());
            telemetry.update();

        }
    }
}
