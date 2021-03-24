package com.kuldeep.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker implements Runnable {
    private final Integer id;
    private final Socket client;
    private final ClientRepository repository;

    public Worker(Integer id, Socket client, ClientRepository repo) {
        this.id = id;
        this.client = client;
        this.repository = repo;
    }

    @Override
    public void run() {
        joinClient();

        listenClientMessages();

        closeConnection();
    }

    private void joinClient() {
        try {
            MessageObserver observer = new MessageObserver(new PrintWriter(client.getOutputStream(), true));
            String clientId = "client-" + id;

            System.out.printf("%s joined us", clientId);
            System.out.println();

            repository.register(clientId, observer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void listenClientMessages() {
        MessageObserver observer;
        final int MAX_INPUT = 1024;
        int read;
        try (InputStream is = client.getInputStream()) {
            byte[] buf = new byte[MAX_INPUT];
            while ((read = is.read(buf)) != -1) {
                String line = new String(buf, 0, read);

                if (line.equals("bye")) break;

                String[] parts = line.split(",");
                String cID = parts[0];
                String message = parts[1];

                observer = repository.findObserver(cID.trim());
                if (observer == null)
                    System.out.println("Client not found with ID");
                else
                    observer.notify(String.format("%s : %s", "client-" + id, message));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void closeConnection() {
        repository.removeObserver("client-"+id);
        try{
            client.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
