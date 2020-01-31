
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.Arrays;

@TeleOp(name="Controller", group="Linear Opmode")

public class ControllerCode extends LinearOpMode {

    HardwarePushbot hardware = new HardwarePushbot();

    private Driver robot;

    private ElapsedTime runtime = new ElapsedTime();



    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);
        robot = new Driver();

        telemetry.addData("Status", "Initialized");


        telemetry.addData("Status", "Initialized");

        waitForStart();

        runtime.reset();

        init();

        telemetry.addData("Left Servo: ", hardware.intakeXLeft.getPosition());




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

            hardware.frontLeft.setPower(FrontLeftVal/3);
            hardware.frontRight.setPower(FrontRightVal/3);
            hardware.backLeft.setPower(BackLeftVal/3);
            hardware.backRight.setPower(BackRightVal/3);


            hardware.intakeXLeft.setPosition(gamepad1.left_trigger);









            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Left Pos: ", hardware.intakeXLeft.getPosition());
            telemetry.addData("Right Pos: ", hardware.intakeXRight.getPosition());
            telemetry.update();

        }
    }
}