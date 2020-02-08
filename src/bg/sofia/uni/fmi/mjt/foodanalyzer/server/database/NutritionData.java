package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

public class NutritionData {
    private static final String NUTRITION_DATA_TEMPLATE = "description: %s%ningredients: %s%nfdcId: %s%n";

    private String description;
    private String ingredients;
    private int fdcId;
    private NutrientContainer labelNutrients;

    public NutritionData(String description, String ingredients, int fdcId, NutrientContainer labelNutrients) {
        super();
        this.description = description;
        this.ingredients = ingredients;
        this.fdcId = fdcId;
        this.labelNutrients = labelNutrients;
    }

    public int getFdcId() {
        return fdcId;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public NutrientContainer getLabelNutrients() {
        return labelNutrients;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
                String.format(NUTRITION_DATA_TEMPLATE, description, ingredients, fdcId));
        builder.append(labelNutrients);
        return builder.toString();

    }

}
