package com.kuldeep.server;

import java.io.PrintWriter;

public class MessageObserver {
    private final PrintWriter os;

    public MessageObserver(PrintWriter os) {
        this.os = os;
    }

    public void notify(String message) {
        os.write(message);
        os.flush();
    }
}
