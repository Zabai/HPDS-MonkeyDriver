package com.ulpgc.eii.zah.Bus;

import com.ulpgc.eii.zah.Message.Message;
import com.ulpgc.eii.zah.Subscriber.BusSubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyBus implements Bus {

    private HashMap<String, List<BusSubscriber>> subscribers = new HashMap<>();

    @Override
    public Subscription register(BusSubscriber subscriber) {
        return types -> {
            for (String type : types) {
                subscribersOf(type).add(subscriber);
            }
        };
    }

    @Override
    public void send(Message message) {
        subscribersOf(message.type()).forEach(busSubscriber -> busSubscriber.receive(message));
    }

    private List<BusSubscriber> subscribersOf(String type){
        createIfNotExistSubscriberOf(type);
        return subscribers.get(type);
    }

    private void createIfNotExistSubscriberOf(String type) {
        if(subscribers.containsKey(type)) return;
        subscribers.put(type, new ArrayList<>());
    }
}
