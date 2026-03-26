package server;

import common.Request;
import exceptions.DeserializationException;

import java.io.*;
import java.util.logging.Logger;

/**
 * ObjectInputStream-based implementation that converts a serialized byte array into a {@link Request}.
 * Uses an ObjectInputFilter to avoid deserializing unexpected classes and keeps the API narrow
 */
public class ServerByteMapper {

    private final ObjectInputFilter objectInputFilter;
    private static final Logger LOGGER = Logger.getLogger(ServerByteMapper.class.getName());

    /**
     * Creates a deserializer with the default safe filter for request payloads.
     */
    public ServerByteMapper() {
        this(defaultFilter());
    }

    /**
     * Creates a deserializer with a custom filter.
     *
     * @param objectInputFilter filter defining allowed classes during deserialization
     */
    public ServerByteMapper(ObjectInputFilter objectInputFilter) {
        this.objectInputFilter = objectInputFilter == null ? defaultFilter() : objectInputFilter;
    }

    public Request deserialize(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            throw new DeserializationException("Empty request payload");
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {

            ois.setObjectInputFilter(objectInputFilter);
            Object candidate = ois.readObject();

            if (!(candidate instanceof Request request)) {
                throw new DeserializationException(
                        "Unexpected payload type: " + (candidate == null ? "null" : candidate.getClass().getName()));
            }

            return request;
        } catch (InvalidClassException | StreamCorruptedException e) {
            LOGGER.warning("Rejected request payload: " + e.getMessage());
            throw new DeserializationException("Rejected request payload", e);
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.warning("Failed to deserialize request: " + e.getMessage());
            throw new DeserializationException("Failed to deserialize request", e);
        }
    }

    private static ObjectInputFilter defaultFilter() {
        // Permissive filter with sane depth/size bounds to avoid accidental rejection of legitimate commands.
        return ObjectInputFilter.Config.createFilter("maxdepth=64;maxbytes=4194304;*");
    }

    public byte[] serialize(Object obj) throws IOException {
        // Создаем поток в память
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            // Записываем объект
            oos.writeObject(obj);
            oos.flush();

            // Получаем массив байтов
            return bos.toByteArray();
        }
    }
}
