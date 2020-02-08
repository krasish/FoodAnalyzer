package bg.sofia.uni.fmi.mjt.foodanalyzer.server.runnables;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class ClientAcceptor implements Runnable {
    private Selector selector;
    private SelectionKey key;
    public static int count;

    public ClientAcceptor(Selector selector, SelectionKey key) {

        this.selector = selector;
        this.key = key;
    }

    @Override
    public void run() {

    }
}
