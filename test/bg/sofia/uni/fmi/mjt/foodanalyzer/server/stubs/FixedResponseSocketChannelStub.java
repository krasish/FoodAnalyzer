package bg.sofia.uni.fmi.mjt.foodanalyzer.server.stubs;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

public class FixedResponseSocketChannelStub extends SocketChannel {
    public String message;

    protected FixedResponseSocketChannelStub(SelectorProvider provider) {
        super(provider);
    }

    public FixedResponseSocketChannelStub(String message) {
        super(null);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public <T> T getOption(SocketOption<T> name) throws IOException {
        return null;
    }

    @Override
    public Set<SocketOption<?>> supportedOptions() {
        return null;
    }

    @Override
    public SocketChannel bind(SocketAddress local) throws IOException {
        return null;
    }

    @Override
    public <T> SocketChannel setOption(SocketOption<T> name, T value) throws IOException {
        return null;
    }

    @Override
    public SocketChannel shutdownInput() throws IOException {
        return null;
    }

    @Override
    public SocketChannel shutdownOutput() throws IOException {
        return null;
    }

    @Override
    public Socket socket() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public boolean isConnectionPending() {
        return false;
    }

    @Override
    public boolean connect(SocketAddress remote) throws IOException {
        return false;
    }

    @Override
    public boolean finishConnect() throws IOException {
        return false;
    }

    @Override
    public SocketAddress getRemoteAddress() throws IOException {
        return null;
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        dst.put(message.getBytes());
        return 1;
    }

    @Override
    public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
        return 0;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        return 0;
    }

    @Override
    public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
        return 0;
    }

    @Override
    public SocketAddress getLocalAddress() throws IOException {
        return null;
    }

    @Override
    protected void implCloseSelectableChannel() throws IOException {

    }

    @Override
    protected void implConfigureBlocking(boolean block) throws IOException {

    }

}
