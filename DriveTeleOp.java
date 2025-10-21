package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "DriveTeleOp", group = "LinearOpMode")
public class DriveTeleOp extends LinearOpMode {

    // Declare Motors
    private DcMotor FLW, FRW, BLW, BRW;
    private Intake intake;
    //declares the intake object...!

    @Override
    public void runOpMode() throws InterruptedException {

        FRW = hardwareMap.get(DcMotor.class, "FRW");
        FLW = hardwareMap.get(DcMotor.class, "FLW");
        BRW = hardwareMap.get(DcMotor.class, "BRW");
        BLW = hardwareMap.get(DcMotor.class, "BLW");

        FRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FRW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLW.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRW.setDirection(DcMotor.Direction.FORWARD);
        BRW.setDirection(DcMotor.Direction.REVERSE);
        FLW.setDirection(DcMotor.Direction.REVERSE);
        BLW.setDirection(DcMotor.Direction.FORWARD);

        intake = new Intake(hardwareMap);
        //creates intake object, linking to the motor in the configuration...!

        this.telemetry.addData("Status", "Initialized");
        this.telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double leftPower = -gamepad1.left_stick_y;
            double rightPower = -gamepad1.right_stick_y;

            FLW.setPower(leftPower);
            BLW.setPower(leftPower);
            FRW.setPower(rightPower);
            BRW.setPower(rightPower);


//intake control
            if (gamepad1.right_trigger > 0.1) {
                intake.in(); //right trigger causes the intake motor spin in(forwards)
                
            } else if (gamepad1.left_trigger > 0.1){
                intake.out(); //left trigger causes the intake motor to spin out(backwards)
           
            } else {
                intake.stop(); //none of the triggers are pressed then nothing happens(stops)...!
            }
                
            if (gamepad1.a){
                telemetry.addData("Gamepad1", "A pressed");
                // do a bunch of stuff
            }else if (gamepad1.b){
                telemetry.addData("Gamepad1", "B pressed");
            }

            if(gamepad2.a) {
                telemetry.addData("Gamepad2", "A pressed");
            } else {
                telemetry.addData("Gamepad2", "A not pressed");
            }

            telemetry.addData("GamePad1 A Pressed", gamepad1.a);
            telemetry.addData("Left Power", leftPower);
            telemetry.addData("Right Power", rightPower);
            telemetry.update();
        }
    }

}



