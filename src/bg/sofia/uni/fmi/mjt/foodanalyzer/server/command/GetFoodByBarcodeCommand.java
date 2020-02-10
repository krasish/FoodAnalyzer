package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;

public class GetFoodByBarcodeCommand extends Command {
    private static final String NULL_BARCODE = "Barcode was null";

    private String barcode;

    public GetFoodByBarcodeCommand(SocketChannel socketChannel, String barcode) {
        super(socketChannel);
        if (barcode == null) {
            throw new IllegalArgumentException(NULL_BARCODE);
        }
        this.barcode = barcode;
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {

        Food result = database.getByBarcode(barcode);
        if (result != null) {
            writeToChannel(result.toString(), socketChannel, buffer);
            return;
        }
        HttpRequestHandler httpHandler = new HttpRequestHandler();

        try {
            handleHttpRquest(httpHandler, database, buffer, () -> httpHandler.handleBarcodeRequest(barcode));
        } catch (IllegalArgumentException iae) {
            handleError(buffer);
        }
    }

}
