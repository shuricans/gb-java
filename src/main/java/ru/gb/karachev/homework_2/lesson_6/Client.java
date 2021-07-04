package ru.gb.karachev.homework_2.lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;

public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;

    public static void main(String[] args) {
        new Client(6543);
    }

    public Client(int port) {
        startClient(port);
    }

    private void startClient(int port) {
        ExecutorService executor = null;
        try {
            socket = new Socket("localhost", port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            executor = Executors.newSingleThreadExecutor();
            executor.submit(new Reader(in));

            scanner = new Scanner(System.in);

            while (true) {
                String msg = scanner.nextLine();
                if("/end".equalsIgnoreCase(msg)) {
                    break;
                }
                out.writeUTF(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(executor != null) {
                executor.shutdown();
            }
            closeConnections();
        }
    }

    private void closeConnections() {
        try {
            in.close();
            out.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Reader implements Runnable {

        private final DataInputStream in;

        public Reader(DataInputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    String msg = in.readUTF();
                    System.out.println("server: " + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
