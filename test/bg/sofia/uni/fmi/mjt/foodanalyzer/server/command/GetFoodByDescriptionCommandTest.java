package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;

public class GetFoodByDescriptionCommandTest extends CommandTestBase {

    @Test
    public void testExecuteWritesCorrectDescriptionToChannelWhenBarcodeIsPresentInDatabase() {
        GetFoodByDescriptionCommand barcodeCommand = new GetFoodByDescriptionCommand(channelStub, DESCRIPTION_1);
        barcodeCommand.execute(database, buffer);

        assertEquals(TEST_1_MESSAGE, testFood1.toString(), outputStream.toString());

    }

    @Test
    public void testHandleHttpRequestSendsCorrectHttpRequest() throws IOException {
        HttpRequestHandler handlerMock = mock(HttpRequestHandler.class);
        when(handlerMock.handleDescriptionRequest(INEXISTING_DESCRIPTION)).thenReturn(new CompletableFuture<String>());

        GetFoodByDescriptionCommand barcodeCommand = new GetFoodByDescriptionCommand(channelStub,
                INEXISTING_DESCRIPTION);

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        Supplier<CompletableFuture<String>> supplier = () -> handlerMock
                .handleDescriptionRequest(INEXISTING_DESCRIPTION);
        barcodeCommand.handleHttpRquest(handlerMock, database, buffer, supplier);

        verify(handlerMock).handleDescriptionRequest(INEXISTING_DESCRIPTION);
    }

}
