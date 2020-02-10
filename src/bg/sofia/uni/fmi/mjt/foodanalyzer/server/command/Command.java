package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json.JSONParser;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.io.ChannelWriter;

public abstract class Command {
    private static final String MESSAGE_INTERNAL_ERROR = "An internal error occured."
            + " Your request could not be processed";
    private static final String CHANNEL_WRITE_ERROR = "Unsuccessful attempt to write to channel!";
    private static final String EMPTY_LIST_ERROR = "Information about that food was not found";

    protected SocketChannel socketChannel;

    private static Function<? super String, ? extends List<Food>> parseJsonFunction = json -> JSONParser
            .parseFromFoodSearchEndpoint(json);

    public Command(SocketChannel socketChannel) {
        if (socketChannel == null) {
            throw new IllegalArgumentException("Channel was null");
        }
        this.socketChannel = socketChannel;
    }

    public abstract void execute(Database database, ByteBuffer buffer);

    void writeToChannel(String input, SocketChannel socketChannel, ByteBuffer buffer) {
        ChannelWriter writer = new ChannelWriter(socketChannel, buffer);
        try {
            writer.write(input.toString());
        } catch (IOException e) {
            System.err.println(CHANNEL_WRITE_ERROR);
            e.printStackTrace();
        }
    }

    void handleError(ByteBuffer buffer) {
        writeToChannel(MESSAGE_INTERNAL_ERROR, socketChannel, buffer);
    }

    public void handleHttpRquest(HttpRequestHandler httpHandler, Database database, ByteBuffer buffer,
            Supplier<CompletableFuture<String>> func) {
        if (httpHandler == null || database == null || buffer == null) {
            throw new IllegalArgumentException("HandleHttpRequest recieved null argument");
        }
        func.get().thenApply(parseJsonFunction).thenAccept(list -> {
            if (list.isEmpty()) {
                writeToChannel(EMPTY_LIST_ERROR, socketChannel, buffer);
            } else {
                database.addFood(list);
                list.forEach(food -> writeToChannel(food.toString(), socketChannel, buffer));
            }
        });
    }
}
