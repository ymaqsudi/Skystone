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

    public Servo intakeXLeft;
    public Servo intakeXRight;

    public Servo intakeYLeft;
    public Servo intakeYRight;



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

        intakeXLeft = hwMap.get(Servo.class, "xLeft");
        intakeXRight = hwMap.get(Servo.class, "xRight");

        intakeYLeft = hwMap.get(Servo.class, "yLeft");
        intakeYRight = hwMap.get(Servo.class, "yRight");


        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);


        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);


        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
 }