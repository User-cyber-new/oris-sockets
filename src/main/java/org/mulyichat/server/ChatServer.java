package org.mulyichat.server;

import org.mulyichat.observer.impl.ClientHandler;
import org.mulyichat.models.Message;
import org.mulyichat.observer.Observable;
import org.mulyichat.observer.impl.BroadcaterClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private ServerSocket serverSocket;
    private Observable<Message> observable;

    public ChatServer() {
        try {
            this.serverSocket = new ServerSocket(4004);
            this.observable = new BroadcaterClient();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void connectClients() {
        try {
            Socket socketClient = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(this, socketClient);
            observable.addObserver(clientHandler);

            Thread connectionWithClient = new Thread(clientHandler);
            connectionWithClient.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Observable getObservable() {
        return observable;
    }


    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();

        while (true){
            chatServer.connectClients();
        }
    }

}
