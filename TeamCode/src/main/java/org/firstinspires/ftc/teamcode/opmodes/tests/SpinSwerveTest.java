package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libswerve.SwerveModule;
import org.firstinspires.ftc.teamcode.libswerve.differential.DiffySwerveModule;

@Autonomous(name = "Spin Swerve Test", group = "tests")
public class SpinSwerveTest extends LinearOpMode {
    private double t = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor right = hardwareMap.get(DcMotor.class, "dswerve_m0_0");
        DcMotor left = hardwareMap.get(DcMotor.class, "dswerve_m1_0");
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.FORWARD);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        SwerveModule module = new DiffySwerveModule(0, 0, right, left) {
            @Override
            public double getAngle() {
                return 0;
            }
        };

        waitForStart();
        // Run at full speed for a bit
        long start = System.currentTimeMillis();
        while (opModeIsActive() && start + 5000 > System.currentTimeMillis()) {
            right.setPower(1.0);
            left.setPower(1.0);
        }

        while (opModeIsActive()) {
            module.setPowers(0.0, Math.sin(t));
            sleep(100);
            idle();
            t += 0.01;
        }
    }
}
