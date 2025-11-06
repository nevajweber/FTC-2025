package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Servo Test OpMode", group="FTC")
public class DriveTeleOp extends LinearOpMode {

    private Servo myServo;

    @Override
    public void runOpMode() {
        // Initialize the servo
        myServo = hardwareMap.get(Servo.class, "myServo"); // "myServo" must match configuration

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Control servo position with gamepad buttons
            if (gamepad2.a) {
                myServo.setPosition(0.0); // Move to position 0
            } else if (gamepad2.b) {
                myServo.setPosition(0.5); // Move to position 0.5
            } else if (gamepad2.x) {
                myServo.setPosition(1.0); // Move to position 1
            }

            // Display servo position on Driver Station
            telemetry.addData("Servo Position", myServo.getPosition());
            telemetry.update();
        }
    }
}
