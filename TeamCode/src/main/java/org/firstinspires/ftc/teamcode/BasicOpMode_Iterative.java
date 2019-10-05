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
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 *
 * Test-Tank
 *
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
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;

    // Setup a variable for each drive wheel to save power level for telemetry
    private double backLeftPower = 0;
    private double backRightPower = 0;
    private double frontLeftPower = 0;
    private double frontRightPower = 0;

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

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);



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


/*
        double right = gamepad1.left_stick_y;
        double left  =  gamepad1.right_stick_y;

        backLeftPower    = Range.clip(-left, -1.0, 1.0) ;
        backRightPower   = Range.clip(-right, -1.0, 1.0) ;
        frontLeftPower   = Range.clip(right, -1.0, 1.0) ;
        frontRightPower   = Range.clip(left, -1.0, 1.0) ;
*/
     /*   if (gamepad1.x) {
            backLeftPower = Range.clip(-left, -1.0, 1.0);
            frontLeftPower = Range.clip(-left, -1.0, 1.0);

            backRightPower = Range.clip(-right, 1.0, -1.0);
            frontRightPower = Range.clip(-right, 1.0, -1.0);
        }
       */

        // Show the elapsed game time and wheel power.

        double forward = gamepad1.right_stick_y;
        double strafe = gamepad1.right_stick_x;

        double rotate = gamepad1.left_stick_x;

    // if the left stick x value > 0 rotate clockwise
    // if the left stick x value < 0 rotate counter clockwise

        if (rotate > 0) {
            backLeftDrive.setPower(1);
            frontLeftDrive.setPower(1);

            backRightDrive.setPower(-1);
            frontRightDrive.setPower(-1);
        } else if(rotate < 0){
            backLeftDrive.setPower(-1);
            frontLeftDrive.setPower(-1);

            backRightDrive.setPower(1);
            frontRightDrive.setPower(1);
        }

        // set all wheels to gamepad1.rightstick_y

        // make it go forward and backward

        double right = gamepad1.left_stick_y;
        backLeftPower = Range.clip(-right, -1.0, 1.0);
        frontLeftPower = Range.clip(-right, -1.0, 1.0);
        backRightPower = Range.clip(right, 1.0, -1.0);
        frontRightPower = Range.clip(right, 1.0, -1.0);

        // if rightstick x > 1 strafe right
        // if rightstick x < 1 strafe left
    /*
        if (gamepad1.right_stick_x > 1) {
            frontRightDrive.setPower(-1);
            backLeftDrive.setPower(-1);

            frontLeftDrive.setPower(1);
            backRightDrive.setPower(1);
        } else if (gamepad1.right_stick_x < 1) {
            frontRightDrive.setPower(1);
            backLeftDrive.setPower(1);

            frontLeftDrive.setPower(-1);
            backRightDrive.setPower(-1);
        }
*/
        setDrivePower(backLeftPower, backRightPower, frontLeftPower, frontRightPower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "frontLeft (%.2f), frontRight (%.2f), backLeft (%.2f), backRight(%.2f) ", frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        telemetry.addData("right stick y", " : " + gamepad1.right_stick_y);
        telemetry.addData("right stick x", " : " + gamepad1.right_stick_x);

        telemetry.update();
    }

    /**
     * @param backLeftPower
     * @param backRightPower
     * @param frontLeftPower
     * @param frontRightPower
     */
    public void setDrivePower(double backLeftPower, double backRightPower, double frontLeftPower, double frontRightPower) {
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
        frontRightDrive.setPower(frontRightPower);
        frontLeftDrive.setPower(frontLeftPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
