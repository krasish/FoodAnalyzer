package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

public class Food {
    private int fdcId;
    private String description;
    private String gtinUpc;
    private String ingredients;

    Food(int fdcId, String description, String gtinUpc, String ingredients) {
        this.fdcId = fdcId;
        this.description = description;
        this.gtinUpc = gtinUpc;
        this.ingredients = ingredients;
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

    public String getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Description: %s%n", description));

        result.append(String.format("fdcId: %d%n", fdcId));

        if (gtinUpc != null) {
            result.append(String.format("GTIN/UPC: %s%n", gtinUpc));
        }
        if (ingredients != null) {
            result.append(String.format("Ingredients: %s%n", ingredients));
        }
        return result.toString();
    }

}
