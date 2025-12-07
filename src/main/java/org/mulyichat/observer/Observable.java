package org.mulyichat.observer;

import java.util.List;

public interface Observable<T> {
    List<Observer<T>> getObservers();
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(T data);
}
