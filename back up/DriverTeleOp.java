
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="DriverTeleOp", group="Linear OpMode")

public class DriverTeleOp extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FLW = null;
    private DcMotor FRW = null;
    private DcMotor BLW = null;
    private DcMotor BRW = null;

    private IntakeTeleOp intake;
    private OuttakeTeleOp outtake;

    private Servo leftCoverServo;
    private Servo rightCoverServo;
    private boolean isOpen = false;
    private boolean buttonPressed = false;

    private static final double MAX_POS = 1.0; // fully open
    private static final double MIN_POS = 0.0; // fully closed


    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the Driver Station.
        FLW  = hardwareMap.get(DcMotor.class, "FLW");
        FRW = hardwareMap.get(DcMotor.class, "FRW");
        BLW  = hardwareMap.get(DcMotor.class, "BLW");
        BRW = hardwareMap.get(DcMotor.class, "BRW");

        FLW.setDirection(DcMotor.Direction.REVERSE);
        BLW.setDirection(DcMotor.Direction.REVERSE);
        FRW.setDirection(DcMotor.Direction.FORWARD);
        BRW.setDirection(DcMotor.Direction.FORWARD);

        FLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake = new IntakeTeleOp(hardwareMap);
        outtake = new OuttakeTeleOp(hardwareMap);

        //in configuration it is called coverLeft, and coverRight
        leftCoverServo = hardwareMap.get(Servo.class, "coverLeft");
        rightCoverServo = hardwareMap.get(Servo.class, "coverRight");

        leftCoverServo.setPosition(MIN_POS);
        rightCoverServo.setPosition(MIN_POS);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double frontLeftPower  = axial + lateral + yaw;
            double frontRightPower = axial - lateral - yaw;
            double backLeftPower   = axial - lateral + yaw;
            double backRightPower  = axial + lateral - yaw;



            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {
                frontLeftPower  /= max;
                frontRightPower /= max;
                backLeftPower   /= max;
                backRightPower  /= max;
            }

            // Send calculated power to wheels
            FLW.setPower(frontLeftPower);
            FRW.setPower(frontRightPower);
            BLW.setPower(backLeftPower);
            BRW.setPower(backRightPower);

            if (gamepad2.right_trigger > 0.1) {
                intake.in(); //right trigger causes the intake motor spin in(forwards)

            } else if (gamepad2.left_trigger > 0.1){
                intake.out(); //left trigger causes the intake motor to spin out(backwards)

            } else {
                intake.stop(); //none of the triggers are pressed then nothing happens(stops)!
            }

            if (gamepad2.right_bumper) {
                outtake.in(); //right trigger causes the intake motor spin in(forwards)

            } else if (gamepad2.left_bumper){
                outtake.out(); //left trigger causes the intake motor to spin out(backwards)

            } else {
                outtake.stop(); //none of the triggers are pressed then nothing happens(stops)!
            }

            // Cover flipper control
            if (gamepad2.a) {  // press A to flip
                if (!buttonPressed) {  // only trigger once per press
                    buttonPressed = true;

                    if (!isOpen) {
                        leftCoverServo.setPosition(MAX_POS);
                        rightCoverServo.setPosition(MIN_POS);
                        isOpen = true;
                    } else {
                        leftCoverServo.setPosition(MIN_POS);
                        rightCoverServo.setPosition(MAX_POS);
                        isOpen = false;
                    }
                }
            } else {
                buttonPressed = false; // reset for next press
            }


            // Show the elapsed time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", frontLeftPower, frontRightPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", backLeftPower, backRightPower);
            telemetry.addData("Cover", isOpen ? "Open" : "Closed");
            telemetry.update();
        }
    }
}
