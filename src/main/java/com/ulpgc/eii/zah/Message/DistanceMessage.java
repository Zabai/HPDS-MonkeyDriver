package com.ulpgc.eii.zah.Message;

public class DistanceMessage implements Message{
    private double distance;

    public DistanceMessage(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String type() {
        return "Distance";
    }
}
