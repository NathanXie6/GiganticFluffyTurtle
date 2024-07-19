package org.firstinspires.ftc.teamcode.libswerve;

import java.util.LinkedList;

public final class Path {
    protected final LinkedList<Pose2d> points = new LinkedList<>();
    public double threshold;

    public Path() {}

    public Pose2d end() {
        return points.getLast();
    }

    /**
     * Create a new spline between two poses <b>(splines heading)</b><br>
     * Derived in <code>https://www.desmos.com/calculator/e8hut5lqcm</code>
     * @param d distance scalar (check d0 and d1 from desmos graph)
     * @param pointDistance distance between each point (inches)
     */
    public void addSpline(Pose2d p0, Pose2d p1, double d, double pointDistance) {
        Pose2d last = p0;
        double distance = Math.sqrt(Math.pow(p0.x - p1.x, 2) + Math.pow(p0.y - p1.y, 2));
        double x0 = distance * Math.cos(p0.h) * d + p0.x;
        double y0 = distance * Math.sin(p0.h) * d + p0.y;
        double x1 = distance * Math.cos(p0.h) * d + p1.x;
        double y1 = distance * Math.sin(p0.h) * d + p1.y;
        for (double t = 0; t < 1; t += 0.001) {
            Pose2d newPose = new Pose2d(
                p0.x + 3 * Math.pow(t, 2) * x1 - Math.pow(t, 3) * p0.x - 3 * Math.pow(t, 3) * x1 + 3 * t * x0 + 3 * Math.pow(t, 3) * x0 + Math.pow(t, 3) * p1.x + 3 * Math.pow(t, 2) * p0.x - 6 * Math.pow(t, 2) * p1.x - 3 * t * p0.x,
                p0.y + 3 * Math.pow(t, 2) * y1 - Math.pow(t, 3) * p0.y - 3 * Math.pow(t, 3) * y1 + 3 * t * y0 + 3 * Math.pow(t, 3) * y0 + Math.pow(t, 3) * p1.y + 3 * Math.pow(t, 2) * p0.y - 6 * Math.pow(t, 2) * p1.y - 3 * t * p0.y,
                0
            );

            if (last.distance(newPose) > pointDistance) {
                newPose.h = Math.atan2(
                    -9 * y1 * Math.pow(t, 2) + 9 * y0 * Math.pow(t, 2) + 3 * p1.y * Math.pow(t, 2) - 3 * p0.y * Math.pow(t, 2) + 6 * y1 * t - 12 * y0 * t + 6 * p0.y * t + 3 * y0 - 3 * p0.y,
                    -9 * x1 * Math.pow(t, 2) + 9 * x0 * Math.pow(t, 2) + 3 * p1.x * Math.pow(t, 2) - 3 * p0.x * Math.pow(t, 2) + 6 * x1 * t - 12 * x0 * t + 6 * p0.x * t + 3 * x0 - 3 * p0.x
                );
                last = newPose;
                points.add(newPose);
            }
        }
    }
}