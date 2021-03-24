package com.kuldeep.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static com.kuldeep.server.ChatClient.MAX_INPUT;

class ServerListener implements Runnable{
    private final InputStream is;
    private final Consumer<String> consumer;

    public ServerListener(InputStream is, Consumer<String> consumer) {
        this.is = is;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        int read;
        try {
            byte[] buf = new byte[MAX_INPUT];
            while ((read = is.read(buf)) != -1) {
                String line = new String(buf, 0, read);
                consumer.accept(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
