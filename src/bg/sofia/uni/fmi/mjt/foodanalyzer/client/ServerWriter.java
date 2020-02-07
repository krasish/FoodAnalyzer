package bg.sofia.uni.fmi.mjt.foodanalyzer.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ServerWriter implements Runnable {
    private static final int BUFFER_SIZE = 2048;

    private static final String WRITE_TO_SERVER_ERROR = "Writing to socketChannel was unsuccesful!";
    private static final String QUIT_REGEX = "^\\s*quit\\s*$";

    SocketChannel socketChannel;
    ByteBuffer buffer;

    public ServerWriter(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    boolean isQuitPrompt(String prompt) {
        return prompt.matches(QUIT_REGEX);
    }

    private String askForPrompt(Scanner scanner) {
        System.out.print("> ");
        String result = scanner.nextLine();
        return result;
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
            while (true) {
                String prompt = askForPrompt(scanner);
                if (isQuitPrompt(prompt)) {
                    break;
                }
                // TODO Check if prompt contains path to image and resolve barcode if so

                writeToServer(prompt, socketChannel, buffer);
            }
        }
    }
}
