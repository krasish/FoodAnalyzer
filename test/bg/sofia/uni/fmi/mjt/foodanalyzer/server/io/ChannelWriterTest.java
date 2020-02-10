package bg.sofia.uni.fmi.mjt.foodanalyzer.server.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.StreamWritingSocketChannelStub;

public class ChannelWriterTest {

    private static final int BUFFER_SIZE = 128;
    private static final String MESSAGE = "Zdravei, kak si, priqteliu?";
    private static final String TEST_MESSAGE = "Channel writer writes to channel";

    @Test
    public void testWriteWritesToChannel() {
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub stub = new StreamWritingSocketChannelStub(outputStream);
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        ChannelWriter writer = new ChannelWriter(stub, buffer);

        try {
            writer.write(MESSAGE);
        } catch (IOException e) {
            fail();
        }

        assertEquals(TEST_MESSAGE, MESSAGE, outputStream.toString());
    }

}
