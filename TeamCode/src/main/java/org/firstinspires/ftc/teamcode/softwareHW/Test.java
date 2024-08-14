package org.firstinspires.ftc.teamcode.softwareHW;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.libswerve.PID;
import org.firstinspires.ftc.teamcode.utils.TelemetryUtil;
import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

import java.util.List;


@Config
@TeleOp
public class Test extends LinearOpMode {
    public CRServo servo0;
    public CRServo servo1;

    private long start;

    private List<PriorityMotor> motors;

    public static PID test = new PID(0, 0, 0);


    public static int desiredPosition = 100;

    FtcDashboard dashboard;
    @Override
    public void runOpMode() throws InterruptedException {
        servo0 = hardwareMap.get(CRServo.class, "FrontLeftS");
        servo1 = hardwareMap.get(CRServo.class, "FrontRightS");
        //get our analog input from the hardwareMap
        AnalogInput analogInput0 = hardwareMap.get(AnalogInput.class, "FrontLeftE");
        AnalogInput analogInput1 = hardwareMap.get(AnalogInput.class, "FrontRightE");

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
        TelemetryUtil.setup();

        waitForStart();
        if (isStopRequested()) return;

        double initPos0 = analogInput0.getVoltage() * 2 * (Math.PI / 4.972) * (180/Math.PI);
        double initPos1 = analogInput1.getVoltage() * 2 * (Math.PI / 4.972) * (180/Math.PI);
        int i = 0;

        while(opModeIsActive()){

            double position0 = analogInput0.getVoltage() * ((2 * Math.PI )/ 4.972) * (180/Math.PI) ;
            double position1 = analogInput1.getVoltage() * ((2*Math.PI)/4.972) * (180/Math.PI);

            double justVoltage0 = analogInput0.getVoltage();
            double justVoltage1 = analogInput1.getVoltage();

            double error0 = desiredPosition - position0;
            double error1 = desiredPosition - position1;


            //testing PIDs
            double command0 = test.getOut(error0);
            double command1 = test.getOut(error1);
            servo0.setPower(0.0);
            servo1.setPower(0.0);



            double servoPosition = position0;

            TelemetryUtil.packet.put("Error0", error0);
            TelemetryUtil.packet.put("Encoder Position 0", position0);
            TelemetryUtil.packet.put("Error1", error1);
            TelemetryUtil.packet.put("Encoder Position 1", position1);

            TelemetryUtil.packet.put("just voltage0", justVoltage0);
            TelemetryUtil.packet.put("just voltage1", justVoltage1);



            TelemetryUtil.packet.put("Desired Position", desiredPosition);
            TelemetryUtil.packet.put("Initial Position0", initPos0);
            TelemetryUtil.packet.put("Initial Position1", initPos1);

            TelemetryUtil.sendTelemetry();

            telemetry.update();




        }
    }



    //pw xie1017
}