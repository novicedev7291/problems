package com.kuldeep.server;

import java.io.IOException;

public class ChatApplication {
    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer();
        server.start();
    }
}
