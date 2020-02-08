package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

public class NutrientContainer {
    public static final String NUTRIENT_CONTAINER_TEMPLATE = "fat: %s%ncarbohydrates: %s%n"
            + "fiber: %s%nprotein: %s%ncalories: %s%n";;

    private Nutrient fat;
    private Nutrient carbohydrates;
    private Nutrient fiber;
    private Nutrient protein;
    private Nutrient calories;

    public NutrientContainer(Nutrient fat, Nutrient carbohydrates, Nutrient fiber, Nutrient protein,
            Nutrient calories) {
        super();
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
        this.protein = protein;
        this.calories = calories;
    }

    public Nutrient getFat() {
        return fat;
    }

    public Nutrient getCarbohydrates() {
        return carbohydrates;
    }

    public Nutrient getFiber() {
        return fiber;
    }

    public Nutrient getProtein() {
        return protein;
    }

    public Nutrient getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return String.format(NUTRIENT_CONTAINER_TEMPLATE, fat, carbohydrates, fiber, protein, calories);
    }
}
