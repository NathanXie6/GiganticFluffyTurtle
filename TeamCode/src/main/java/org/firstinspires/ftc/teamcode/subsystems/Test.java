package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import android.util.Log;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Test extends LinearOpMode {
    public CRServo servo;
    private long start;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(CRServo.class, "test");


        DcMotorEx frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotorEx testMotor = hardwareMap.get(DcMotorEx.class, "testMotor");
        DcMotorEx poopMotor = hardwareMap.get(DcMotorEx.class, "poopMotor");

        servo.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) return;

        double prev = 0;
        double prevPos = 0;
        while(opModeIsActive()){
            // servo.setPower(0.5);
            frontLeftMotor.setVelocity(0.1);
            frontLeftMotor.setPower(0.1);

            testMotor.setPower(0.1);
            poopMotor.setPower(0.1);

            double velo = frontLeftMotor.getVelocity();
            double velo2 = testMotor.getVelocity();

            Log.i("velocity", String.valueOf(velo));
            //code for deriving velocity from position (old and not used)
//            double now = System.currentTimeMillis();
//
//            frontLeftMotor.setPower(0.1);
//            int newPos = frontLeftMotor.getCurrentPosition();
//
//            testMotor.setPower(0.1);
//            poopMotor.setPower(0.1);
//
//            double elMillis =  now - prev;
//            double elSec = elMillis / 1000;
//            double elPos = newPos - prevPos;
//            double velocity = elPos/ elSec;
//
//            prev = now;
//            prevPos = newPos;
//
//            Log.i("elTime", String.valueOf(elPos));

//            double time_curr = System.currentTimeMillis();
//            double time_new = 0;
//            while(time_curr < )

            // servo.setDirection(DcMotorSimple.Direction.FORWARD);

//            servo.setPower(-1);
        }
    }

//    public void pause (double milliseconds) {
//        start = System.currentTimeMillis();
//        while (System.currentTimeMillis() - start < milliseconds && opModeIsActive()) {
//            servo.update();
//        }
//    }
//
//    public void update() {
//        START_LOOP();
//        Globals.gotBloodyAnnihilated = System.currentTimeMillis() - Globals.autoStartTime > 29500 && Globals.autoStartTime != -1 && deposit.slides.length > 1;
//        updateSubsystems();
//        updateTelemetry();
//    }

    //testing everytioasdfkhasdf
    //testing push
    //testing push clone

    //pw xie1017
}