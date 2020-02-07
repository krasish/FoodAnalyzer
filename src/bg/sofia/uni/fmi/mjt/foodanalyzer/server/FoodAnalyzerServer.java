package bg.sofia.uni.fmi.mjt.foodanalyzer.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class FoodAnalyzerServer implements Closeable {
    private static final String SERVER_HOST = "localhost";
    private int serverPort;

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public FoodAnalyzerServer(int port) {
        serverPort = port;
    }

    public void start() {
        try {
            openServerSocketChannel();
            openSelector();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("Server was unable to start.");
            e.printStackTrace();
        }
        try (ClientProcessor processor = new ClientProcessor(selector)) {
            processor.process();
        } catch (IOException e) {
            System.out.println("Closing processor was not successful");
            e.printStackTrace();
        }

    }

    void openServerSocketChannel() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(SERVER_HOST, serverPort));
        serverSocketChannel.configureBlocking(false);
    }

    void openSelector() throws IOException {
        selector = Selector.open();
    }

    @Override
    public void close() throws IOException {
        serverSocketChannel.close();
        selector.close();
    }
}
