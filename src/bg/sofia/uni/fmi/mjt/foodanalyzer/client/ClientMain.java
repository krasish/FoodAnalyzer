package bg.sofia.uni.fmi.mjt.foodanalyzer.client;

public class ClientMain {
    private static final int PORT = 8080;
    private static final String LOCAL_HOST = "localhost";

    public static void main(String... args) {
        FoodAnalyzerClient client = new FoodAnalyzerClient(PORT, LOCAL_HOST);
        client.start();

    }
}
