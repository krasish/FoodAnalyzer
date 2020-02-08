package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.function.Function;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json.JSONParser;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelWriter;

public class GetFoodByBarcodeCommand extends Command {
    private String barcode;
    private static final String CHANNEL_WRITE_ERROR = "Unsuccessful attempt to write to channel!";
    private static Function<? super String, ? extends List<Food>> parseJsonFunction = json -> JSONParser
            .parseFromFirstEntrypoint(json);

    public GetFoodByBarcodeCommand(SocketChannel socketChannel, String barcode) {
        super(socketChannel);
        if (barcode == null) {
            throw new IllegalArgumentException("Barcode was null");
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

        HttpRequestHandler.handleBarcodeRequest("009800146130").thenApply(parseJsonFunction).thenAccept(list -> {
            list.forEach(food -> writeToChannel(food.toString(), socketChannel, buffer));
        });
    }

    private void writeToChannel(String input, SocketChannel socketChannel, ByteBuffer buffer) {
        ChannelWriter writer = new ChannelWriter(socketChannel, buffer);
        try {
            writer.write(input.toString());
        } catch (IOException e) {
            System.err.println(CHANNEL_WRITE_ERROR);
            e.printStackTrace();
        }
    }
}
