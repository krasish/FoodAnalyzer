package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.function.Function;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json.JSONParser;

public class GetFoodByBarcodeCommand extends Command {
    private static final String NULL_BARCODE = "Barcode was null";
    private static final String EMPTY_LIST_ERROR = "Information about that food was not found";

    private String barcode;
    private static Function<? super String, ? extends List<Food>> parseJsonFunction = json -> JSONParser
            .parseFromFoodSearchEndpoint(json);

    public GetFoodByBarcodeCommand(SocketChannel socketChannel, String barcode) {
        super(socketChannel);
        if (barcode == null) {
            throw new IllegalArgumentException(NULL_BARCODE);
        }
        this.barcode = barcode;
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {

        List<Food> result = database.getByBarcode(barcode);
        if (result != null) {
            result.forEach(food -> writeToChannel(food.toString(), socketChannel, buffer));
            return;
        }
        HttpRequestHandler httpHandler = new HttpRequestHandler();

        handleHttpRquest(httpHandler, database, buffer);
    }

    public void handleHttpRquest(HttpRequestHandler httpHandler, Database database, ByteBuffer buffer) {
        httpHandler.handleBarcodeRequest(barcode).thenApply(parseJsonFunction).thenAccept(list -> {
            if (list.isEmpty()) {
                writeToChannel(EMPTY_LIST_ERROR, socketChannel, buffer);
            } else {
                database.addFood(list);
                list.forEach(food -> writeToChannel(food.toString(), socketChannel, buffer));
            }
        });
    }
}
