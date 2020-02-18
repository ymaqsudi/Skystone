//// Simple autonomous program that drives bot forward until end of period
//// or touch sensor is hit. If touched, backs up a bit and turns 90 degrees
//// right and keeps going. Demonstrates obstacle avoidance and use of the
//// REV Hub's built in IMU in place of a gyro. Also uses gamepad1 buttons to
//// simulate touch sensor press and supports left as well as right turn.
////
//// Also uses IMU to drive in a straight line when not avoiding an obstacle.
//
//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.TouchSensor;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
//import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
//import org.firstinspires.ftc.robotcore.external.navigation.Position;
//import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
//
//import java.lang.Math;
//
//@TeleOp(name="IMU TEST", group="Linear Opmode")
////@Disabled
//public class GyroTest extends LinearOpMode {
//
//    BNO055IMU imu;
//
//    Orientation angles;
//
//
//
//    HardwarePushbot hardware = new HardwarePushbot();
//    Methods robot = new Methods(hardware);
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        hardware.init(hardwareMap);
//
//
//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
//        imu.initialize(parameters);
//
//        telemetry.addData("Mode", "calibrating...");
//        telemetry.update();
//
//        // make sure the imu gyro is calibrated before continuing.
//        while (!isStopRequested() && !imu.isGyroCalibrated()) {
//            sleep(50);
//            idle();
//        }
//
//        telemetry.addData("Mode", "waiting for start");
//        telemetry.addData("imu calib status", imu.getCalibrationStatus().toString());
//        telemetry.update();
//
//        // wait for start button.
//
//        waitForStart();
//
//        telemetry.addData("Mode", "running");
//        telemetry.update();
//
//        sleep(1000);
//
//        // drive until end of period.
//
//        while (opModeIsActive()) {
//
//            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//            telemetry.addData("Heading ", Math.abs(angles.firstAngle));   // How far you turn
//            telemetry.addData("Roll ", angles.secondAngle);
//            telemetry.addData("Pitch ", angles.thirdAngle);
//            telemetry.update();
//
//
//            // I want to press A and turn the robot 90 degrees
//            // turn until current angle > starting angle + 90
//
//
//
//            if (gamepad1.a && (Math.abs(angles.firstAngle) < 85))
//                turn(.3);
//            // Heading - 92.5625
//
//
//
//            if (gamepad1.b && (Math.abs(angles.firstAngle) < 85))
//                turn(-.3);
//            // Heading - 93.9375
//
//
//            if (Math.abs(angles.firstAngle) > 85) {
//                robot.stopDriving();
//            }
//
//
//
//        }
//    }
//
//    public void turn(double power) {
//        hardware.backLeft.setPower(-power);
//        hardware.frontLeft.setPower(-power);
//        hardware.backRight.setPower(power);
//        hardware.frontRight.setPower(power);
//    }
//
//    public void getHeading() {
//        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//    }
//
//
//}