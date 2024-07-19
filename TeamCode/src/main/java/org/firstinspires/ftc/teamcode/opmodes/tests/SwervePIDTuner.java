package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.libswerve.SwerveModule;
import org.firstinspires.ftc.teamcode.libswerve.Util;
import org.firstinspires.ftc.teamcode.modules.MyCoaxialSwerveModule;
import org.firstinspires.ftc.teamcode.modules.MyDiffySwerveModule;

@Config
@Autonomous(name = "Swerve PID Tuner", group = "tests")
public class SwervePIDTuner extends LinearOpMode {
    public static double angle = 0;
    public static double fwd = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        MyCoaxialSwerveModule m1 = new MyCoaxialSwerveModule(-1, 1,
            hardwareMap.get(DcMotor.class, "FrontLeftM"),
            hardwareMap.get(CRServoImplEx.class, "FrontLeftS"),
            hardwareMap.get(AnalogInput.class, "FrontLeftE"),
            0, DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            m1.fwdPower = fwd;
            telemetry.addData("Angle (deg)", Math.toDegrees(Util.clampAngle(m1.getAngle())));
            telemetry.addData("Target (deg)", Math.toDegrees(Util.clampAngle(m1.getTargetAngle())));
            telemetry.addData("Error", Math.toDegrees(Util.clampAngle(m1.getTargetAngle() - m1.getAngle())));
            telemetry.update();

            m1.setTargetAngle(Math.toRadians(angle));

            m1.update();
        }
    }
}
