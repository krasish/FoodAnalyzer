package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;

public class InvalidCommand extends Command {
    private static final String INVALID_COMMAND_ERROR_MESSAGE = "Invalid command!";

    public InvalidCommand(SocketChannel socketChannel) {
        super(socketChannel);
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {
        writeToChannel(INVALID_COMMAND_ERROR_MESSAGE, socketChannel, buffer);
    }

}
