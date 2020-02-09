package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

public class Food {
    private int fdcId;
    private String description;
    private String gtinUpc;

    public Food(int fdcId, String description, String gtinUpc, String ingredients) {
        this.fdcId = fdcId;
        this.description = description;
        this.gtinUpc = gtinUpc;
    }

    public int getFdcId() {
        return fdcId;
    }

    public String getDescription() {
        return description;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Description: %s%n", description));

        result.append(String.format("fdcId: %d%n", fdcId));

        if (gtinUpc != null) {
            result.append(String.format("GTIN/UPC: %s%n", gtinUpc));
        }

        return result.toString();
    }

}
