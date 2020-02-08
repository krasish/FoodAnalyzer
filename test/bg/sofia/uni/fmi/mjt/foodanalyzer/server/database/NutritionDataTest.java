package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NutritionDataTest {
    private static final int FAT_VALUE = 5;
    private static final int CARBOHYDRATES_VALUE = 100;
    private static final int FIBER_VALUE = 20;
    private static final double PROTEIN_VALUE = 24;
    private static final double CALORIES_VALUE = 170.536;
    private static final int FDC_ID = 12345;
    private static final String DESCRIPTION = "Vafla 'Boiko Borisov'";
    private static final String INGREDIENTS = "tikva";

    private static final String EXPECTED_STRING = "description: Vafla 'Boiko Borisov'\r\n" + "ingredients: tikva\r\n"
            + "fdcId: 12345\r\n" + "fat: 5.0\r\n" + "carbohydrates: 100.0\r\n" + "fiber: 20.0\r\n" + "protein: 24.0\r\n"
            + "calories: 170.536\r\n";

    @Test
    public void testToStingReturnsExpectedOutput() {
        NutrientContainer labelNutrients = new NutrientContainer(new Nutrient(FAT_VALUE),
                new Nutrient(CARBOHYDRATES_VALUE), new Nutrient(FIBER_VALUE), new Nutrient(PROTEIN_VALUE),
                new Nutrient(CALORIES_VALUE));

        NutritionData data = new NutritionData(DESCRIPTION, INGREDIENTS, FDC_ID, labelNutrients);

        assertEquals(EXPECTED_STRING, data.toString());
    }

}
