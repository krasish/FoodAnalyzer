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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((calories == null) ? 0 : calories.hashCode());
        result = prime * result + ((carbohydrates == null) ? 0 : carbohydrates.hashCode());
        result = prime * result + ((fat == null) ? 0 : fat.hashCode());
        result = prime * result + ((fiber == null) ? 0 : fiber.hashCode());
        result = prime * result + ((protein == null) ? 0 : protein.hashCode());
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
        NutrientContainer other = (NutrientContainer) obj;
        if (calories == null) {
            if (other.calories != null) {
                return false;
            }
        } else if (!calories.equals(other.calories)) {
            return false;
        }
        if (carbohydrates == null) {
            if (other.carbohydrates != null) {
                return false;
            }
        } else if (!carbohydrates.equals(other.carbohydrates)) {
            return false;
        }
        if (fat == null) {
            if (other.fat != null) {
                return false;
            }
        } else if (!fat.equals(other.fat)) {
            return false;
        }
        if (fiber == null) {
            if (other.fiber != null) {
                return false;
            }
        } else if (!fiber.equals(other.fiber)) {
            return false;
        }
        if (protein == null) {
            if (other.protein != null) {
                return false;
            }
        } else if (!protein.equals(other.protein)) {
            return false;
        }
        return true;
    }

}
