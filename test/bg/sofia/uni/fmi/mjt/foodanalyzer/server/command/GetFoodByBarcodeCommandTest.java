package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.BeforeClass;
import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Food;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.StreamWritingSocketChannelStub;

public class GetFoodByBarcodeCommandTest {
    private static final String BARCODE_TEST_1_MESSAGE = "Command writes to channel info for food present in database";

    private static final int BUFFER_SIZE = 128;
    private static Database database;
    private static final String BARCODE_1 = "08003434343";
    private static final String INEXISTING_BARCODE = "080037624233";
    private static final ByteBuffer BUFFER = ByteBuffer.allocate(BUFFER_SIZE);

    private static final int FDC_ID_1 = 12345;
    private static final String DESCRIPTION_1 = "vafla";
    private static final String INGREDIENTS_1 = "gadosti";

    private static Food testFood1 = new Food(FDC_ID_1, DESCRIPTION_1, BARCODE_1, INGREDIENTS_1);

    @BeforeClass
    public static void initializeDatabase() {

        List<Food> foods = Arrays.asList(testFood1);

        database = new Database();
        database.addFood(foods);

    }

    @Test
    public void testExecuteWritesCorrectBarcodeToChannelWhenBarcodeIsPresentInDatabase() {
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub channelStub = new StreamWritingSocketChannelStub(outputStream);

        GetFoodByBarcodeCommand barcodeCommand = new GetFoodByBarcodeCommand(channelStub, BARCODE_1);
        barcodeCommand.execute(database, BUFFER);

        assertEquals(BARCODE_TEST_1_MESSAGE, testFood1.toString(), outputStream.toString());

    }

    @Test
    public void testHandleHttpRequestSendsHttpRequest() throws IOException {
        HttpRequestHandler handlerMock = mock(HttpRequestHandler.class);
        when(handlerMock.handleBarcodeRequest(INEXISTING_BARCODE)).thenReturn(new CompletableFuture<String>());

        OutputStream outputStreamDummy = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub channelStub = new StreamWritingSocketChannelStub(outputStreamDummy);

        GetFoodByBarcodeCommand barcodeCommand = new GetFoodByBarcodeCommand(channelStub, INEXISTING_BARCODE);

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        barcodeCommand.handleHttpRquest(handlerMock, database, buffer);

        verify(handlerMock).handleBarcodeRequest(INEXISTING_BARCODE);
    }
}
