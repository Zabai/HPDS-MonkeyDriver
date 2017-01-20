package com.ulpgc.eii.zah.Publisher.Sensor;

import com.ulpgc.eii.zah.Bus.Bus;
import com.ulpgc.eii.zah.Message.DistanceMessage;
import com.ulpgc.eii.zah.Message.Message;


public class MyDistanceSensor implements Sensor {
    private Bus bus;
    private double distance = 0;
    private final double safeDistance = 25.;

    public MyDistanceSensor(Bus bus) {
        this.bus = bus;
    }

    public void start(){
        Thread thread = new Thread(){
            @Override
            public void run() {sendMessageToBus();
            }
        };
        thread.start();
    }

    public double getDistanceFromSensor(){
        int min = 20, max = 30;
        distance = (Math.random() * ((max - min) + 1)) + min;
        return distance;
    }

    @Override
    public void sendMessageToBus() {
        while(true){
            Message message = new DistanceMessage(getDistanceFromSensor());
            if(distance < safeDistance) bus.send(message);
        }
    }
}
