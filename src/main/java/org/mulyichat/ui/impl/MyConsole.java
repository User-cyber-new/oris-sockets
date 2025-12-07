package org.mulyichat.ui.impl;

import org.mulyichat.client.ChatClient;
import org.mulyichat.models.Message;
import org.mulyichat.ui.InterfaceUI;

import java.io.Console;
import java.util.Scanner;

public class MyConsole implements InterfaceUI {
    private Message message;
    private Scanner scanner;

    public MyConsole() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void printMessage(Message message) {
        System.out.println(message.getText());
    }

    @Override
    public void inputMessage() {

        message = new Message(scanner.nextLine());
    }

    @Override
    public Message getMessage() {
        return message;
    }

}
