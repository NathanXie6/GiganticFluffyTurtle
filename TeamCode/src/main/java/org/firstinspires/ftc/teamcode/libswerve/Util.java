package org.firstinspires.ftc.teamcode.libswerve;

public final class Util {
    // Gluten Free's clip angle function. FIXME pretty unoptimized
    public static double clampAngle(double angle) {
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }

        return angle;
    }
}
