package bg.sofia.uni.fmi.mjt.foodanalyzer.server.runnables;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelReader;

public class ClientHandler implements Runnable {
    private static final short BUFFER_SIZE = 2048;

    private SelectionKey selectionKey;
    private SocketChannel socketChannel;
    private ChannelReader channelReader;
    private Database database;
    private ByteBuffer buffer;

    public ClientHandler(SelectionKey selectionKey, Database database) {
        this.selectionKey = selectionKey;
        this.socketChannel = (SocketChannel) selectionKey.channel();
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
        channelReader = new ChannelReader(socketChannel, buffer);
        this.database = database;
    }

    @Override
    public void run() {

    }
}
