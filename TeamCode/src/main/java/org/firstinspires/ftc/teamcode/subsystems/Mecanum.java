package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.priority.PriorityMotor;
import org.firstinspires.ftc.teamcode.utils.priority.HardwareQueue;

@TeleOp
public class Mecanum extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        HardwareQueue hardwareQueue = new HardwareQueue();
        PriorityMotor frontLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontLeftMotor"), "frontLeftMotor", 1, 1, 1.0);
        PriorityMotor frontRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("frontRightMotor"), "frontRightMotor", 1, 1, -1.0);
        PriorityMotor backLeftMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backLeftMotor"), "backLeftMotor", 1, 1, 1.0);
        PriorityMotor backRightMotor = new PriorityMotor((DcMotorEx) hardwareMap.dcMotor.get("backRightMotor"), "backRightMotor", 1, 1, -1.0);



        hardwareQueue.addDevice(frontLeftMotor);
        hardwareQueue.addDevice(backLeftMotor);
        hardwareQueue.addDevice(frontRightMotor);
        hardwareQueue.addDevice(backRightMotor);

        // By setting these values to new Gamepad(), they will default to all
        // boolean values as false and all float values as 0
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setTargetPower(frontLeftPower);
            backLeftMotor.setTargetPower(backLeftPower);
            frontRightMotor.setTargetPower(frontRightPower);
            backRightMotor.setTargetPower(backRightPower);

            // Store the gamepad values from the previous loop iteration in
            // previousGamepad1/2 to be used in this loop iteration.
            // This is equivalent to doing this at the end of the previous
            // loop iteration, as it will run in the same order except for
            // the first/last iteration of the loop.
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            // Store the gamepad values from this loop iteration in
            // currentGamepad1/2 to be used for the entirety of this loop iteration.
            // This prevents the gamepad values from changing between being
            // used and stored in previousGamepad1/2.
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

//            if(currentGamepad1.a && !previousGamepad1.a){
//                servo.setPosition(servo.getPosition()+0.1);
//            }



            // Main teleop loop goes here

        }


    }
}