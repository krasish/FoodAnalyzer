package bg.sofia.uni.fmi.mjt.foodanalyzer.server.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;

public class ChannelReader {

    private ByteBuffer buffer;
    SocketChannel socketChannel;

    public ChannelReader(SocketChannel socketChannel, ByteBuffer buffer) {
        this.socketChannel = socketChannel;
        this.buffer = buffer;
    }

    public String read() throws IOException {
        int read = socketChannel.read(buffer);
        if (read <= 0) {
            socketChannel.close();
            throw new ClosedChannelException();
        }
        buffer.flip();

        String result = new String(buffer.array(), 0, buffer.limit());
        buffer.clear();
        return result.intern();
    }
}
