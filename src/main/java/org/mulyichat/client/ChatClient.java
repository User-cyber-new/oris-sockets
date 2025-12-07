package org.mulyichat.client;

// идея клиентов много. Клиент - наблюдатель. Сервер - оповещатель. Обновления должны получать все наблюдатели - клиенты.



import org.mulyichat.models.Message;
import org.mulyichat.ui.InterfaceUI;
import org.mulyichat.ui.impl.MyConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient{

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    private InterfaceUI myConsole;

    public ChatClient() throws IOException {
        initSocket();
    }

    public void begin(){
        printMessage();
        writeMessageAndSendToServer();
    }

    public void writeMessageAndSendToServer(){
        while(true){
            System.out.println("Client, Sending Message please: ");
            myConsole.inputMessage();

            sendMessageToServer();

            if (myConsole.getMessage().equals("q")){
                break;
            }
        }
    }

    private void sendMessageToServer() {
        out.println(
                myConsole.getMessage().getText()
        );
        out.flush();
    }

    public void printMessage(){
        //this.myConsole = new MyConsole();

        Thread thread = new Thread(() -> {
            Message message;
            while ((message = getMessageFromServer())!=null){
                myConsole.printMessage(message);
            }
            System.out.println("no message...");

        }
        );

        thread.start();

    }


    private Message getMessageFromServer() {
        try {
            return new Message(
                    in.readLine()
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initSocket() {
        try {
            this.myConsole = new MyConsole();

            this.clientSocket = new Socket("localhost", 4004);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream(), true); // и автоматический перенос строки '/n'

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


}
