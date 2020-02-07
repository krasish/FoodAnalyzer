package bg.sofia.uni.fmi.mjt.foodanalyzer.server.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.FixedResponseSocketChannelStub;

public class ChannelReaderTest {

    @Test
    public void testReadFromChannelReturnsContentOFChannel() {
        final int bufferCapacity = 1024;
        ByteBuffer buffer = ByteBuffer.allocate(bufferCapacity);
        FixedResponseSocketChannelStub socketChannelStub = new FixedResponseSocketChannelStub("Alabala portokala");
        ChannelReader channelReader = new ChannelReader(socketChannelStub, buffer);
        try {
            assertEquals("Read form channel returns string with content", socketChannelStub.getMessage(),
                    channelReader.read());
        } catch (IOException e) {
            fail();
        }
    }

}
