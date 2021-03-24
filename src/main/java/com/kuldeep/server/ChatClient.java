package com.kuldeep.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private final Socket ss;
    private final PrintWriter writer;
    private final InputStream is;
    public static final int MAX_INPUT = 1024;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ChatClient() throws IOException {
        this.ss = new Socket("localhost", ChatServer.port);
        writer = new PrintWriter(ss.getOutputStream(), true);
        is = ss.getInputStream();
    }

    public void start() {
        new Thread(new ServerListener(is, this::printMessage)).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            sendMessage(message);
            if (message.equals("bye")) break;
        }

        clearResources();
    }

    private void clearResources() {
        try{
            writer.close();
            is.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void printMessage(String message) {
        log.info(message);
    }

    private void sendMessage(String message) {
        new Thread(() -> {
            writer.write(message);
            writer.flush();
        }).start();
    }
}
