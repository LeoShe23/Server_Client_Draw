package services;

import entyties.Command;
import entyties.DrawingElement;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client extends JFrame {

    private Connection connection;
    List<DrawingElement> list = new ArrayList<>();
    JFrame frame = new JFrame("Client");

    private String getServerAddress() {
        return "localhost";
    }
    private int getServerPort() {
        return 29288;
    }
    private final String MAC = "60:21:C0:2A:E0:33";

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void initClient() {
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setVisible(true);
        DrawPanel board = new DrawPanel(list);
        frame.add(board);
    }

    public void run() {
        try {
            connection = new Connection(new Socket(getServerAddress(), getServerPort()));
            SocketThread socketThread = new SocketThread();
            socketThread.setDaemon(true);
            socketThread.start();
            initClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMAC() {
        return MAC;
    }

    public class SocketThread extends Thread {
        private volatile DrawingElement element;

        public DrawingElement getElement() {
            return element;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Command command = connection.receiveCommand();
                    if (command == null)
                        break;
                    if (command.getMac().equals(MAC)) {
                        element = command.getElement();
                        System.out.println(element.WIDTH);
                        System.out.println(element.HEIGHT);
                        System.out.println(element.getAction());
                        System.out.println(element.getX());
                        System.out.println(element.getY());
                        System.out.println(element.getColor());
                        list.add(element);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
