package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "6 wheel TeleOp", group = "teleop")
public class SixWheelTeleOp extends OpMode {
    private DcMotor[] left = null;
    private DcMotor[] right = null;

    @Override
    public void init() {
        left = new DcMotor[] {
            hardwareMap.get(DcMotor.class, "left0"),
            hardwareMap.get(DcMotor.class, "left1")
        };
        right = new DcMotor[] {
            hardwareMap.get(DcMotor.class, "right0"),
            hardwareMap.get(DcMotor.class, "right1")
        };

        for (int i = 0; i < right.length; i++) {
            left[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            right[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            left[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            left[i].setDirection(DcMotor.Direction.FORWARD);
            right[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            right[i].setDirection(DcMotor.Direction.FORWARD);
        }
        right[0].setDirection(DcMotor.Direction.REVERSE);
        left[0].setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        double rightPower;
        double leftPower;
        rightPower = leftPower = gamepad1.left_stick_y;

        rightPower += gamepad1.right_stick_x;
        leftPower -= gamepad1.right_stick_x;

        for (int i = 0; i < right.length; i++) {
            left[i].setPower(leftPower);
            right[i].setPower(rightPower);
        }
    }
}