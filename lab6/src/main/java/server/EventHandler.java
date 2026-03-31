package server;

import common.Request;
import common.Response;
import exceptions.DeserializationException;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventHandler {

    private final Logger logger = Logger.getLogger(EventHandler.class.getName());
    private final ByteHandler byteHandler;
    private final RequestHandler requestHandler;

    public EventHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.byteHandler = new ByteHandler();
    }

    public void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        try {
            SocketChannel clientChannel = channel.accept();
            if (clientChannel == null) {
                channel.close();
                key.cancel();
                return;
            }
            clientChannel.configureBlocking(false);
            logger.log(Level.INFO, "Accepted connection from " + clientChannel.socket().getRemoteSocketAddress());
            clientChannel.register(key.selector(), SelectionKey.OP_READ);
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Failed to accept connection: " + e.getMessage());
        }
    }

    public void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        try {
            Request request = byteHandler.getRequest(clientChannel);
            if (request == null) {
                return; // not enough data yet or client closed
            }
            Response response = requestHandler.handle(request);
            byteHandler.sendResponse(clientChannel, response);
            logger.log(Level.INFO, "Send response: " + response.toString());
        } catch (DeserializationException e) {
            logger.log(Level.WARNING, e.getMessage());
            clientChannel.close();
        }
    }

}
