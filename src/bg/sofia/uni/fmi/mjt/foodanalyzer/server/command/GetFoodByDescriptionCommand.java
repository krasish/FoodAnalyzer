package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;

public class GetFoodByDescriptionCommand extends Command {
    private String description;

    public GetFoodByDescriptionCommand(SocketChannel socketChannel, String description) {
        super(socketChannel);
        if (description == null) {
            throw new IllegalArgumentException("Descrition was null");
        }
        this.description = description;
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {

    }

}
