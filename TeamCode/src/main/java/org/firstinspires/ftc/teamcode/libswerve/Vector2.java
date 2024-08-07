package org.firstinspires.ftc.teamcode.libswerve;

public class Vector2 {
    public double x;
    public double y;
    private double magcache;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        x = y = 0;
    }

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public double mag() {
        if (magcache == 0 && (x != 0 || y != 0)) {
            magcache = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }

        return magcache;
    }

    public void mul(double a) {
        x *= a;
        y *= a;
    }

    public void norm() {
        double mag = mag();

        if (mag == 0) {
            return;
        }

        x /= mag;
        y /= mag;
    }

    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}
