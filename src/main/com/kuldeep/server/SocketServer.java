package com.kuldeep.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by mario on 18/07/17.
 */
public class SocketServer {
    private ServerSocket serverSocket;

    public void setSocketService(SocketService socketService) {
        this.socketService = socketService;
    }

    private SocketService socketService;
    private boolean isRunning;
    private ExecutorService executor;

    public SocketServer(int port, SocketService socketService) throws IOException {
        serverSocket = new ServerSocket(port);
        this.socketService = socketService;
        executor = Executors.newFixedThreadPool(4);
    }

    public void start() throws IOException {
        Runnable connectionHandler = () -> {
            try{
                while (isRunning){
                    Socket serviceSocket = serverSocket.accept();
                    executor.execute(() -> {
                        socketService.serve(serviceSocket);
                    });
                    Thread.sleep(10);
                }
            }catch (IOException e){;
                if(isRunning)
                    e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        executor.execute(connectionHandler);
        isRunning = true;
    }

    public void stop() throws IOException, InterruptedException {
        isRunning = false;
        serverSocket.close();
        executor.shutdown();
        executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
    }

    public boolean isRunning() {
        return isRunning;
    }
}
