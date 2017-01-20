package com.ulpgc.eii.zah;

import com.ulpgc.eii.zah.Bus.Bus;
import com.ulpgc.eii.zah.Bus.MyBus;
import com.ulpgc.eii.zah.Message.DistanceMessage;
import com.ulpgc.eii.zah.Message.Message;
import com.ulpgc.eii.zah.Publisher.Sensor.MyDistanceSensor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Sensor_ {
    private Bus bus;

    @Before
    public void setUp() throws Exception {
        bus = mock(MyBus.class);
    }

    @Test
    public void should_send_distance_and_speed() throws Exception {
        MyDistanceSensor distanceSensor = new MyDistanceSensor(bus);
        distanceSensor.start();

        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(DistanceMessage.class);
        verify(bus).send(captor.capture());

        for (Message message : captor.getAllValues()) {
            System.out.println(message.type());
        }

    }
}
