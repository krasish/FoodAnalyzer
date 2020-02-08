package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.function.Function;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.NutritionData;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json.JSONParser;

public class GetFoodReportCommand extends Command {
    private static final String NEGATIVE_FDC_ID = "Food data central ID must be positive";
    private static Function<? super String, ? extends NutritionData> parseJsonFunciton = json -> JSONParser
            .parseFromFoodDetailsEndpoint(json);

    int fdcId;

    public GetFoodReportCommand(SocketChannel socketChannel, int fdcId) {
        super(socketChannel);
        if (fdcId < 0) {
            throw new IllegalArgumentException(NEGATIVE_FDC_ID);
        }
        this.fdcId = fdcId;
    }

    @Override
    public void execute(Database database, ByteBuffer buffer) {
        NutritionData result = database.getByfdcId(fdcId);
        if (result != null) {
            writeToChannel(result.toString(), socketChannel, buffer);
            return;
        }
        HttpRequestHandler handler = new HttpRequestHandler();
        handler.handlefdcIdRequest(fdcId).thenApply(parseJsonFunciton).thenAccept(data -> {
            database.addNutritionData(data);
            writeToChannel(data.toString(), socketChannel, buffer);
        });
    }

}
