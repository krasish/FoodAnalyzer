package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.http.HttpRequestHandler;

public class GetFoodByBarcodeCommandTest extends CommandTestBase {

    @Test
    public void testExecuteWritesCorrectBarcodeToChannelWhenBarcodeIsPresentInDatabase() {

        GetFoodByBarcodeCommand barcodeCommand = new GetFoodByBarcodeCommand(channelStub, BARCODE_1);
        barcodeCommand.execute(database, buffer);

        assertEquals(TEST_1_MESSAGE, testFood1.toString(), outputStream.toString());

    }

    @Test
    public void testHandleHttpRequestSendsCorrectHttpRequestForBarcode() throws IOException {
        HttpRequestHandler handlerMock = mock(HttpRequestHandler.class);
        when(handlerMock.handleBarcodeRequest(INEXISTING_BARCODE)).thenReturn(new CompletableFuture<String>());

        GetFoodByBarcodeCommand barcodeCommand = new GetFoodByBarcodeCommand(channelStub, INEXISTING_BARCODE);

        Supplier<CompletableFuture<String>> supplier = () -> handlerMock.handleBarcodeRequest(INEXISTING_BARCODE);

        barcodeCommand.handleHttpRquest(handlerMock, database, buffer, supplier);

        verify(handlerMock).handleBarcodeRequest(INEXISTING_BARCODE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleHttpRequestThrowsExceptionWhenPassedNull() {
        GetFoodByBarcodeCommand barcodeCommand = new GetFoodByBarcodeCommand(channelStub, INEXISTING_BARCODE);
        barcodeCommand.handleHttpRquest(null, database, buffer, () -> new CompletableFuture<String>());
    }
}
