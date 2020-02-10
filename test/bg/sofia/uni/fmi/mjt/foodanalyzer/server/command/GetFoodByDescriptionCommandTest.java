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

public class GetFoodByDescriptionCommandTest extends CommandTestBase {

    @Test
    public void testExecuteWritesCorrectDescriptionToChannelWhenBarcodeIsPresentInDatabase() {
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub channelStub = new StreamWritingSocketChannelStub(outputStream);

        GetFoodByDescriptionCommand barcodeCommand = new GetFoodByDescriptionCommand(channelStub, DESCRIPTION_1);
        barcodeCommand.execute(database, BUFFER);

        assertEquals(TEST_1_MESSAGE, testFood1.toString(), outputStream.toString());

    }

    @Test
    public void testHandleHttpRequestSendsCorrectHttpRequest() throws IOException {
        HttpRequestHandler handlerMock = mock(HttpRequestHandler.class);
        when(handlerMock.handleDescriptionRequest(INEXISTING_DESCRIPTION)).thenReturn(new CompletableFuture<String>());

        OutputStream outputStreamDummy = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub channelStub = new StreamWritingSocketChannelStub(outputStreamDummy);

        GetFoodByDescriptionCommand barcodeCommand = new GetFoodByDescriptionCommand(channelStub,
                INEXISTING_DESCRIPTION);

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        barcodeCommand.handleHttpRquest(handlerMock, database, buffer);

        verify(handlerMock).handleDescriptionRequest(INEXISTING_DESCRIPTION);
    }

}
