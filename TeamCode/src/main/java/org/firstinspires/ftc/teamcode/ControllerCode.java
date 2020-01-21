
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.Arrays;

@TeleOp(name="Controller", group="Linear Opmode")

public class ControllerCode extends LinearOpMode {

    HardwarePushbot hardware = new HardwarePushbot();

    Driver robot = new Driver();

    private ElapsedTime runtime = new ElapsedTime();


    double rightArmServoPos;
    double leftArmServoPos;


    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);

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
            hardware.frontLeft.setPower(FrontLeftVal);
            hardware.frontRight.setPower(FrontRightVal);
            hardware.backLeft.setPower(BackLeftVal);
            hardware.backRight.setPower(BackRightVal);

            if((gamepad1.right_stick_x > 0 || gamepad1.right_stick_x < 0) & gamepad1.left_stick_x == 0 & gamepad1.left_stick_y == 0) {
                robot.rotateClockwise(gamepad1.right_stick_x);
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
                hardware.handLeft.setPower(1);
                hardware.handRight.setPower(0);
            }
             else {
                 hardware.handLeft.setPower(.5);
                 hardware.handRight.setPower(.5);
            }

           if (gamepad1.left_trigger > 0.1) {
               leftArmServoPos += 0.01;
               rightArmServoPos -= 0.01;
               hardware.handLeft.setPower(0);    // inward rotation
               hardware.handRight.setPower(1);   // inward rotation
           }

            hardware.armRight.setPosition(rightArmServoPos);
            hardware.armLeft.setPosition(leftArmServoPos);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Right Arm: ", hardware.armRight.getPosition());
            telemetry.addData("Left Arm: ", hardware.armLeft.getPosition());
            telemetry.addData("Right Hand: ", hardware.handRight.getPower());
            telemetry.addData("Left Hand: ", hardware.handLeft.getPower());
            telemetry.update();
        }


    }
}