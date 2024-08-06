package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;

public class Intake {
    public enum CurrState{
        ON,
        OFF,
        REVERSE
    }

    public final PriorityMotor intake;
    public CurrState currState = CurrState.OFF;

    public double intakePower = 1.0;

    public Intake(HardwareMap hardwareMap, HardwareQueue hardwareQueue){
        intake =  new PriorityMotor(hardwareMap.get(DcMotorEx.class, "intake"), "intake", 1, 2, -1);
        this.currState = CurrState.OFF;
    }

    public void update(){
        switch(currState) {
            case ON:
                intake.setTargetPower(intakePower);
                break;
            case OFF:
                intake.setTargetPower(0.0);
                break;
            case REVERSE:
                intake.setTargetPower(-intakePower);
                break;
        }
    }

    public void turnON() {
        currState = CurrState.ON;
    }

    public void turnOFF() {
        currState = CurrState.OFF;
    }

    public void reverse() {
        currState = CurrState.REVERSE;
    }

}
