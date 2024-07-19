package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImplEx;

@Autonomous(name = "Get Angles", group = "tests")
public class GetAngles extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        CRServoImplEx[] servos = new CRServoImplEx[] {
            hardwareMap.get(CRServoImplEx.class, "FrontLeftS"),
            hardwareMap.get(CRServoImplEx.class, "FrontRightS"),
            hardwareMap.get(CRServoImplEx.class, "BackRightS"),
            hardwareMap.get(CRServoImplEx.class, "BackLeftS")
        };
        AnalogInput[] encoders = new AnalogInput[] {
            hardwareMap.get(AnalogInput.class, "FrontLeftE"),
            hardwareMap.get(AnalogInput.class, "FrontRightE"),
            hardwareMap.get(AnalogInput.class, "BackRightE"),
            hardwareMap.get(AnalogInput.class, "BackLeftE")
        };

        waitForStart();

        while (opModeIsActive()) {
            for (int i = 0; i < servos.length; i++) {
                servos[i].setPower(0); // Power needs to be sent to get something from absolute encoders
                System.out.println(i + " " + encoders[i].getVoltage() / 3.3 * Math.PI * 2);
            }
        }
    }
}
