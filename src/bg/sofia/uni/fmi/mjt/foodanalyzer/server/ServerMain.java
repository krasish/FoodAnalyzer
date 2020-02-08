package bg.sofia.uni.fmi.mjt.foodanalyzer.server;

import java.io.IOException;

public class ServerMain {
    private static final String SERVER_ERROR = "Could not start server. :(";
    private static final int PORT = 8080;
    private static final String SERVER_HOST = "localhost";

    public static void main(String... args) {
        try (FoodAnalyzerServer server = new FoodAnalyzerServer(PORT, SERVER_HOST)) {
            server.start();
        } catch (IOException e) {
            System.err.println(SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
