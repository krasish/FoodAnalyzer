package bg.sofia.uni.fmi.mjt.foodanalyzer.client.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.SocketChannel;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.FixedResponseSocketChannelStub;

public class ServerReaderTest {
    private static final String MESSAGE = "Kaji chestno";

    @Test
    public void testReadFromChannelGetsCorrectResponses() throws IOException {
        final String expected = MESSAGE + System.lineSeparator(); // println adds trailing \n

        SocketChannel socketChannel = new FixedResponseSocketChannelStub(MESSAGE);
        ServerReader reader = new ServerReader(socketChannel);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        reader.readFromChannel(socketChannel, printStream);

        String actual = outputStream.toString();

        assertEquals(expected, actual);
    }

}
