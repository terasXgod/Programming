package client;

import common.Response;

import java.io.*;

public class ClientByteMapper {


    public byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            oos.flush();
            return bos.toByteArray();
        }
    }

    public Response deserialize(byte[] bytes) throws IOException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (Response) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Invalid response payload", e);
        }
    }

}
