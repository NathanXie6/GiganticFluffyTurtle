package org.firstinspires.ftc.teamcode.libswerve;

public interface Localizer {
    void setPoseEstimate(Pose2d p);
    Pose2d getPoseEstimate();
    void update();
}