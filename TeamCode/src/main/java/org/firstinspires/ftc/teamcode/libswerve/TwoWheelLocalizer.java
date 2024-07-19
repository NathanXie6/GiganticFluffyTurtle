package org.firstinspires.ftc.teamcode.libswerve;

// Shamelessly copied from pj!
public abstract class TwoWheelLocalizer implements Localizer {
    private Pose2d p;
    private final double fwdy;
    private final double strx;
    protected double dfwdEncoder, dstrEncoder, dTheta;

    public TwoWheelLocalizer(Pose2d p, double fwdy, double strx) {
        this.fwdy = fwdy;
        this.strx = strx;
        this.p = p;
    }

    @Override
    public Pose2d getPoseEstimate() {
        return p;
    }

    @Override
    public void update() {
        // In this case we should always use deltas
        double dFwd = dfwdEncoder - fwdy * dTheta;
        double dStr = dstrEncoder - strx * dTheta;
        p.h += dTheta;

        // Convert it all from relative to global ( this code is for without constant velocity arcs)
        p.x += dFwd * Math.cos(p.h) - dStr * Math.sin(p.h);
        p.y += dStr * Math.cos(p.h) + dFwd * Math.sin(p.h);

        // Heres the version with constant velocity arcs
        /*double r0;
        double r1;
        if (dTheta == 0) {
            r0 = r1 = 0;
        } else {
            r0 = dFwd / dTheta;
            r1 = dStr / dTheta;
        }

        double reldx = r0 * Math.sin(dTheta) - r1 * (1 - Math.cos(dTheta));
        double reldy = r1 * Math.sin(dTheta) + r0 * (1 - Math.cos(dTheta));

        // Now just convert relative to global
        p.x += reldx * Math.cos(p.h) - reldy * Math.sin(p.h);
        p.y += reldy * Math.cos(p.h) - reldx * Math.sin(p.h);*/
    }

    @Override
    public void setPoseEstimate(Pose2d p) {
        this.p = p;
    }

    // Used by the user to set encoder values for computation
    public abstract void updateValues(double dfwdEncoder, double dstrEncoder, double dTheta);
}
