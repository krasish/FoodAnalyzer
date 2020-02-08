package bg.sofia.uni.fmi.mjt.foodanalyzer.client.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import bg.sofia.uni.fmi.mjt.foodanalyzer.client.resolver.CommandResolver;

public class ServerWriter implements Runnable {
    private static final int BUFFER_SIZE = 2048;

    private static final String WRITE_TO_SERVER_ERROR = "Writing to socketChannel was unsuccesful!";
    private static final String UNPARSABLE_COMMAND_ERROR = "Unsupported command!";

    SocketChannel socketChannel;
    ByteBuffer buffer;

    public ServerWriter(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    private String askForPrompt(Scanner scanner) {
        System.out.print("> ");
        String result = scanner.nextLine();
        return result;
    }

    private void displayCommandError() {
        System.err.println(UNPARSABLE_COMMAND_ERROR);
    }

    private void writeToServer(String prompt, SocketChannel socketChannel, ByteBuffer buffer) {
        try {
            buffer.clear();
            buffer.put(prompt.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
        } catch (IOException e) {
            System.out.println(WRITE_TO_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            CommandResolver resolver = new CommandResolver();
            while (true) {
                String prompt = askForPrompt(scanner);
                if (resolver.isQuitPrompt(prompt)) {
                    break;
                }
                try {
                    prompt = resolver.resolve(prompt);
                } catch (IllegalArgumentException iae) {
                    displayCommandError();
                    continue;
                }

                writeToServer(prompt, socketChannel, buffer);
            }
        }
    }
}
