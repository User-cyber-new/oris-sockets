package org.mulyichat.observer.impl;

import org.mulyichat.models.Message;
import org.mulyichat.observer.Observable;
import org.mulyichat.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class BroadcaterClient implements Observable<Message> {

    private List<Observer<Message>> clientsHandlers;

    @Override
    public List<Observer<Message>> getObservers() {
        return clientsHandlers;
    }

    public BroadcaterClient() {
        clientsHandlers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
       clientsHandlers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        clientsHandlers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observer clientHandler: clientsHandlers) {
            clientHandler.update(message);
        }
    }
}
