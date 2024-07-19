package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.libswerve.Drivetrain;
import org.firstinspires.ftc.teamcode.libswerve.Localizer;
import org.firstinspires.ftc.teamcode.libswerve.SwerveModule;
import org.firstinspires.ftc.teamcode.libswerve.Vector2;
import org.firstinspires.ftc.teamcode.modules.MyDiffySwerveModule;

class MyDrivetrain extends Drivetrain {
    public MyDrivetrain(double speed, Localizer localizer, SwerveModule[] modules) {
        super(speed, localizer, modules);
    }

    @Override
    /**
     * Gamepad left stick: strafing<br>
     * Gamepad right stick: turning<br>
     * <b>DOES NOT UPDATE THE SWERVE MODULES USE <code>Drivetrain.update()</code></b>
     * @param gamepad
     */
    public void drive(Gamepad gamepad) {
        setPower(new Vector2(gamepad.left_stick_x, gamepad.left_stick_y), gamepad.right_stick_x);
    }
}

@TeleOp(name = "Diffy TeleOp", group = "teleop")
public class DiffyTeleop extends OpMode {
    private SwerveModule m1 = null, m2 = null;
    private Drivetrain drive;

    @Override
    public void init() {
        // Two diffy swerve modules
        m1 = new MyDiffySwerveModule(
            4, 0,
            hardwareMap.get(DcMotor.class, "dswerve_m0_0"),
            hardwareMap.get(DcMotor.class, "dswerve_m1_0")
        );
        m2 = new MyDiffySwerveModule(
            -4, 0,
            hardwareMap.get(DcMotor.class, "dswerve_m0_1"),
            hardwareMap.get(DcMotor.class, "dswerve_m1_1")
        );
        drive = new MyDrivetrain(0.2, null, new SwerveModule[]{m1, m2});
    }

    @Override
    public void loop() {
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