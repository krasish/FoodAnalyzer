package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;

public class GetFoodByBarcodeCommand extends Command {
    private String barcode;

    public GetFoodByBarcodeCommand(SocketChannel socketChannel, String barcode) {
        super(socketChannel);
        if (barcode == null) {
            throw new IllegalArgumentException("Barcode was null");
        }
        this.barcode = barcode;
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {

    }

}
