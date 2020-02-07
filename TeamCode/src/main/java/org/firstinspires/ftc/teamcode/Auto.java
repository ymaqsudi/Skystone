
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.lang.reflect.Method;

/**
 * this autonomous opMode is run based on time. Usually we can consistently park under the sky bridge using
 * this code. since it is run based on time, dont expect this to be easily modified.
 */

@Autonomous(name="Auto", group="Linear Opmode")

public class Auto extends LinearOpMode {



    double intakeXLeftPos;
    double intakeXRightPos;

    double intakeYLeftPos;
    double intakeYRightPos;


    private ElapsedTime runtime = new ElapsedTime();
    HardwarePushbot hardware = new HardwarePushbot();
    Methods robot = new Methods();


    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);

        waitForStart();

        runtime.reset();

        telemetry.addData("Mode", "Start");
        telemetry.update();

        // sets servo positions to "starting position"
        intakeYLeftPos = 1;
        intakeYRightPos = 0.168999;
        intakeXLeftPos = .577;
        intakeXRightPos = .571;

        // sets servos to the servoPos
        hardware.intakeYRight.setPosition(intakeYRightPos);
        hardware.intakeYLeft.setPosition(intakeYLeftPos);
        hardware.intakeXRight.setPosition(intakeXRightPos);
        hardware.intakeXLeft.setPosition(intakeXLeftPos);

        // waits 2 seconds before going
        sleep(2000);

        // travels 2 tiles - 96 inches - and parks under bridge
        robot.driveOrReverse(.25);

        // runs the above code segment for 2 seconds
        sleep(2000);

        // stops all movement of motors
        robot.stopDriving();


    }


}