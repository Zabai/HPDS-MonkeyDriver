package com.ulpgc.eii.zah.Subscriber;

import com.ulpgc.eii.zah.Message.Message;

public interface BusSubscriber {
    void receive(Message message);
}
