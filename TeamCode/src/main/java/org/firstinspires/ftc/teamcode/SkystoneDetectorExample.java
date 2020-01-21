package org.firstinspires.ftc.teamcode;


import com.disnodeteam.dogecv.detectors.skystone.SkystoneDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.Locale;


@Autonomous(name = "Skystone Detector OpenCV", group="DogeCV")

public class SkystoneDetectorExample extends LinearOpMode {
    private OpenCvCamera phoneCam;
    private SkystoneDetector skyStoneDetector;

    HardwarePushbot hardware = new HardwarePushbot();
    Driver robot = new Driver();

    @Override
    public void runOpMode() {

        hardware.init(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        phoneCam.openCameraDevice();


        skyStoneDetector = new SkystoneDetector();
        phoneCam.setPipeline(skyStoneDetector);


        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);


        waitForStart();

        robot.outtake();

        while (opModeIsActive())
        {

            telemetry.addData("Stone Position X", skyStoneDetector.getScreenPosition().x);
            telemetry.addData("Stone Position Y", skyStoneDetector.getScreenPosition().y);
            telemetry.addData("Frame Count", phoneCam.getFrameCount());
            telemetry.addData("FPS", String.format(Locale.US, "%.2f", phoneCam.getFps()));
            telemetry.addData("Total frame time ms", phoneCam.getTotalFrameTimeMs());
            telemetry.addData("Pipeline time ms", phoneCam.getPipelineTimeMs());
            telemetry.addData("Overhead time ms", phoneCam.getOverheadTimeMs());
            telemetry.addData("Theoretical max FPS", phoneCam.getCurrentPipelineMaxFps());
            telemetry.update();

            // skystone is in the "right" position
            if (skyStoneDetector.getScreenPosition().x > 110) {
                rightSkystonePos();
            }

        }
    }

        public void rightSkystonePos() {
            robot.strafeLeftEncoder(.01, 6);

            robot.driveOrReverseEncoder(.01, 48);

            robot.intake();

            robot.driveOrReverseEncoder(-.01, 18);

            robot.strafeLeftEncoder(.01, 96);

            robot.outtake();
        }
}