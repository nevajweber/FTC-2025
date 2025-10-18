package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {
    private DcMotor intakeMotor;
    //declares a variable to represent intake motor
    public Intake(HardwareMap hardwareMap) {
        //constructor (named the same as my class)
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        //connects the code to the motor in Control Hub
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //makes the motor stop instead of roaming around
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //controls motor speed directly without encoder
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        //sets the rotation direction (can switch direction to REVERSE if the motor spins the wrong way).
    }

    public void in() {
        intakeMotor.setPower(1.0);
        //spins the motor forwards (intake in)

    }
    public void out() {
        intakeMotor.setPower(-1.0);
        //spins the motor backwards(outtake)
    }
    public void stop() {
        intakeMotor.setPower(0.0);
        //stops the motor completely

    }



}
