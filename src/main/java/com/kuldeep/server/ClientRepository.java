package com.kuldeep.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientRepository {
    private final Map<String, MessageObserver> clientMap = new ConcurrentHashMap<>();

    public void register(String id, MessageObserver observer) {
        clientMap.put(id, observer);
    }

    public MessageObserver findObserver(String id) {
        return clientMap.get(id);
    }

    public void removeObserver(String id) {
        clientMap.remove(id);
    }
}
