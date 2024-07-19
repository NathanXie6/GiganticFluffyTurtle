package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.libswerve.Drivetrain;
import org.firstinspires.ftc.teamcode.libswerve.Pose2d;
import org.firstinspires.ftc.teamcode.libswerve.SwerveModule;
import org.firstinspires.ftc.teamcode.libswerve.TwoWheelLocalizer;
import org.firstinspires.ftc.teamcode.libswerve.Vector2;
import org.firstinspires.ftc.teamcode.libswerve.differential.DiffySwerveModule;
import org.firstinspires.ftc.teamcode.modules.MyDiffySwerveModule;

@Autonomous(name = "Auto test", group = "tests")
public class AutonomousTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Two diffy swerve modules
        DiffySwerveModule m1 = new MyDiffySwerveModule(
            4, 0,
            hardwareMap.get(DcMotor.class, "dswerve_m0_0"),
            hardwareMap.get(DcMotor.class, "dswerve_m1_0")
        );
        DiffySwerveModule m2 = new MyDiffySwerveModule(
            -4, 0,
            hardwareMap.get(DcMotor.class, "dswerve_m0_1"),
            hardwareMap.get(DcMotor.class, "dswerve_m1_1")
        );

        Drivetrain drive = new Drivetrain(
            0.2,
            new TwoWheelLocalizer(new Pose2d(0, 0, 0), 4, 4) {
                public void updateValues(double dfwdEncoder, double dstrEncoder, double dTheta) {
                    this.dfwdEncoder = dfwdEncoder * ((0.6889764 * Math.PI * 2.0) / 8192.0);
                    this.dstrEncoder = dstrEncoder * ((0.6889764 * Math.PI * 2.0) / 8192.0);
                    this.dTheta = dTheta;
                }
            },
            new SwerveModule[]{m1, m2}
        ) {
            public void drive(Gamepad gamepad) {
                setPower(new Vector2(gamepad.left_stick_x, gamepad.left_stick_y), gamepad.right_stick_x);
            }
        };

        DcMotor fwdEncoder = hardwareMap.get(DcMotor.class, "fwd_encoder");
        DcMotor strEncoder = hardwareMap.get(DcMotor.class, "str_encoder");
        IMU imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(new IMU.Parameters(
            new RevHubOrientationOnRobot(new Orientation(
                AxesReference.INTRINSIC,
                AxesOrder.XYZ,
                AngleUnit.RADIANS,
                0, 0, 0,
                0 // Unused
            ))
        ));

        waitForStart();

        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        int fwd = fwdEncoder.getCurrentPosition();
        int str = strEncoder.getCurrentPosition();
        double h =  orientation.getYaw(AngleUnit.RADIANS);
        while (opModeIsActive()) {
            drive.render(FtcDashboard.getInstance());
            int nfwd = fwdEncoder.getCurrentPosition();
            int nstr = strEncoder.getCurrentPosition();
            orientation = imu.getRobotYawPitchRollAngles();
            double nh = orientation.getYaw(AngleUnit.RADIANS);
            ((TwoWheelLocalizer) (drive.localizer)).updateValues(
                nfwd - fwd,
                nstr - str,
                nh - h
            );
            fwd = nfwd;
            str = nstr;
            h = nh;
            drive.localizer.update();

            if (gamepad1.right_bumper) {
                if (drive.speed < 1) {
                    drive.speed += 0.01;
                }
            }
            if (gamepad1.right_trigger > 0) {
                if (drive.speed > 0) {
                    drive.speed -= 0.01;
                }
            }

            telemetry.addData("Robot speed:", drive.speed);
            telemetry.update();

            drive.drive(gamepad1);
            drive.update();
        }
    }
}
