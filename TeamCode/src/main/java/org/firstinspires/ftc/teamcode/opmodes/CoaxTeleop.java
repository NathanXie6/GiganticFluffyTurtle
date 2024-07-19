package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PwmControl;

import org.firstinspires.ftc.teamcode.libswerve.Drivetrain;
import org.firstinspires.ftc.teamcode.libswerve.SwerveModule;
import org.firstinspires.ftc.teamcode.libswerve.Util;
import org.firstinspires.ftc.teamcode.libswerve.Vector2;
import org.firstinspires.ftc.teamcode.libswerve.coaxial.CoaxialSwerveModule;
import org.firstinspires.ftc.teamcode.modules.MyCoaxialSwerveModule;

@TeleOp(name = "Coax Teleop", group = "teleop")
public class CoaxTeleop extends OpMode {
    private Drivetrain drivetrain;
    private SwerveModule m1, m2, m3, m4;

    @Override
    public void init() {
        m1 = new MyCoaxialSwerveModule(-1, -1,
            hardwareMap.get(DcMotor.class, "FrontLeftM"),
            hardwareMap.get(CRServoImplEx.class, "FrontLeftS"),
            hardwareMap.get(AnalogInput.class, "FrontLeftE"),
            5.148403960792001, DcMotorSimple.Direction.FORWARD
        );
        m2 = new MyCoaxialSwerveModule(1, -1,
            hardwareMap.get(DcMotor.class, "FrontRightM"),
            hardwareMap.get(CRServoImplEx.class, "FrontRightS"),
            hardwareMap.get(AnalogInput.class, "FrontRightE"),
            0.931053822791156, DcMotorSimple.Direction.FORWARD
        );
        m3 = new MyCoaxialSwerveModule(1, 1,
            hardwareMap.get(DcMotor.class, "BackRightM"),
            hardwareMap.get(CRServoImplEx.class, "BackRightS"),
            hardwareMap.get(AnalogInput.class, "BackRightE"),
            6.22796943629831, DcMotorSimple.Direction.FORWARD
        );
        m4 = new MyCoaxialSwerveModule(-1, 1,
            hardwareMap.get(DcMotor.class, "BackLeftM"),
            hardwareMap.get(CRServoImplEx.class, "BackLeftS"),
            hardwareMap.get(AnalogInput.class, "BackLeftE"),
            4.640037149574743, DcMotorSimple.Direction.FORWARD
        );

        drivetrain = new Drivetrain(0.8, null, new SwerveModule[]{m1, m2, m3, m4}) {
            @Override
            public void drive(Gamepad gamepad) {
                setPower(new Vector2(gamepad.left_stick_x, gamepad.left_stick_y), gamepad.right_stick_x);
            }
        };
    }

    @Override
    public void loop() {
        drivetrain.drive(gamepad1);
        drivetrain.update();
    }
}
