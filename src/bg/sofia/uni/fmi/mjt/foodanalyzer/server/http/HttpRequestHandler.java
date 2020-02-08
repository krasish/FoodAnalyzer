package bg.sofia.uni.fmi.mjt.foodanalyzer.server.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HttpRequestHandler {
    private static final String API_KEY = "2S2HwYXxjgEKBmNqvc8mkaHrzckZZXzSDcG3wJFn";
    private static final String BARCODE_URI_TEMPLATE = "https://api.nal.usda.gov/fdc/v1"
            + "/search?generalSearchInput=%s&requireAllWords=true" + "&api_key=%s";
    private static final String FDC_ID_TEMPLATE = "https://api.nal.usda.gov/fdc/v1/%d?api_key=%s";
    private static HttpClient client = HttpClient.newHttpClient();
    private static final int TIMEOUT_SECONDS = 45;

    public static CompletableFuture<String> handleBarcodeRequest(String barcode) {
        String barcodeURIAsString = String.format(BARCODE_URI_TEMPLATE, barcode, API_KEY);
        URI barcodeURI = URI.create(barcodeURIAsString);

        HttpRequest barcodeRequest = HttpRequest.newBuilder(barcodeURI).timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .build();

        return client.sendAsync(barcodeRequest, BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    public static void handleDescriptionRequest(String description) {

    }

    public static void handlefdcIdRequest(int fdcId) {

    }

}
