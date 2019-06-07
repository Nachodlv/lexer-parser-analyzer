package main;

import interpreter.ContextListener;

class Printer implements ContextListener {

    @Override
    public void receiveNewOutput(String output) {
        System.out.println(output);
    }
}
