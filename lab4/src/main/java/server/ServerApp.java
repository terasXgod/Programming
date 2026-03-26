package server;

import java.util.logging.Level;
import java.util.logging.Logger;
import server.collection.CollectionManager;
import server.data.DataLoader;
import server.history.HistoryService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Entry point coordinator that loads data, wires services, and listens for client connections.
 */
public class ServerApp {
    private final int port;
    private final CollectionManager collectionManager;
    private final RequestHandler requestHandler;
    private final HistoryService historyService;
    private final String filePath;
    private final Logger logger = Logger.getLogger(ServerApp.class.getName());
    private final EventHandler eventHandler;

    /**
     * Constructs the server with port and backing file for persistence.
     *
     * @param port     listening TCP port
     * @param filePath path to the CSV data file
     */
    public ServerApp(int port, String filePath) {
        this.port = port;
        this.filePath = filePath;
        this.collectionManager = new CollectionManager(DataLoader.loadVehicles());
        this.historyService = new HistoryService();
        this.requestHandler = new RequestHandler(collectionManager, historyService);
        this.eventHandler = new EventHandler(requestHandler);
    }

    public void run() {
        try (
                Selector selector = Selector.open();
                ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ){
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.configureBlocking(false);

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            logger.log(Level.INFO, String.format("Listening on port %d", port));

            while (true) {
                if (selector.select() == 0) continue;

                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (!key.isValid()) continue;

                    if (key.isAcceptable()) {
                        eventHandler.handleAccept(key);
                    } else if (key.isReadable()) {
                        eventHandler.handleRead(key);
                    }
                }
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

}
