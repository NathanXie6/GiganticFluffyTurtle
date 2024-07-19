package org.firstinspires.ftc.teamcode.libswerve.coaxial;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libswerve.SwerveModule;

public abstract class CoaxialSwerveModule extends SwerveModule {
    protected final DcMotor forward;
    protected final CRServo turn;
    protected final AnalogInput encoder;

    /**
     *
     * @param x Reversed
     * @param y Reversed
     * @param forward
     * @param turn
     * @param encoder
     */
    public CoaxialSwerveModule(double x, double y, DcMotor forward, CRServo turn, AnalogInput encoder) {
        super(x, y);

        this.forward = forward;
        this.turn = turn;
        this.encoder = encoder;
    }

    @Override
    public void setPowers(double turnPower, double forwardPower) {
        forward.setPower(forwardPower);
        turn.setPower(turnPower);
    }

    public abstract double getAngle();
}
