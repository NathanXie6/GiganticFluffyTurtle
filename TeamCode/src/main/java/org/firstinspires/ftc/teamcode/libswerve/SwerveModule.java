package org.firstinspires.ftc.teamcode.libswerve;

import com.acmerobotics.dashboard.config.Config;

@Config
public abstract class SwerveModule {
    private double targetAngle = 0;
    public static PID pid = new PID(0.4, 0, 0);
    public double fwdPower = 0;
    public final double x;
    public final double y;

    public SwerveModule(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set powers of a swerve module
     * turning and forward
     * @param turnPower How fast to turn a swerve module [-1, 1]
     * @param forwardPower How fast to move the wheel on a swerve module [-1, 1]
     */
    public abstract void setPowers(double turnPower, double forwardPower);

    /**
     *
     * @return Hardware angle (Determined by you)
     */
    public abstract double getAngle();

    /**
     * <b>CLIPS FOR YOU. SET THE POWER FIRST.</b>
     * @param targetAngle radians
     */
    public void setTargetAngle(double targetAngle) {
        while (targetAngle > getAngle() + Math.PI / 2) {
            targetAngle -= Math.PI;
            fwdPower *= -1;
        }
        while (targetAngle < getAngle() - Math.PI / 2) {
            targetAngle += Math.PI;
            fwdPower *= -1;
        }

        this.targetAngle = targetAngle;
        // this.targetAngle = Util.clampAngle(targetAngle);
    }

    public double getTargetAngle() {
        return targetAngle;
    }

    /**
     * Move to target angle
     */
    public void update() {
        // Set this to negative if PID absolutely not working
        setPowers(pid.getOut(Util.clampAngle(targetAngle - getAngle())), fwdPower);
    }
}