package bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.entities.Food;

public class FoodTest {
    private static final int FDC_ID = 00000;
    private static final String DESCRIPTION = "FMI_RULEZ";
    private static final String BARCODE = "235788";
    private static final String INGREDIENTS = "JAILHOUSE ROCK";
    private static final String MESSAGE = "ToString method of Food return expected String";

    // I truly wish I wasn't using Windows at the moment so I can use Java 13 text
    // blocks here :'(
    private static final String EXPECTED_TO_STRING = "Description: FMI_RULEZ" + System.lineSeparator() + "fdcId: 0"
            + System.lineSeparator() + "GTIN/UPC: 235788" + System.lineSeparator();

    @Test
    public void testToStringReturnsExpectedString() {
        Food testFood = new Food(FDC_ID, DESCRIPTION, BARCODE, INGREDIENTS);

        assertEquals(MESSAGE, EXPECTED_TO_STRING, testFood.toString());
    }

}
