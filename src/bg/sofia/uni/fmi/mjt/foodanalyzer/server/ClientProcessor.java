package bg.sofia.uni.fmi.mjt.foodanalyzer.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.Command;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.command.CommandParser;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelReader;

public class ClientProcessor {
    private static final String SELECTOR_ERROR_MESSAGE = "Selection of keys was unsuccessful";
    private static final String CHANNEL_DISCONNEECT = "A channel was disconected!";
    private static final String CLOSING_CHANNEL_ERROR = "An error occured while closing channel!";
    private static final int SLEEP_MILIS = 256;
    private static final short BUFFER_SIZE = 2048;

    private Selector selector;
    private Database database;

    public ClientProcessor(Selector selector) {
        this.selector = selector;
        this.database = new Database();
    }

    public void process() {

        while (true) {

            if (countReadyKeys() == 0) {
                busyWait();
                continue;
            }
            var selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey currentKey = iterator.next();

                if (currentKey.isAcceptable()) {
                    processAcceptableKey(currentKey);
                } else if (currentKey.isReadable()) {
                    processReadableKey(currentKey);
                }
                iterator.remove();
            }
        }
    }

    void processAcceptableKey(SelectionKey currentKey) {
        ServerSocketChannel sockChannel = (ServerSocketChannel) currentKey.channel();
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

    void processReadableKey(SelectionKey currentKey) {
        SocketChannel socketChannel = (SocketChannel) currentKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        ChannelReader channelReader = new ChannelReader(socketChannel, buffer);
        String input;
        try {
            input = channelReader.read();
        } catch (IOException ioe) {
            System.out.println("Unable to read from channel");
            currentKey.cancel();
            stopChannel(currentKey, socketChannel);
            return;
        }

        Command command = CommandParser.parse(input, socketChannel);
        command.execute(database, buffer);
    }

    void stopChannel(SelectionKey key, SocketChannel sokcetChannel) {
        key.cancel();
        try {
            sokcetChannel.close();
        } catch (IOException e) {
            System.out.println("An error occured while closing channel!");
        }
    }

    int countReadyKeys() {
        try {
            return selector.select();
        } catch (IOException ioe) {
            System.out.println(SELECTOR_ERROR_MESSAGE);
            return 0;
        }
    }

    void busyWait() {
        try {
            Thread.sleep(SLEEP_MILIS);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }

}
