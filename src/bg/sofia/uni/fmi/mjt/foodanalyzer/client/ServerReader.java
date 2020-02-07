package bg.sofia.uni.fmi.mjt.foodanalyzer.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerReader implements Runnable {

    private static final int BUFFER_SIZE = 2048;

    private static final String DISCONNECTED_MESSAGE = "You were disconnected!";
    private ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
    SocketChannel socketChannel;

    public ServerReader(SocketChannel channel) {
        this.socketChannel = channel;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            try {
                readFromChannel(socketChannel);
            } catch (IOException e) {
                System.out.println(DISCONNECTED_MESSAGE);
                break;
            }
        }

    }

    private void readFromChannel(SocketChannel socketChannel) throws IOException {
        buffer.clear();
        socketChannel.read(buffer);
        buffer.flip();
        String reply = new String(buffer.array(), 0, buffer.limit()); // buffer drain
        System.out.println(reply);
    }
}