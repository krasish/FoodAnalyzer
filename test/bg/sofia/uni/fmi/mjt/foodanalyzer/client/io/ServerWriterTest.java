package bg.sofia.uni.fmi.mjt.foodanalyzer.client.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.StreamWritingSocketChannelStub;

public class ServerWriterTest {
    private static final int BUFFER_SIZE = 128;
    private static final String MESSAGE = "Hello, my dear friend!";
    private static final String TEST_MESSAGE = "Writing content into channel is as expected";

    @Test
    public void testWriteToServerWritesToServer() {
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub stub = new StreamWritingSocketChannelStub(outputStream);

        ServerWriter writer = new ServerWriter(stub);

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        writer.writeToServer(MESSAGE, stub, buffer);

        assertEquals(TEST_MESSAGE, MESSAGE, outputStream.toString());
    }

}
