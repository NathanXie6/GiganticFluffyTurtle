package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import android.util.Log;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.libswerve.PID;
import org.firstinspires.ftc.teamcode.utils.Encoder;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

import java.util.List;


@Config
@TeleOp
public class Test extends LinearOpMode {
    public CRServo servo;

    private long start;

    private List<PriorityMotor> motors;

    public static PID test = new PID(0.02, 0, 0.4);

    public static int desiredPosition = 200;

    FtcDashboard dashboard;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(CRServo.class, "FrontLeftS");
        //get our analog input from the hardwareMap
        AnalogInput analogInput = hardwareMap.get(AnalogInput.class, "FrontLeftE");

    // get the voltage of our analog line
    // divide by 3.3 (the max voltage) to get a value between 0 and 1
    // multiply by 360 to convert it to 0 to 360 degrees

        dashboard = FtcDashboard.getInstance();
        HardwareQueue hardwareQueue = new HardwareQueue();

//        PriorityMotor frontLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 1, 1.0);
//        PriorityMotor frontRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontRightMotor"), "frontRightMotor", 1, 1, -1.0);
//        PriorityMotor backLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor"), "backLeftMotor", 1, 1, 1.0);
//        PriorityMotor backRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backRightMotor"), "backRightMotor", 1, 1, -1.0);
//


//        hardwareQueue.addDevice(frontLeftMotor);
//        hardwareQueue.addDevice(backLeftMotor);
//        hardwareQueue.addDevice(frontRightMotor);
//        hardwareQueue.addDevice(backRightMotor);
//
//        frontLeftMotor.motor[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRightMotor.motor[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeftMotor.motor[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRightMotor.motor[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        frontLeftMotor.motor[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        frontRightMotor.motor[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        backLeftMotor.motor[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        backRightMotor.motor[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        //servo.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) return;

        double prev = 0;
        double prevVelo = 0;
        int i = 0;
        while(opModeIsActive()){
            // servo.setPower(0.5);
            double position = analogInput.getVoltage() / 3.3 * 360;

            double initAccel = 0;
            double now = System.currentTimeMillis();

            //testing PIDs
            double command = test.getOut(desiredPosition - position);
            servo.setPower(command);

        //    frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//
//            double servoPosition = position;
//            double velocity = servo.getVelocity();


        //    telemetry.addData("Velocity", velocity);
            telemetry.addData("Encoder Position", position);
            telemetry.addData("Desired Position", desiredPosition);

            telemetry.update();




        }
    }


    //pw xie1017
}