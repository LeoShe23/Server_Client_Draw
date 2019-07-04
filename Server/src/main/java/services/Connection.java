package services;

import entyties.Command;

import java.io.*;
import java.net.Socket;

public class Connection implements Closeable {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;


    public Connection(Socket socket) throws Exception {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

    }

    public void send(String message) throws Exception {
        out.writeObject(message);
    }
    public void send(Command message) throws Exception {
        out.writeObject(message);
    }

    public String receive() throws Exception {
        return (String) in.readObject();
    }
    public Command receiveCommand() throws Exception {
        return (Command) in.readObject();
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
