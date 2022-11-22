package fuck.pjatk;

import fuck.pjatk.bootsrtap.Bootstrapper;
import fuck.pjatk.udp.DatagramClient;
import fuck.pjatk.udp.DatagramResponse;
import fuck.pjatk.utils.GCD;
import fuck.pjatk.utils.PowerOf;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class Client {
    public static void main(String[] args) throws IOException {
        final var serverAddr = "127.0.0.1"; // adres serwera z zadania
        final var serverPort = 15559;

        var boot = new Bootstrapper(serverAddr, serverPort);
        var client = new DatagramClient();
        boot.bootstrap("127.0.0.1", client.getLocalPort(), 2137666); // adres komputera + flaga z zadania
        System.out.println("Packet sent!");

        // Pakiet identyfikujący
        client.receiveResponse();

        // 3.
        client.getLastResponse().reply(client.getLocalPort());

        // 4.
        var x = client.receiveResponse().asBigDecimal();
        var k = PowerOf.findPower(x, 2);
        client.getLastResponse().reply(k.add(BigDecimal.TEN));

        // 5.
        var gcd = Stream.generate(client::receiveResponse)
                .limit(4)
                .map(DatagramResponse::asBigDecimal)
                .reduce(GCD::gcd)
                .orElseThrow();
        client.getLastResponse().reply(gcd);

        // 6.
        var txt1 = client.receiveResponse().asString().replaceAll("0", "");
        client.getLastResponse().reply(txt1);

        // Flag
        var flag = client.receiveResponse().asString();
        System.out.println("--- FLAG below ---");
        System.out.println(flag);
        System.out.println("--- FLAG above ---");
    }
}
