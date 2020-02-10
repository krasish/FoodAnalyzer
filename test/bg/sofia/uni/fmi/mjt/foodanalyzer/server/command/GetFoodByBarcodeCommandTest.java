package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.StreamWritingSocketChannelStub;

public class GetFoodByBarcodeCommandTest extends CommandTestBase {

    @Test
    public void testExecuteWritesCorrectBarcodeToChannelWhenBarcodeIsPresentInDatabase() {
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub channelStub = new StreamWritingSocketChannelStub(outputStream);

        GetFoodByBarcodeCommand barcodeCommand = new GetFoodByBarcodeCommand(channelStub, BARCODE_1);
        barcodeCommand.execute(database, BUFFER);

        assertEquals(TEST_1_MESSAGE, testFood1.toString(), outputStream.toString());

    }

    @Test
    public void testHandleHttpRequestSendsCorrectHttpRequest() throws IOException {
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
