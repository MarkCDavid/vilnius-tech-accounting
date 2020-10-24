package vilnius.tech.session;

import vilnius.tech.session.Session;

import java.io.*;
import java.util.Scanner;

public class Serializer {
    public static Session loadSession(String filename) throws SessionSerializationException {

        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            return (Session) stream.readObject();
        }
        catch (ClassNotFoundException | IOException exception) {
            throw new SessionSerializationException();
        }
    }

    public static void saveSession(Session session, String filename) throws SessionSerializationException {
        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            stream.writeObject(session);
        }
        catch (IOException exception) {
            throw new SessionSerializationException();
        }
    }
    private Serializer() {

    }
}
