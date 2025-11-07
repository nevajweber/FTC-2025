//package org.firstinspires.ftc.teamcode.util;

package org.firstinspires.ftc.teamcode;

public class PIDController {
    private double kP, kI, kD;

    private double integralSum = 0;
    private double lastError = 0;
    private Double lastTime = null;  // use Double so we can detect "first run"

    // Optional: anti-windup
    private double integralMax = 1.0;   // tune if you use Ki

    public PIDController(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public void setCoefficients(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public void reset() {
        integralSum = 0;
        lastError = 0;
        lastTime = null;
    }

    /**
     * Call this every loop.
     * @param setpoint      target value (ticks, degrees, etc.)
     * @param measurement   current value from sensor
     * @param currentTimeS  current runtime in SECONDS (from ElapsedTime or getRuntime())
     * @return              PID output (you send this to motor power or turn it into power)
     */
    public double calculate(double setpoint, double measurement, double currentTimeS) {
        double error = setpoint - measurement;

        double dt;
        if (lastTime == null) {
            dt = 0;
            lastTime = currentTimeS;
        } else {
            dt = currentTimeS - lastTime;
            if (dt <= 0) dt = 1e-6;  // avoid divide-by-zero issues
        }

        // Proportional
        double pTerm = kP * error;

        // Integral (only if Ki != 0)
        integralSum += error * dt;
        // simple anti-windup clamp
        if (integralSum > integralMax) integralSum = integralMax;
        if (integralSum < -integralMax) integralSum = -integralMax;
        double iTerm = kI * integralSum;

        // Derivative (rate of change of error)
        double derivative = (error - lastError) / dt;
        double dTerm = kD * derivative;

        // Store for next cycle
        lastError = error;
        lastTime = currentTimeS;

        return pTerm + iTerm + dTerm;
    }
}

