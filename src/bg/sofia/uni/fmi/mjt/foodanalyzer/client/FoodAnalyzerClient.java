package bg.sofia.uni.fmi.mjt.foodanalyzer.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.client.io.ServerReader;
import bg.sofia.uni.fmi.mjt.foodanalyzer.client.io.ServerWriter;

public class FoodAnalyzerClient {
    private static final int MAX_PORT = 65_535;

    private static final String WRONG_PORT_ERROR = "Port not in supported range";
    private static final String NULL_HOST = "Host must not be null";
    private static final String CLIENT_START_ERROR = "An error occured while starting client!";
    private static final String WELCOME_MESSAGE = "Welcome to Food Analyzer!";

    private int port;
    private String host;

    public FoodAnalyzerClient(int port, String host) {
        if (port < 0 || port > MAX_PORT) {
            throw new IllegalArgumentException(WRONG_PORT_ERROR);
        }
        if (host == null) {
            throw new IllegalArgumentException(NULL_HOST);
        }
        this.port = port;
        this.host = host;
    }

    public void start() {
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(host, port))) {
            printWelcomeMessage();
            startReadingThread(socketChannel);

            ServerWriter serverWriter = new ServerWriter(socketChannel);
            serverWriter.run();

        } catch (IOException e) {
            System.out.println(CLIENT_START_ERROR);
            e.printStackTrace();
        }
    }

    private void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    private void startReadingThread(SocketChannel socketChannel) {
        Thread readThread = new Thread(new ServerReader(socketChannel));
        readThread.start();
    }

}
