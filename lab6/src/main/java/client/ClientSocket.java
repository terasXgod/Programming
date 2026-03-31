package client;

import common.Request;
import common.Response;
import exceptions.DeserializationException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientSocket implements AutoCloseable{

    private SocketChannel channel;
    private final ClientByteMapper mapper = new ClientByteMapper();
    private static final int MAX_FRAME_SIZE = 1_048_576; // 1 MB guard to avoid OOM/invalid frames

    public void connect(String host, int port) throws IOException {
        channel = SocketChannel.open(new InetSocketAddress(host, port));
    }

    public void send(Request request) throws IOException {
        if (channel == null || !channel.isOpen()) {
            throw new IOException("Socket is not connected");
        }

        byte[] data = mapper.serialize(request);
        ByteBuffer buffer = ByteBuffer.allocate(4 + data.length);
        buffer.putInt(data.length);
        buffer.put(data);
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }

    public Response receive() throws IOException, DeserializationException {
        ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
        while (sizeBuffer.hasRemaining()) {
            int read = channel.read(sizeBuffer);
            if (read == -1) {
                throw new IOException("Server closed the connection");
            }
        }

        sizeBuffer.flip();
        int frameSize = sizeBuffer.getInt();
        if (frameSize <= 0 || frameSize > MAX_FRAME_SIZE) {
            throw new DeserializationException("Invalid frame size: " + frameSize + ". Expected 1.." + MAX_FRAME_SIZE +
                    ". Ensure client prefixes payload with length.");
        }

        ByteBuffer dataBuffer = ByteBuffer.allocate(frameSize);
        while (dataBuffer.hasRemaining()) {
            int read = channel.read(dataBuffer);
            if (read == -1) {
                throw new IOException("Server closed the connection");
            }
        }

        dataBuffer.flip();
        byte[] data = new byte[dataBuffer.limit()];
        dataBuffer.get(data);
        return mapper.deserialize(data);
    }

    @Override
    public void close() throws Exception {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
    }
}
