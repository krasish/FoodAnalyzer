package bg.sofia.uni.fmi.mjt.foodanalyzer.server.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChannelWriter {

    private ByteBuffer buffer;
    SocketChannel socketChannel;

    public ChannelWriter(SocketChannel socketChannel, ByteBuffer buffer) {
        this.socketChannel = socketChannel;
        this.buffer = buffer;
    }

    public void write(String text) throws IOException {
        buffer.put(text.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
    }

}
