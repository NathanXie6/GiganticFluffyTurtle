package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@Autonomous(name = "Motor Power Test", group = "tests")
public class MotorPowerTest extends LinearOpMode {
    public static double rightPower = 0;
    public static double leftPower = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor right = hardwareMap.get(DcMotor.class, "dswerve_m0_0");
        DcMotor left = hardwareMap.get(DcMotor.class, "dswerve_m1_0");
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setDirection(DcMotorSimple.Direction.FORWARD);
        left.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            right.setPower(rightPower);
            left.setPower(leftPower);
        }
    }
}
