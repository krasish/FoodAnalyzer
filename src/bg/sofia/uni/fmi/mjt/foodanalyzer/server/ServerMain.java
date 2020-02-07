package bg.sofia.uni.fmi.mjt.foodanalyzer.server;

public class ServerMain {
    private static final int PORT = 8080;
    private static final String SERVER_HOST = "localhost";

    public static void main(String... args) {

        FoodAnalyzerServer server = new FoodAnalyzerServer(PORT, SERVER_HOST);
        server.start();
    }
}
