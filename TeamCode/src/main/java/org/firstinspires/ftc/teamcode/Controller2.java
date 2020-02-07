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
    HardwarePushbot hardware = new HardwarePushbot();


    double intakeXLeftPos;
    double intakeXRightPos;

    double intakeYLeftPos;
    double intakeYRightPos;

    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        intakeYLeftPos = 1;

        intakeYRightPos = 0.178999;

        intakeXLeftPos = .577;
        intakeXRightPos = .571;

        waitForStart();

        runtime.reset();

        while (opModeIsActive()) {

            double FrontLeftVal =  gamepad1.left_stick_y - (gamepad1.left_stick_x)  + -gamepad1.right_stick_x + gamepad2.left_stick_y - gamepad2.left_stick_x + -gamepad2.right_stick_x;
            double FrontRightVal =  gamepad1.left_stick_y  + (gamepad1.left_stick_x) - -gamepad1.right_stick_x + gamepad2.left_stick_y + gamepad2.left_stick_x - -gamepad2.right_stick_x;
            double BackLeftVal = gamepad1.left_stick_y  + (gamepad1.left_stick_x)  + -gamepad1.right_stick_x + gamepad2.left_stick_y + gamepad2.left_stick_x + -gamepad2.right_stick_x;
            double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - -gamepad1.right_stick_x + gamepad2.left_stick_y - gamepad2.left_stick_x - -gamepad2.right_stick_x;




            double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
            Arrays.sort(wheelPowers);
            if (wheelPowers[3] > 1) {
                FrontLeftVal /= wheelPowers[3];
                FrontRightVal /= wheelPowers[3];
                BackLeftVal /= wheelPowers[3];
                BackRightVal /= wheelPowers[3];

            }

            hardware.frontLeft.setPower(FrontLeftVal/2);
            hardware.frontRight.setPower(FrontRightVal/2);
            hardware.backLeft.setPower(BackLeftVal/2);
            hardware.backRight.setPower(BackRightVal/2);


            // intake
            if ((gamepad1.right_trigger > 0.5) || gamepad2.right_trigger > 0.5) {
                intakeXLeftPos = 0.668;
                intakeXRightPos = 0.391999;
                intakeYLeftPos = 1.0;
                intakeYRightPos = 0.1570;
            }

            // outtake
            if ((gamepad1.left_trigger > 0.5) || gamepad2.left_trigger > 0.5) {
                intakeYLeftPos = 1;
                intakeYRightPos = 0.168999;
                intakeXLeftPos = .577;
                intakeXRightPos = .571;
            }


            // up on servo arms
            if (gamepad1.a || gamepad2.a) {
                intakeYLeftPos -= 0.001;
                intakeYRightPos += 0.001;
            }

            // down on servo arms
            if (gamepad1.b || gamepad2.b) {
                intakeYLeftPos += 0.001;
                intakeYRightPos -= 0.001;
            }

            // sets servo positions = servoPos variable
            hardware.intakeXLeft.setPosition(intakeXLeftPos);
            hardware.intakeXRight.setPosition(intakeXRightPos);

            hardware.intakeYLeft.setPosition(intakeYLeftPos);
            hardware.intakeYRight.setPosition(intakeYRightPos);




            // information to print to the phones
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left X Pos: ", hardware.intakeXLeft.getPosition());
            telemetry.addData("Right X Pos: ", hardware.intakeXRight.getPosition());
            telemetry.addData("Left Y Pos: ", hardware.intakeYLeft.getPosition());
            telemetry.addData("Right Y Pos: ", hardware.intakeYRight.getPosition());
            telemetry.update();

        }
    }
}
