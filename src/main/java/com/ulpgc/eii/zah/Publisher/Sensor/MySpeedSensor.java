package com.ulpgc.eii.zah.Publisher.Sensor;

import com.ulpgc.eii.zah.Bus.Bus;
import com.ulpgc.eii.zah.Message.Message;
import com.ulpgc.eii.zah.Message.SpeedMessage;

public class MySpeedSensor implements Sensor{
    private Bus bus;
    private double speed;

    public MySpeedSensor(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void sendMessageToBus() {
        Message message = new SpeedMessage(speed);
        bus.send(message);
        sendMessageToBus();
    }

}
