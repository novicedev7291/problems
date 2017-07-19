package com.kuldeep.server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertTrue;

public class SocketServerTest {
    private SocketServer socketServer;
    private SocketService socketService;
    private int port = 8081;

    @Before
    public void setUp() throws Exception {
        socketService = new EchoService();
        socketServer = new SocketServer(port, socketService);
    }

    @After
    public void tearDown() throws Exception {
        socketServer.stop();
    }

    @Test
    public void testWithSimpleRequest() throws IOException, InterruptedException {
        socketServer.start();
        Socket s = new Socket("localhost", port);
        assertTrue(socketServer.isRunning());
    }

    @Test
    public void canEcho() throws Exception {
        socketServer.start();
        Thread.sleep(10);
        Socket s = new Socket("localhost", port);
        //Send message
        OutputStream os = s.getOutputStream();
        String message1 = "Hello\n";
        os.write(message1.getBytes());
        os.flush();

        //Receive the message
        String message = new BufferedReader(new InputStreamReader(s.getInputStream())).readLine();

        assertTrue(message1.substring(0, message1.length()-1).equals(message));
    }
}

class EchoService extends SocketService{
    @Override
    public void serve(Socket serviceSocket) {
        //Read the message
        try {
            String message = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream())).readLine();
            //System.out.println(message);

            //Reply to the message
            OutputStream os = serviceSocket.getOutputStream();
            os.write(message.getBytes());
            os.flush();
            serviceSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}