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
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.StreamWritingSocketChannelStub;

public abstract class CommandTestBase {
    protected static final String TEST_1_MESSAGE = "Command writes to channel info for food present in database";

    protected static final int BUFFER_SIZE = 128;
    protected static Database database;
    protected static final String BARCODE_1 = "08003434343";
    protected static final String INEXISTING_BARCODE = "080037624233";

    protected static final int FDC_ID_1 = 12345;

    protected static final String DESCRIPTION_1 = "vafla";
    protected static final String INEXISTING_DESCRIPTION = "fafla";

    protected static final String INGREDIENTS_1 = "gadosti";

    protected static Food testFood1 = new Food(FDC_ID_1, DESCRIPTION_1, BARCODE_1, INGREDIENTS_1);

    protected OutputStream outputStream;
    protected StreamWritingSocketChannelStub channelStub;
    protected ByteBuffer buffer;

    @BeforeClass
    public static void initializeDatabase() {

        List<Food> foods = Arrays.asList(testFood1);

        database = new Database();
        database.addFood(foods);

    }

    @Before
    public void initializeResources() {
        outputStream = new ByteArrayOutputStream();
        channelStub = new StreamWritingSocketChannelStub(outputStream);
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

}
