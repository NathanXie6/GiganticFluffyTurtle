package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libswerve.differential.DiffySwerveModule;

// TODO
public class MyDiffySwerveModule extends DiffySwerveModule {
    public MyDiffySwerveModule(double x, double y, DcMotor right, DcMotor left) {
        super(x, y, right, left);
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.FORWARD);
        //left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public double getAngle() {
        return (((right.getCurrentPosition() / 537.6) * (2 * Math.PI)) + ((left.getCurrentPosition() / 537.6) * (2 * Math.PI))) / (66.0 / 30.0) / 2;
    }
}