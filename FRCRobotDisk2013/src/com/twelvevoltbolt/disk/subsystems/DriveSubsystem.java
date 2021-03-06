package com.twelvevoltbolt.disk.subsystems;

import com.twelvevoltbolt.disk.RobotMap;
import com.twelvevoltbolt.disk.commands.CommandBase;
import com.twelvevoltbolt.disk.commands.ArcadeDriveCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * A drive system consisting of 4 motors, 2 on each side with the same output, left and right
 */
public class DriveSubsystem extends Subsystem {

    public CANJaguar leftMotor1;
    public CANJaguar leftMotor2;
    public CANJaguar rightMotor1;
    public CANJaguar rightMotor2;
//    public Jaguar leftMotor1;
//    public Jaguar leftMotor2;
//    public Jaguar rightMotor1;
//    public Jaguar rightMotor2;
    RobotDrive drive;
    
    public DriveSubsystem() {
        try {
            leftMotor1 = new CANJaguar(RobotMap.leftMotor1);
            leftMotor1.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            leftMotor1.configEncoderCodesPerRev(200);
//            leftMotor1.setSpeedReference(CANJaguar.SpeedReference.kQuadEncoder);
        } catch (CANTimeoutException ex) {
            System.out.println("Drive subsystem error");
            ex.printStackTrace();
        }
        
        try {
            leftMotor2 = new CANJaguar(RobotMap.leftMotor2);
            leftMotor2.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            leftMotor2.configEncoderCodesPerRev(200);
        } catch (CANTimeoutException ex) {
            System.out.println("Drive subsystem error");
            ex.printStackTrace();
        }
        
        try {
            rightMotor1 = new CANJaguar(RobotMap.rightMotor1);
            rightMotor1.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            rightMotor1.configEncoderCodesPerRev(200);
        } catch (CANTimeoutException ex) {
            System.out.println("Drive subsystem error");
            ex.printStackTrace();
        }
        
        try {
            rightMotor2 = new CANJaguar(RobotMap.rightMotor2);
            rightMotor2.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            rightMotor2.configEncoderCodesPerRev(200);
        } catch (CANTimeoutException ex) {
            System.out.println("Drive subsystem error");
            ex.printStackTrace();
        }
        
        /*leftMotor1 = new Jaguar(RobotMap.leftMotor1);
        leftMotor2 = new Jaguar(RobotMap.leftMotor2);
        rightMotor1 = new Jaguar(RobotMap.rightMotor1);
        rightMotor2 = new Jaguar(RobotMap.rightMotor2);*/
        
        drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
        
        LiveWindow.addActuator(getName(), "Left Motor 1", leftMotor1);
        LiveWindow.addActuator(getName(), "Left Motor 2", leftMotor2);
        LiveWindow.addActuator(getName(), "Right Motor 1", rightMotor1);
        LiveWindow.addActuator(getName(), "Right Motor 2", rightMotor2);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDriveCommand());
    }
    
    public void stop() {
        drive(0, 0);
    }
    
    public void drive(double magnitude, double angle) {
//        if (CommandBase.oi.isDebug()) {
//            System.out.println("Driving: " + actualLeft + ", " + actualRight);
//        }
        
        drive.arcadeDrive(magnitude, angle);
        //drive.tankDrive(actualLeft, actualRight);
    }
    
    public void arcadeDrive(double magnitude, double angle) {
        drive.arcadeDrive(magnitude * (reversed ? -1 : 1), angle);
    }
    
    private boolean reversed;
    
    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }
    
    public boolean getReversed() {
        return reversed;
    }
    
    public void toggleReversed() {
        setReversed(!getReversed());
    }
    
    
    public void updateGears() {
        try {
            double avg = (leftMotor1.getSpeed());
            
//            if ((avg > speedShiftUp) && !getGear()) {
//                setGear(true);
//                shiftGear(gear);
//                System.out.println("Shifting up " + avg);
//            } else if ((avg < speedShiftDown) && getGear()) {
//                setGear(false);
//                shiftGear(gear);
//                System.out.println("Shifting down " + avg);
//            }
            
            if (CommandBase.oi.isDebug()) {
                System.out.println("Speed: " + avg);
            }
        } catch (CANTimeoutException e) {
            e.printStackTrace();
        }
    }
}
