package org.firstinspires.ftc.teamcode.libswerve;

public final class Pose2d {
    public double x, y, h;

    public Pose2d(double x, double y, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public Pose2d() {
        this.x = this.y = this.h = 0;
    }

    public double distance(Pose2d p) {
        return Math.sqrt(
            Math.pow(p.y - x, 2) +
            Math.pow(p.x - x, 2)
        );
    }

    public String toString() {
        return String.format("(%f, %f, %f)", x, y, h);
    }
}
