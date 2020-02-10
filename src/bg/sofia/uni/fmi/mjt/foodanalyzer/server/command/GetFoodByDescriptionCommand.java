package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.function.Function;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json.JSONParser;

public class GetFoodByDescriptionCommand extends Command {
    private static final String NULL_DESCRIPTION = "Description was null";
    private static final String EMPTY_LIST_ERROR = "Information about that food was not found";

    private String description;
    private static Function<? super String, ? extends List<Food>> parseJsonFunction = json -> JSONParser
            .parseFromFoodSearchEndpoint(json);

    public GetFoodByDescriptionCommand(SocketChannel socketChannel, String description) {
        super(socketChannel);
        if (description == null) {
            throw new IllegalArgumentException(NULL_DESCRIPTION);
        }
        this.description = description;
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {
        List<Food> result = database.getByDescription(description);
        if (result != null) {
            result.forEach(food -> writeToChannel(food.toString(), socketChannel, buffer));
            return;
        }
        HttpRequestHandler httpHandler = new HttpRequestHandler();

        try {
            handleHttpRquest(httpHandler, database, buffer);
        } catch (IllegalArgumentException iae) {
            handleError(buffer);
        }
    }

    public void handleHttpRquest(HttpRequestHandler httpHandler, Database database, ByteBuffer buffer) {
        if (httpHandler == null || database == null || buffer == null) {
            throw new IllegalArgumentException("HandleHttpRequest recieved null argument");
        }
        httpHandler.handleDescriptionRequest(description).thenApply(parseJsonFunction).thenAccept(list -> {
            if (list.isEmpty()) {
                writeToChannel(EMPTY_LIST_ERROR, socketChannel, buffer);
            } else {
                database.addFood(list);
                list.forEach(food -> writeToChannel(food.toString(), socketChannel, buffer));
            }
        });
    }

}
