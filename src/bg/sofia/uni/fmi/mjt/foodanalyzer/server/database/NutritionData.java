package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

public class NutritionData {
    private static final String NUTRITION_WITH_INGREDIENTS_DATA_TEMPLATE = "description: %s%n"
            + "ingredients: %s%nfdcId: %s%n";
    private static final String NUTRITION_NO_INGREDIENTS_DATA_TEMPLATE = "description: %s%nfdcId: %s%n";

    private static final Object NO_NUTRITION_DATA_ERROR = "Nutrition data for this food was not found :(";

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
        StringBuilder builder;
        if (ingredients == null) {
            builder = new StringBuilder(String.format(NUTRITION_NO_INGREDIENTS_DATA_TEMPLATE, description, fdcId));
        } else {
            builder = new StringBuilder(
                    String.format(NUTRITION_WITH_INGREDIENTS_DATA_TEMPLATE, description, ingredients, fdcId));
        }

        if (labelNutrients == null) {
            builder.append(NO_NUTRITION_DATA_ERROR);
        } else {
            builder.append(labelNutrients);
        }
        return builder.toString();

    }

}
