package org.mulyichat;

import org.mulyichat.client.ChatClient;

import java.io.IOException;

public class Client1 {
    public static void main(String[] args) {
        try {
            new ChatClient().begin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
