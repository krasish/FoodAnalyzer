package bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities.Nutrient;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities.NutrientContainer;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities.NutritionData;

public class JSONParserTest {
    private static final String MESSAGE_SEARCH_TEST = "Parsing foods is correct";
    private static final String MESSAGE_DETAILS_TEST = "Parising nutrition data is correct";

    private static final String BARCODE = "08003434343";
    private static final int FDC_ID = 12345;
    private static final String DESCRIPTION = "vafla";
    private static final String INGREDIENTS = "gadosti";
    private static final double NUTRIENT_VALUE = 5.0;

    private static final Nutrient FAT = new Nutrient(NUTRIENT_VALUE);
    private static final Nutrient CARBOHYDRATES = new Nutrient(NUTRIENT_VALUE);
    private static final Nutrient FIBER = new Nutrient(NUTRIENT_VALUE);
    private static final Nutrient PROTEIN = new Nutrient(NUTRIENT_VALUE);
    private static final Nutrient CALORIES = new Nutrient(NUTRIENT_VALUE);
    private static final NutrientContainer LABEL_NUTRIENTS = new NutrientContainer(FAT, CARBOHYDRATES, FIBER, PROTEIN,
            CALORIES);
    private static final String TEST_FOOD_JSON = "{\"foods\": [{\"fdcId\":12345," + "\"description\":\"vafla\","
            + "\"gtinUpc\":\"08003434343\"}]}";
    private static final String TEST_NUTRITION_DATA_JSON = "{\"description\":\"vafla\","
            + "\"ingredients\":\"gadosti\"," + "\"fdcId\":12345,\"labelNutrients\":{\"fat\":{\"value\":5.0},"
            + "\"carbohydrates\":{\"value\":5.0},\"fiber\":{\"value\":5.0},"
            + "\"protein\":{\"value\":5.0},\"calories\":{\"value\":5.0}}}";
    protected static final Food TEST_FOOD = new Food(FDC_ID, DESCRIPTION, BARCODE, INGREDIENTS);
    protected static final NutritionData TEST_NUTRITION_DATA = new NutritionData(DESCRIPTION, INGREDIENTS, FDC_ID,
            LABEL_NUTRIENTS);

    @Test
    public void testParseFromFoodSearchEndpointParsesCorrectly() {
        assertEquals(MESSAGE_SEARCH_TEST, TEST_FOOD,
                JSONParser.parseFromFoodSearchEndpoint(TEST_FOOD_JSON).iterator().next());
    }

    @Test
    public void testParseFromFoodDetailsEndpointParsesCorrectly() {
        assertEquals(MESSAGE_DETAILS_TEST, TEST_NUTRITION_DATA,
                JSONParser.parseFromFoodDetailsEndpoint(TEST_NUTRITION_DATA_JSON));
    }

}
