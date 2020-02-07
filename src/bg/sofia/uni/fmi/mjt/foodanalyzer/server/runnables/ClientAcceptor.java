package bg.sofia.uni.fmi.mjt.foodanalyzer.server.runnables;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ClientAcceptor implements Runnable {
    private Selector selector;
    private SelectionKey key;

    public ClientAcceptor(Selector selector, SelectionKey key) {
        this.selector = selector;
        this.key = key;
    }

    @Override
    public void run() {
        ServerSocketChannel sockChannel = (ServerSocketChannel) key.channel();
        SocketChannel accept;
        try {
            accept = sockChannel.accept();
            accept.configureBlocking(false);
            accept.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } catch (IOException e) {
            System.out.println("Could not accept socketChannel.");
            e.printStackTrace();
        }
    }
}
