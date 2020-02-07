package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelWriter;

public class InvalidCommand extends Command {
    private static final String INVALID_COMMAND_ERROR_MESSAGE = "Invalid command!";

    public InvalidCommand(SocketChannel socketChannel) {
        super(socketChannel);
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {
        ChannelWriter writer = new ChannelWriter(socketChannel, buffer);
        try {
            writer.write(INVALID_COMMAND_ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println("Writing to channel was unsuccesful.");
            e.printStackTrace();
        }
    }

}
