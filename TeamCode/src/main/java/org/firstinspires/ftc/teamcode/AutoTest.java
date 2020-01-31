package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



@Autonomous(name="Briananomous", group="Linear Opmode")
public class AutoTest extends LinearOpMode {

    HardwarePushbot hardware = new HardwarePushbot();
    private Driver robot = new Driver();


    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);  // initialize hardware map and set up robot object


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