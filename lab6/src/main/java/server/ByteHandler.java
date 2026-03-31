package server;

import common.Request;
import common.Response;
import exceptions.DeserializationException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class ByteHandler {

    private final ServerByteMapper mapper;
    private final Logger logger = Logger.getLogger(ByteHandler.class.getName());
    private static final int MAX_FRAME_SIZE = 1_048_576; // 1 MB guard to avoid OOM/invalid frames

    public ByteHandler() {
        this.mapper = new ServerByteMapper();
    }

    public Request getRequest(SocketChannel channel) throws IOException, DeserializationException {
        ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
        while (sizeBuffer.hasRemaining()) {
            int read = channel.read(sizeBuffer);
            if (read == -1) return null; // client closed
            if (read == 0) continue;     // non-blocking: try again
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
            if (read == -1) return null; // client closed
            if (read == 0) continue;     // wait for more data
        }

        dataBuffer.flip();
        byte[] data = new byte[dataBuffer.limit()];
        dataBuffer.get(data);
        return mapper.deserialize(data);
    }

    public void sendResponse(SocketChannel channel, Response response) throws IOException {
        byte[] data = mapper.serialize(response);
        ByteBuffer buffer = ByteBuffer.allocate(4 + data.length);
        buffer.putInt(data.length);
        buffer.put(data);
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }
}
