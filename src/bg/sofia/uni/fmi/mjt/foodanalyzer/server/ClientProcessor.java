package bg.sofia.uni.fmi.mjt.foodanalyzer.server;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bg.sofia.uni.fmi.mjt.foodanalyzer.server.database.Database;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.runnables.ClientAcceptor;
import bg.sofia.uni.fmi.mjt.foodanalyzer.server.runnables.ClientHandler;

public class ClientProcessor implements Closeable {
    private static final String SELECTOR_ERROR_MESSAGE = "Selection of keys was unsuccessful";
    private static final int SLEEP_MILIS = 256;
    private Selector selector;
    private ExecutorService executor;
    private Database database;

    public ClientProcessor(Selector selector) {
        this.selector = selector;
        this.executor = Executors.newCachedThreadPool();
        this.database = new Database();
    }

    public void process() {

        while (true) {

            if (countReadyKeys() == 0) {
                busyWait();
                continue;
            }
            var selectedKeys = selector.selectedKeys();
            System.out.println(selectedKeys.size());
            Iterator<SelectionKey> iterator = selectedKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey currentKey = iterator.next();
                // System.out.println(System.currentTimeMillis());
                if (currentKey.isAcceptable()) {
                    ClientAcceptor acceptor = new ClientAcceptor(selector, currentKey);
                    executor.execute(acceptor);
                } else if (currentKey.isReadable()) {
                    ClientHandler handler = new ClientHandler(currentKey, database);
                    executor.execute(handler);
                }
                iterator.remove();
            }
        }
    }

    int countReadyKeys() {
        try {
            return selector.select();
        } catch (IOException ioe) {
            System.out.println(SELECTOR_ERROR_MESSAGE);
            return 0;
        }
    }

    void busyWait() {
        try {
            Thread.sleep(SLEEP_MILIS);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }

    @Override
    public void close() throws IOException {
        executor.shutdown();
    }
}
