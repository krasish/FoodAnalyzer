package bg.sofia.uni.fmi.mjt.foodanalyzer.client.io;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerReader implements Runnable {

    private static final int BUFFER_SIZE = 4096;

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
                readFromChannel(socketChannel, System.out);
            } catch (IOException e) {
                System.out.println(DISCONNECTED_MESSAGE);
                break;
            }
        }

    }

    public void readFromChannel(SocketChannel socketChannel, PrintStream stream) throws IOException {
        buffer.clear();
        socketChannel.read(buffer);
        buffer.flip();
        String reply = new String(buffer.array(), 0, buffer.limit()); // buffer drain
        stream.println(reply);
    }
}
