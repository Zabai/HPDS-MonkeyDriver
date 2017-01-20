package com.ulpgc.eii.zah.Message;

public class SpeedMessage implements Message{
    private double speed;

    public SpeedMessage(double speed){
        this.speed = speed;
    }

    public double getSpeed(){
        return speed;
    }

    @Override
    public String type() {
        return "Speed";
    }
}
