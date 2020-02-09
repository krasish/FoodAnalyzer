package bg.sofia.uni.fmi.mjt.foodanalyzer.server.command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs.StreamWritingSocketChannelStub;

public class InvalidCommandTest {
    private static final int BUFFER_SIZE = 128;
    private static final String MESSAGE = "Test message 123, I'm bored";
    private static final String TEST_MESSAGE = "Writing content into channel is as expected";
    private static final String EXECUTE_TEST_MESSAGE = "Executing command writes into channel as expected";
    private static final String INVALID_COMMAND_ERROR_MESSAGE = "Invalid command!";

    @Test
    public void testWriteToChannelWritesCorrectly() {
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub channelStub = new StreamWritingSocketChannelStub(outputStream);
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        InvalidCommand command = new InvalidCommand(channelStub);

        command.writeToChannel(MESSAGE, channelStub, buffer);
        assertEquals(TEST_MESSAGE, MESSAGE, outputStream.toString());
    }

    @Test
    public void testExecuteWritesExpectedMessageToChanel() {
        Database database = new Database();
        OutputStream outputStream = new ByteArrayOutputStream();
        StreamWritingSocketChannelStub channelStub = new StreamWritingSocketChannelStub(outputStream);
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        InvalidCommand command = new InvalidCommand(channelStub);

        command.execute(database, buffer);

        assertEquals(EXECUTE_TEST_MESSAGE, INVALID_COMMAND_ERROR_MESSAGE, outputStream.toString());
    }
}
