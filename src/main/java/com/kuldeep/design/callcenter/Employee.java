package com.kuldeep.design.callcenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
abstract class Employee {
    private final String name;
    private final Role role;
    private EmployeeState state = EmployeeState.IDLE;
    private Call currentCall;
    private CallEscalationObserver escalationObserver;

    private static final Logger log = LoggerFactory.getLogger(Employee.class.getName());

    protected Employee(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    protected void register(CallEscalationObserver observer) {
        escalationObserver = observer;
    }

    public synchronized boolean assign(Call call) {
        if (isBusy()) {
            log.trace("{} is busy, so escalating for someone else to handle", name);
            escalationObserver.notifyEscalation(call);
            return false;
        }
        log.info("{}({}) is taking this call", name,  role);
        this.currentCall = call;
        call.changeTo(CallState.PROCESSING);
        state = EmployeeState.BUSY;
        return true;
    }

    public synchronized void free() {
        log.info("{}({}) has completed the call", name,  role);
        state = EmployeeState.IDLE;
        currentCall.changeTo(CallState.COMPLETED);
        currentCall = null;
    }

    private synchronized boolean isBusy() {
        return state == EmployeeState.BUSY;
    }

    public Call currentCall() {
        return currentCall;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
