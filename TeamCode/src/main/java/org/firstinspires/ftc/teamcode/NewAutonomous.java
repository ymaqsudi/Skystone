package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



@Autonomous(name="It's this one", group="Linear Opmode")
public class NewAutonomous extends LinearOpMode {

    HardwarePushbot hardware = new HardwarePushbot();
    private Driver robot;

    @Override
    public void runOpMode() {
        hardware.init(hardwareMap);  // initialize hardware map and set up robot object
        robot = new Driver(hardware);
        
        robot.runUsingEncoder();

        waitForStart(); // start

        robot.driveOrReverseEncoder(.01, 24);
    }
}