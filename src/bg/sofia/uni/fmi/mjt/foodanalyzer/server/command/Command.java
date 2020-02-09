package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelWriter;

public abstract class Command {
    private static final String CHANNEL_WRITE_ERROR = "Unsuccessful attempt to write to channel!";
    protected SocketChannel socketChannel;

    public Command(SocketChannel socketChannel) {
        if (socketChannel == null) {
            throw new IllegalArgumentException("Channel was null");
        }
        this.socketChannel = socketChannel;
    }

    public abstract void execute(Database database, ByteBuffer buffer);

    void writeToChannel(String input, SocketChannel socketChannel, ByteBuffer buffer) {
        ChannelWriter writer = new ChannelWriter(socketChannel, buffer);
        try {
            writer.write(input.toString());
        } catch (IOException e) {
            System.err.println(CHANNEL_WRITE_ERROR);
            e.printStackTrace();
        }
    }
}
