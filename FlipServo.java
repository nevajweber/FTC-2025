
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FlipServo {

    private final Servo flipServo;
    private boolean flippedUp = false;

    // TUNE these positions to match your robot’s mechanism
    private static final double POSITION_DOWN = 0.2; // folded
    private static final double POSITION_UP   = 0.8; //flipped up


    /**
     * "flipServo" MUST match the name in the RC configuration.
     */
    public FlipServo(HardwareMap hardwareMap) {

        flipServo = hardwareMap.get(Servo.class, "flipServo");
    }

    public void toggleFlip() {
        if (flippedUp) {
            flipServo.setPosition(POSITION_DOWN);
            flippedUp = false;

        } else {
            flipServo.setPosition(POSITION_UP);
            flippedUp = true;
        }
    }

    //servo state
public boolean isFlippedUp() {
    return flippedUp;
}

public double getPositon() {
    return flipServo.getPosition();
    }
}
