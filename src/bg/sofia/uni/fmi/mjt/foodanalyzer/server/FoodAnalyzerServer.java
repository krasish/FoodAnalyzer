package bg.sofia.uni.fmi.mjt.foodanalyzer.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class FoodAnalyzerServer implements Closeable {
    private static final int MAX_PORT = 65_535;
    private int serverPort;
    private String host;

    private static final String WRONG_PORT_ERROR = "Port not in supported range";
    private static final String SERVER_ERROR = "Server was unable to start.";
    private static final String NULL_HOST = "Host must not be null";
    private static final String CLOSING_PROCESSOR_ERROR = "Closing processor was not successful";

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public FoodAnalyzerServer(int port, String host) {
        if (port < 0 || port > MAX_PORT) {
            throw new IllegalArgumentException(WRONG_PORT_ERROR);
        }
        if (host == null) {
            throw new IllegalArgumentException(NULL_HOST);
        }

        serverPort = port;
        this.host = host;
    }

    public void start() {
        try {
            openServerSocketChannel();
            openSelector();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println(SERVER_ERROR);
            e.printStackTrace();
        }
        try (ClientProcessor processor = new ClientProcessor(selector)) {
            System.out.println("Server started!");
            processor.process();
        } catch (IOException e) {
            System.out.println(CLOSING_PROCESSOR_ERROR);
            e.printStackTrace();
        }

    }

    void openServerSocketChannel() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(host, serverPort));
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
