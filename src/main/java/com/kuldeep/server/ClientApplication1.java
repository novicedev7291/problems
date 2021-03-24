package com.kuldeep.server;

import java.io.IOException;

public class ClientApplication1 {
    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient();
        client.start();
    }
}
