package com.kuldeep.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatServer {
    public static final Integer port = 9000;
    private final ServerSocket server;
    private final ClientRepository repository = new ClientRepository();
    private final ExecutorService service = Executors.newFixedThreadPool(10);
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    public ChatServer() throws IOException {
        this.server = new ServerSocket(port);
    }

    public void start() {
        try {
            while (true) {
                Worker worker = new Worker(idGenerator.incrementAndGet(), server.accept(), repository);
                service.execute(worker);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
