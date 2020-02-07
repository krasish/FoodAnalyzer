package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;

public class GetFoodReportCommand extends Command {
    int fdcId;

    public GetFoodReportCommand(SocketChannel socketChannel, int fdcId) {
        super(socketChannel);
        this.fdcId = fdcId;
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {

    }

}
