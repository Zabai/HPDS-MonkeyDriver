package com.ulpgc.eii.zah.Bus;

import com.ulpgc.eii.zah.Message.Message;
import com.ulpgc.eii.zah.Subscriber.BusSubscriber;

public interface Bus {
    Subscription register(BusSubscriber subscriber);
    void send(Message message);

    interface Subscription {
        void to(String... types);
    }
}
