package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;

public abstract class Command {
    protected SocketChannel socketChannel;

    public Command(SocketChannel socketChannel) {
        if (socketChannel == null) {
            throw new IllegalArgumentException("Channel was null");
        }
        this.socketChannel = socketChannel;
    }

    public abstract void execute(Database database, ByteBuffer buffer);
}
