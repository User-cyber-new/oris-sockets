package org.mulyichat.ui;

import org.mulyichat.models.Message;

public interface InterfaceUI {
    void printMessage(Message msg);
    void inputMessage();
    Message getMessage();
}
