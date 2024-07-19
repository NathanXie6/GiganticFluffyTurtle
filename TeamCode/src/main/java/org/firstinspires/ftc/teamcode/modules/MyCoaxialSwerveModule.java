package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PwmControl;

import org.firstinspires.ftc.teamcode.libswerve.coaxial.CoaxialSwerveModule;

public class MyCoaxialSwerveModule extends CoaxialSwerveModule {
    private final double angleOffset;

    /**
     * @param x Reversed
     * @param y Reversed
     * @param forward
     * @param turn
     * @param encoder
     */
    public MyCoaxialSwerveModule(double x, double y, DcMotor forward, CRServoImplEx turn, AnalogInput encoder, double angleOffset, DcMotorSimple.Direction direction) {
        super(x, y, forward, turn, encoder);
        this.angleOffset = angleOffset;
        turn.setPwmRange(new PwmControl.PwmRange(500, 2500));
        turn.setDirection(DcMotorSimple.Direction.REVERSE);
        forward.setDirection(direction);
        forward.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        forward.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public double getAngle() {
        return encoder.getVoltage() / 3.3 * 2 * Math.PI - angleOffset;
    }
}
