package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public class FlipServo extends LinearOpMode {

    static final double MAX_POS = 1.0;   // Open position
    static final double MIN_POS = 0.0;   // Closed position

    // Define servo and state variables
    private Servo servo;
    private boolean isOpen = false;       // Track cover state
    private boolean buttonPressed = false; // Track button press

    @Override
    public void runOpMode() {

        // Connect to servo
        servo = hardwareMap.get(Servo.class, "left_hand");
        servo.setPosition(MIN_POS); // Start closed

        telemetry.addData(">", "Press Start to control Servo.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {


            // Display the current cover state
            telemetry.addData("Cover State", isOpen ? "Open" : "Closed");
            telemetry.addData(">", "Press Stop to end test.");
            telemetry.update();
        }
    }
}
