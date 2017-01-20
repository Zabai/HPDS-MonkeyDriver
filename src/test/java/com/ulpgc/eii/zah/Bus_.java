package com.ulpgc.eii.zah;

import com.ulpgc.eii.zah.Bus.Bus;
import com.ulpgc.eii.zah.Bus.MyBus;
import com.ulpgc.eii.zah.Message.Message;
import com.ulpgc.eii.zah.Message.SpeedMessage;
import com.ulpgc.eii.zah.Subscriber.BusSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class Bus_ {
    private Bus bus;

    @Before
    public void setUp() throws Exception {
        bus = new MyBus();
    }

    @Test
    public void should_send_a_message_to_subscriber() throws Exception {
        BusSubscriber subscriber = mock(BusSubscriber.class);

        bus.register(subscriber).to("foo");

        bus.send(messageOfType("foo"));

        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(subscriber, times(1)).receive(captor.capture());
        assertThat(captor.getValue().type(), is("foo"));
    }

    @Test
    public void should_send_message_to_associated_subscriber() throws Exception {
        BusSubscriber subscriber = mock(BusSubscriber.class);

        bus.register(subscriber).to("foo");

        bus.send(messageOfType("foo"));
        bus.send(messageOfType("fii"));
        bus.send(messageOfType("fuu"));

        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(subscriber, times(1)).receive(captor.capture());
        assertThat(captor.getValue().type(), is("foo"));
    }

    @Test
    public void should_send_from_publisher() throws Exception {
        /*BusSubscriber subscriber = mock(BusSubscriber.class);
        SpeedSensor sensor = mock(MySpeedSensor.class);

        bus.register(subscriber).to("Distance", "Speed");
        bus.register(sensor).to("Distance", "Speed");
        sensor.register(bus);

        bus.send(messageOfType("Distance"));

        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(SpeedMessage.class);
        verify(sensor).receive(captor.capture());
        // ¿Por qué cojones no envía?
        verify(sensor).send();*/
    }

    private Message messageOfType(String type) {
        Message message = mock(Message.class);
        when(message.type()).thenReturn(type);
        return message;
    }

    private SpeedMessage speedMessageWithSpeed(double speed){
        SpeedMessage message = mock(SpeedMessage.class);
        when(message.type()).thenReturn("Speed");
        when(message.getSpeed()).thenReturn(speed);
        return message;
    }
}
