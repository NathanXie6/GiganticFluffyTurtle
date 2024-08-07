package org.firstinspires.ftc.teamcode.softwareHW;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.softwareHW.subsystems.Intake;
import org.firstinspires.ftc.teamcode.softwareHW.subsystems.Mecanum;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

public class Robot {
    public HardwareQueue hardwareQueue;

    public final Mecanum drivetrain;
    public final Intake intake;

    public Robot(HardwareMap hardwareMap){

        drivetrain = new Mecanum(hardwareMap, hardwareQueue);
        intake = new Intake(hardwareMap, hardwareQueue);

    }

    private void updateSubsystems(){
        intake.update();

    }
}
