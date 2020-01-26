
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
        robot= new Driver(hardware);

        telemetry.addData("Status", "Initialized");


        telemetry.addData("Status", "Initialized");

        waitForStart();

        runtime.reset();



        while (opModeIsActive()) {


            double FrontLeftVal =  gamepad1.left_stick_y - (gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
            double FrontRightVal =  gamepad1.left_stick_y  + (gamepad1.left_stick_x) - -gamepad1.right_stick_x;
            double BackLeftVal = gamepad1.left_stick_y  + (gamepad1.left_stick_x)  + -gamepad1.right_stick_x;
            double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - -gamepad1.right_stick_x;

            double linearLiftVal = gamepad1.left_trigger;
            double linearLiftVal2 = -gamepad1.right_trigger;



            double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal, linearLiftVal, linearLiftVal2};
            Arrays.sort(wheelPowers);
            if (wheelPowers[3] > 1) {
                FrontLeftVal /= wheelPowers[3];
                FrontRightVal /= wheelPowers[3];
                BackLeftVal /= wheelPowers[3];
                BackRightVal /= wheelPowers[3];

                linearLiftVal /= wheelPowers[3];
                linearLiftVal2 /= wheelPowers[3];
            }

            hardware.frontLeft.setPower(FrontLeftVal/2);
            hardware.frontRight.setPower(FrontRightVal/2);
            hardware.backLeft.setPower(BackLeftVal/2);
            hardware.backRight.setPower(BackRightVal/2);

            hardware.linearLift1.setPower(linearLiftVal/3);
            hardware.linearLift2.setPower(linearLiftVal/3);

            hardware.linearLift1.setPower(linearLiftVal2/3);
            hardware.linearLift2.setPower(linearLiftVal2/3);


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();



        }


    }
}