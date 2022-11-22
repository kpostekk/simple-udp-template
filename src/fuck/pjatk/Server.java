package fuck.pjatk;

import fuck.pjatk.udp.DatagramClient;
import fuck.pjatk.utils.GCD;
import fuck.pjatk.utils.PowerOf;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            System.out.println("got connection from " + connection.getInetAddress().getHostName());

            var initFlag = reader.nextLine();
            if (!initFlag.equals("2137666")) throw new RuntimeException();
            var initArgs = reader.nextLine();
            runTasks(initArgs);
        }
    }

    public void runTasks(String initLine) throws IOException {
        var args = initLine.split(":");
        var addr = args[0];
        var port = args[1];

        // pakiet identyfikujący
        udpClient.sendObject(1, addr, Integer.parseInt(port));

        // 3.
        var r3 = udpClient.receiveResponse();
        var r3actual = r3.asBigDecimal().intValue();
        var r3expected = r3.getPacket().getPort();
        assert r3expected == r3actual;

        // 4.
        var r4arg = new Random().nextLong();
        var r4expected = PowerOf.findPower(new BigDecimal(r4arg), 2);
        udpClient.getLastResponse().reply(r4arg);
        var r4 = udpClient.receiveResponse();
        var r4actual = r4.asBigDecimal();
        assert r4expected.equals(r4actual);

        // 5.
        var r5arg = Stream.generate(() -> new BigDecimal(new Random().nextLong()).abs()).limit(4).collect(Collectors.toList());
        var r5expected = GCD.gcd(r5arg.toArray(BigDecimal[]::new));
        for (var b : r5arg) {
            udpClient.getLastResponse().reply(b);
        }
        var r5 = udpClient.receiveResponse();
        var r5received = r5.asBigDecimal();
        assert r5expected.equals(r5received);

        // 6.
        var r6arg = Long.valueOf(new Random().nextLong()).toString();
        udpClient.getLastResponse().reply(r6arg);
        var r6expected = r6arg.replaceAll("0", "");
        var r6 = udpClient.receiveResponse();
        var r6actual = r6.asString();
        assert r6expected.equals(r6actual);

        // send flag
        udpClient.getLastResponse().reply("58008");
    }

    public static void main(String[] args) throws IOException {
        var s = new Server();
        s.listenForFlag();
    }
}
