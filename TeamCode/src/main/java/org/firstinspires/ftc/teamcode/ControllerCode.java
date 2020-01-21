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
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.Arrays;

@TeleOp(name="Controller", group="Linear Opmode")

public class ControllerCode extends LinearOpMode {

    HardwarePushbot robot = new HardwarePushbot();

    Driver hardware = new Driver();

    private ElapsedTime runtime = new ElapsedTime();


    double rightArmServoPos;
    double leftArmServoPos;



    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        runtime.reset();


        rightArmServoPos = .393;        // open values
        leftArmServoPos = .216;         // open values

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            double FrontLeftVal =  gamepad1.left_stick_x + (+gamepad1.left_stick_y)  - +gamepad1.right_stick_y;
            double FrontRightVal =  +gamepad1.left_stick_x  - (+gamepad1.left_stick_y) + +gamepad1.right_stick_y;
            double BackLeftVal = +gamepad1.left_stick_x  - (+gamepad1.left_stick_y)  - +gamepad1.right_stick_y;
            double BackRightVal = +gamepad1.left_stick_x + (+gamepad1.left_stick_y) + +gamepad1.right_stick_y;



            double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
            Arrays.sort(wheelPowers);
            if (wheelPowers[3] > 1) {
                FrontLeftVal /= wheelPowers[3];
                FrontRightVal /= wheelPowers[3];
                BackLeftVal /= wheelPowers[3];
                BackRightVal /= wheelPowers[3];
            }
            robot.frontLeft.setPower(FrontLeftVal);
            robot.frontRight.setPower(FrontRightVal);
            robot.backLeft.setPower(BackLeftVal);
            robot.backRight.setPower(BackRightVal);

            if((gamepad1.right_stick_x > 0 || gamepad1.right_stick_x < 0) & gamepad1.left_stick_x == 0 & gamepad1.left_stick_y == 0) {
                hardware.rotateClockwise(gamepad1.right_stick_x);
            }

            if (gamepad1.left_trigger > 0.1) {
                leftArmServoPos += 0.005;
                rightArmServoPos -= 0.005;
            }

            if (gamepad1.right_trigger > 0.1) {
                leftArmServoPos -= 0.005;
                rightArmServoPos += 0.005;
            }

            if (gamepad1.left_bumper) {
                robot.handLeft.setPower(1);
                robot.handRight.setPower(0);
            }
             else {
                 robot.handLeft.setPower(.5);
                 robot.handRight.setPower(.5);
            }

           if (gamepad1.left_trigger > 0.1) {
               leftArmServoPos += 0.01;
               rightArmServoPos -= 0.01;
               robot.handLeft.setPower(0);    // inward rotation
               robot.handRight.setPower(1);   // inward rotation
           }

            robot.armRight.setPosition(rightArmServoPos);
            robot.armLeft.setPosition(leftArmServoPos);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Right Arm: ", robot.armRight.getPosition());
            telemetry.addData("Left Arm: ", robot.armLeft.getPosition());
            telemetry.addData("Right Hand: ", robot.handRight.getPower());
            telemetry.addData("Left Hand: ", robot.handLeft.getPower());
            telemetry.update();
        }


    }
}