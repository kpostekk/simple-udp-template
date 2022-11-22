package fuck.pjatk;

import fuck.pjatk.udp.DatagramClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    private final ServerSocket tcpSocket;
    private final DatagramClient udpClient;

    public Server() throws IOException {
        tcpSocket = new ServerSocket(15559);
        udpClient = new DatagramClient();
    }

    public void listenForFlag() throws IOException {
        while (!tcpSocket.isClosed()) {
            var connection = tcpSocket.accept();
            var reader = new Scanner(connection.getInputStream());

            if (reader.nextLine().equals("2137666")) {
                var init = reader.nextLine();
                if (!init.matches("^\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}:\\d+$")) throw new RuntimeException();
                connection.close();
                runTasks(init);
            }
        }
    }

    public void runTasks(String initLine) throws IOException {
        var args = initLine.split(":");
        var addr = args[0];
        var port = args[1];

        udpClient.sendObject("Hello", addr, Integer.parseInt(port));
        var r1 = udpClient.receiveResponse().asString();
    }
}
