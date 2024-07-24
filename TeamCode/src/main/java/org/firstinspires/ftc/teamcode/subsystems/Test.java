package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import android.util.Log;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.libswerve.PID;
import org.firstinspires.ftc.teamcode.utils.Encoder;

@Config
@TeleOp
public class Test extends LinearOpMode {
    public CRServo servo;
    private long start;

    public static PID test = new PID(1, 0, 0.5);

    public static int desiredPosition = 200;

    FtcDashboard dashboard;
    @Override
    public void runOpMode() throws InterruptedException {
//        servo = hardwareMap.get(CRServo.class, "test");


        dashboard = FtcDashboard.getInstance();

        DcMotorEx frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        DcMotorEx frontRightMotor = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
        DcMotorEx backLeftMotor = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
        DcMotorEx backRightMotor = hardwareMap.get(DcMotorEx.class, "backRightMotor");

        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //servo.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) return;

        double prev = 0;
        double prevVelo = 0;
        int i = 0;
        while(opModeIsActive()){
            // servo.setPower(0.5);

            double initAccel = 0;
            double now = System.currentTimeMillis();


            double command = test.getOut(desiredPosition - frontLeftMotor.getCurrentPosition());
            frontLeftMotor.setTargetPosition(desiredPosition);
            frontLeftMotor.setPower(command);

//            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            double position = frontLeftMotor.getCurrentPosition();
            double velocity = frontLeftMotor.getVelocity();


            telemetry.addData("Velocity", velocity);
            telemetry.addData("Encoder Position", position);
            telemetry.addData("Desired Position", desiredPosition);

            telemetry.update();



        }
    }


    //pw xie1017
}