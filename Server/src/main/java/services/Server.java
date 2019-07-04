package services;

import entyties.Command;
import entyties.DrawingElement;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 29288;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (Connection connection = new Connection(socket)) {
                List<String> list = fileParse();
                while (!list.isEmpty()) {
                    String[] arr = list.get(0).split(";");
                    DrawingElement element = new DrawingElement(arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), arr[4]);
                    Command command = new Command(arr[0], element);
                    connection.send(command);
                    list.remove(0);
                }

            } catch (Exception e) {
                System.out.println("UPS! Something wrong :(");
            }
        }

        private static List<String> fileParse() {
            List<String> list = new ArrayList<>();
            String file = "src/main/resources/test.txt";
            try {
                list = Files.readAllLines(Paths.get(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

    }
}
