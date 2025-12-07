package org.mulyichat.observer.impl;

import org.mulyichat.models.Message;
import org.mulyichat.observer.Observer;
import org.mulyichat.server.ChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable, Observer<Message> {
    private ChatServer server;
    private Socket socketClient;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(ChatServer server, Socket socketClient) {
        this.server = server;
        this.socketClient = socketClient;
        try {
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            out = new PrintWriter(socketClient.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // передать ListenClient...

    @Override
    public void run() {
        Message message;
        try {
            while((message = new Message(in.readLine()))!=null){

                sendToEveryone(message);
                // почему-то рассылается далеко не всем пользователям(((
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendToEveryone(Message message) {
        server.getObservable().notifyObservers(message);
    }

    @Override
    public void update(Message message) {
        sendMessageToChatClient(message);
    }

    private void sendMessageToChatClient(Message message){
        out.println("сообщение от сервера: " + message.getText());
        out.flush();
    }
}
