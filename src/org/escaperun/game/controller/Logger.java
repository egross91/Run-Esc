package org.escaperun.game.controller;

import java.util.Deque;
import java.util.LinkedList;

public class Logger {

    private static Logger _logger = new Logger();
    private Deque<String> messageQueue = new LinkedList<String>();
    private Deque<String> rightMessageQueue = new LinkedList<String>();


    private Logger() {}

    public static Logger getInstance() {
        return _logger;
    }

    public void pushMessage(String str) {
        messageQueue.addLast(str);
    }

    public void pushRightMessage(String str){
        rightMessageQueue.addLast(str);
    }
    public String pollFront() {
        return messageQueue.pollFirst();
    }

    public String pollFrontRight(){
        return rightMessageQueue.pollFirst();
    }

    public boolean isEmpty() {
        return messageQueue.isEmpty();
    }

    public boolean isRightEmpty(){
        return rightMessageQueue.isEmpty();
    }
}
