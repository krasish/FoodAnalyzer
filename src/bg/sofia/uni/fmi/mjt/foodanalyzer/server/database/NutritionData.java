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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + fdcId;
        result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
        result = prime * result + ((labelNutrients == null) ? 0 : labelNutrients.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NutritionData other = (NutritionData) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (fdcId != other.fdcId) {
            return false;
        }
        if (ingredients == null) {
            if (other.ingredients != null) {
                return false;
            }
        } else if (!ingredients.equals(other.ingredients)) {
            return false;
        }
        if (labelNutrients == null) {
            if (other.labelNutrients != null) {
                return false;
            }
        } else if (!labelNutrients.equals(other.labelNutrients)) {
            return false;
        }
        return true;
    }

}
