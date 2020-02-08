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
    private static final String FOOD_SEARCH_ENDPOINT_URI_TEMPLATE = "https://api.nal.usda.gov/fdc/v1"
            + "/search?generalSearchInput=%s&requireAllWords=true" + "&api_key=%s";
    private static final String FOOD_DETAILS_ENDPOINT_TEMPLATE = "https://api.nal.usda.gov/fdc/v1/%d?api_key=%s";

    private HttpClient client;

    public HttpRequestHandler() {
        client = HttpClient.newHttpClient();
    }

    private static final int TIMEOUT_SECONDS = 45;

    public CompletableFuture<String> handleBarcodeRequest(String barcode) {
        String barcodeURIAsString = String.format(FOOD_SEARCH_ENDPOINT_URI_TEMPLATE, barcode, API_KEY);
        URI barcodeURI = URI.create(barcodeURIAsString);

        HttpRequest barcodeRequest = HttpRequest.newBuilder(barcodeURI).timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .build();

        return client.sendAsync(barcodeRequest, BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> handleDescriptionRequest(String description) {
        String descriptionURIAsString = String.format(FOOD_SEARCH_ENDPOINT_URI_TEMPLATE, description, API_KEY);
        URI descriptionURI = URI.create(descriptionURIAsString);

        HttpRequest descriptionRequest = HttpRequest.newBuilder(descriptionURI)
                .timeout(Duration.ofSeconds(TIMEOUT_SECONDS)).build();

        return client.sendAsync(descriptionRequest, BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> handlefdcIdRequest(int fdcId) {
        String fdcIdURIAsString = String.format(FOOD_DETAILS_ENDPOINT_TEMPLATE, fdcId, API_KEY);
        URI fdcIdURI = URI.create(fdcIdURIAsString);

        HttpRequest fdcIdRequest = HttpRequest.newBuilder(fdcIdURI).timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .build();

        return client.sendAsync(fdcIdRequest, BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

}
