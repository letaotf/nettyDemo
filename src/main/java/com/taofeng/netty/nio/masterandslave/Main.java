package com.taofeng.netty.nio.masterandslave;
      
    import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        try {
            TCPReactor reactor = new TCPReactor(1333);
            Thread thread = new Thread(reactor);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}