package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 *
 */
public class HardwarePushbot
{

    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;

    public DcMotor firstLiftMotorOne;
    public DcMotor firstLiftMotorTwo;

    public DcMotor secondLiftMotorOne;

    public Servo intake;






    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    public HardwarePushbot(){
    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        backLeft  = hwMap.get(DcMotor.class, "backLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        frontLeft  = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");

        firstLiftMotorOne = hwMap.get(DcMotor.class, "firstLiftMotorOne");
        firstLiftMotorTwo = hwMap.get(DcMotor.class, "firstLiftMotorTwo");
        secondLiftMotorOne = hwMap.get(DcMotor.class, "secondLiftMotorOne");

        intake = hwMap.get(Servo.class, "intake");


        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);

        firstLiftMotorOne.setDirection(DcMotor.Direction.REVERSE);
        firstLiftMotorTwo.setDirection(DcMotor.Direction.REVERSE);
        secondLiftMotorOne.setDirection(DcMotor.Direction.FORWARD);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        firstLiftMotorOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        firstLiftMotorTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        secondLiftMotorOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);

        firstLiftMotorOne.setPower(0);
        firstLiftMotorTwo.setPower(0);
        secondLiftMotorOne.setPower(0);


        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        firstLiftMotorOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        firstLiftMotorTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        secondLiftMotorOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
 }