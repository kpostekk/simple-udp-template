package fuck.pjatk;

import fuck.pjatk.bootsrtap.Bootstrapper;
import fuck.pjatk.udp.DatagramClient;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        final var serverAddr = "127.0.0.1"; // adres serwera z zadania
        final var serverPort = 15559;

        var boot = new Bootstrapper(serverAddr, serverPort);
        var client = new DatagramClient();
        boot.bootstrap("172.23.129.4", client.getPort(), 2137666); // adres komputera + flaga z zadania
        System.out.println("Packet sent!");

        // Flag
        var flag = client.receiveResponse().asString();
        System.out.println("--- FLAG below ---");
        System.out.println(flag);
        System.out.println("--- FLAG above ---");
    }
}
