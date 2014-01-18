/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.Timer;

class DriveCommand extends CommandBase {

    private double time;
    private double left = 0;
    private double right = 0;

    public DriveCommand(double left, double right, double time) {
        this.time = time;
        this.left = left;
        this.right = right;
        
        requires(drive);
        requires(firing);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        drive.drive(left, right);
        Timer.delay(time);
        drive.drive(0, 0);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}