package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class OuttakeTeleOp {
    private final DcMotor outtakeMotor;
    //declares a variable to represent intake motor
    public OuttakeTeleOp(@NonNull HardwareMap hardwareMap) {

        //constructor (named the same as my class)
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtake");
        //connects the code to the motor in Control Hub
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //makes the motor stop
        outtakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //controls motor speed directly without encoder
        outtakeMotor.setDirection(DcMotor.Direction.FORWARD);
        //sets the rotation direction (can switch direction to REVERSE if the motor spins the wrong way).
    }

    public void in() {
        outtakeMotor.setPower(1.0);
        //spins the motor forwards  (out)

    }
    public void out() {
        outtakeMotor.setPower(-1.0);
        //spins the motor backwards(in)
    }
    public void stop() {
        outtakeMotor.setPower(0.0);
        //stops the motor completely

    }



}