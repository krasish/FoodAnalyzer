package bg.sofia.uni.fmi.mjt.foodanalyzer.server;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.DoNothingSocketChannelStub;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.WriteWhatYouReadToStreamSocketChannelStub;

public class ClientProcessorTest {

    private static final String PROCESS_READABLE_KEY_MESSAGE = "OBICHAM SHOPSKATA SALATA";
    private static final String PROCESS_READABLE_KEY_TEST_MESSAGE = "ProcessReadableKey writes to channel"
            + " invalind command for invalid command";
    private static final String EXPECTED_PROCESS_READABLE_KEY_OUTPUT = "Invalid command!";

    @Test
    public void testProcessReadableKeyReadsInputFromChannel() throws IOException {
        SelectionKey keyMock = mock(SelectionKey.class);

        OutputStream outputStream = new ByteArrayOutputStream();
        WriteWhatYouReadToStreamSocketChannelStub socketChannelStub = new WriteWhatYouReadToStreamSocketChannelStub(
                outputStream, PROCESS_READABLE_KEY_MESSAGE);

        when(keyMock.channel()).thenReturn(socketChannelStub);

        Selector selectorDummy = Selector.open();
        ClientProcessor processor = new ClientProcessor(selectorDummy);

        processor.processReadableKey(keyMock);

        verify(keyMock).channel();

        assertEquals(PROCESS_READABLE_KEY_TEST_MESSAGE, EXPECTED_PROCESS_READABLE_KEY_OUTPUT, outputStream.toString());
    }

    @Test
    public void testStopChannelStopsChannel() throws IOException {
        SelectionKey keyMock = mock(SelectionKey.class);
        SocketChannel channelMock = new DoNothingSocketChannelStub();

        Selector selectorDummy = Selector.open();
        ClientProcessor processor = new ClientProcessor(selectorDummy);

        processor.stopChannel(keyMock, channelMock);

        verify(keyMock).cancel();
    }

}
