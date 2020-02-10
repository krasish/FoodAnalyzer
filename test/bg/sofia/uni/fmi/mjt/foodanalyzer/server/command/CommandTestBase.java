package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Nutrient;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.NutrientContainer;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.NutritionData;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.StreamWritingSocketChannelStub;

public abstract class CommandTestBase {
    protected static final String TEST_1_MESSAGE = "Command writes to channel info for food present in database";

    protected static final int BUFFER_SIZE = 256;
    protected static Database database;
    protected static final String BARCODE_1 = "08003434343";
    protected static final String INEXISTING_BARCODE = "080037624233";

    protected static final int FDC_ID_1 = 12345;
    protected static final int FDC_ID_2 = 22233;

    protected static final String DESCRIPTION_1 = "vafla";
    private static final String DESCRIPTION_2 = "Partial content...";
    protected static final String INEXISTING_DESCRIPTION = "fafla";

    protected static final String INGREDIENTS_1 = "gadosti";

    private static final double NUTRIENT_VALUE = 5.0;

    private static final Nutrient FAT = new Nutrient(NUTRIENT_VALUE);
    private static final Nutrient CARBOHYDRATES = new Nutrient(NUTRIENT_VALUE);
    private static final Nutrient FIBER = new Nutrient(NUTRIENT_VALUE);
    private static final Nutrient PROTEIN = new Nutrient(NUTRIENT_VALUE);
    private static final Nutrient CALORIES = new Nutrient(NUTRIENT_VALUE);
    private static final NutrientContainer LABEL_NUTRIENTS = new NutrientContainer(FAT, CARBOHYDRATES, FIBER, PROTEIN,
            CALORIES);

    protected static Food testFood1 = new Food(FDC_ID_1, DESCRIPTION_1, BARCODE_1, INGREDIENTS_1);
    protected static NutritionData testNutritionData1 = new NutritionData(DESCRIPTION_1, INGREDIENTS_1, FDC_ID_1,
            LABEL_NUTRIENTS);
    protected static NutritionData testNutritionData2 = new NutritionData(DESCRIPTION_2, null, FDC_ID_2, null);

    protected OutputStream outputStream;
    protected StreamWritingSocketChannelStub channelStub;
    protected ByteBuffer buffer;

    @BeforeClass
    public static void initializeDatabase() {

        List<Food> foods = Arrays.asList(testFood1);

        database = new Database();
        database.addFood(foods);
        database.addNutritionData(testNutritionData1);
        database.addNutritionData(testNutritionData2);
    }

    @Before
    public void initializeResources() {
        outputStream = new ByteArrayOutputStream();
        channelStub = new StreamWritingSocketChannelStub(outputStream);
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

}
