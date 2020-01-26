package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



@Autonomous(name="Briananomous", group="Linear Opmode")
public class AutoTest extends LinearOpMode {

//    // Declare OpMode members.
//    private DcMotor backLeft;
//    private DcMotor backRight;
//    private DcMotor frontLeft;
//    private DcMotor frontRight;
//
//    private Servo armRight;
//    private Servo armLeft;
//
//    private CRServo handRight;
//    private CRServo handLeft;
//
//    static final int MOTOR_TICK_COUNTS = 1120;
//    private double circumference = 3.141592653589793238462643383 * 4;   // PI * diameter

    HardwarePushbot hardware = new HardwarePushbot();
    Driver robot = new Driver(hardware);


    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);

//        backLeft = hardwareMap.dcMotor.get("backLeft");
//        backRight = hardwareMap.dcMotor.get("backRight");
//        frontLeft = hardwareMap.dcMotor.get("frontLeft");
//        frontRight = hardwareMap.dcMotor.get("frontRight");
//
//        armRight = hardwareMap.get(Servo.class, "armRight");
//        armLeft = hardwareMap.get(Servo.class, "armLeft");
//        handRight = hardwareMap.get(CRServo.class, "handRight");
//        handLeft = hardwareMap.get(CRServo.class, "handLeft");
//
//        //   colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
//
//        // Set motor direction
//        backLeft.setDirection(DcMotor.Direction.REVERSE);
//        backRight.setDirection(DcMotor.Direction.FORWARD);
//        frontLeft.setDirection(DcMotor.Direction.REVERSE);
//        frontRight.setDirection(DcMotor.Direction.FORWARD);
//
//        // set right motor to run with an encoder.
//        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Run until the end of the match (driver presses STOP)

        waitForStart();

// WORK IN PROGRESS (WORKS)
        /**Blue Alliance Autonomous**/

        //robot.outtake(); // Gets ready for embrace
        sleep(2000);

        robot.driveOrReverse(0.03); // Goes for block
        sleep(2200);
        robot.stopDriving();

       // robot.intake(); //Embraces block
        sleep(2000);

        robot.driveOrReverse(-0.04); // Brings block back
        sleep(1000);
        robot.stopDriving();

        robot.rotateCounterClockwise(.1); // Rotates
        sleep(700);
        robot.stopDriving();

        robot.driveOrReverse(.03); // Brings block over bridge
        sleep(2500);
        robot.stopDriving();

        //robot.outtake(); // Unloads block

        robot.driveOrReverse(-0.03); // Stays under bridge
        sleep(1100);
        robot.stopDriving();

/** GOOD CODE BRIDGE PARK
        driveOrReverse(.025);
        sleep(1100);
        counterClockWise(.030);
        sleep(700);
        driveOrReverse(.025);
        sleep(1200);
**/
    }


}