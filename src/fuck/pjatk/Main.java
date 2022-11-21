package fuck.pjatk;

import fuck.pjatk.bootsrtap.Bootstrapper;
import fuck.pjatk.udp.DatagramResponse;
import fuck.pjatk.udp.DatagramClient;
import fuck.pjatk.utils.PowerOf;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        final var serverAddr = "172.21.48.41"; // adres serwera z zadania
        final var serverPort = 15559;

        var boot = new Bootstrapper(serverAddr, serverPort);
        var client = new DatagramClient();
        boot.bootstrap("172.23.129.4", 9090, 131668); // adres komputera + flaga z zadania
        System.out.println("Packet sent!");

        // 2. W 4 kolejnych liniach odbierz 4 liczb(y) naturalnych(e). Policz sumę tych liczb i odeślij.
        var responses = Stream.generate(client::receiveResponse)
                .limit(4)
                .toList();
        var sum = responses.stream().map(DatagramResponse::asBigDecimal).reduce(BigDecimal.ZERO, BigDecimal::add);
        client.getLastResponse().reply(sum);

        // przykład
        var n1 = client.receiveResponse().asBigDecimal();
        var n2 = client.receiveResponse().asBigDecimal();
        var n3 = client.receiveResponse().asBigDecimal();
        var n4 = client.receiveResponse().asBigDecimal();

        var sum2 = n1.add(n2).add(n3).add(n4);
        client.getLastResponse().reply(sum2);

        // 3. Wyślij numer portu z którego się komunikujesz.
        client.sendObject(client.getLocalPort(), serverAddr, serverPort);

        // 4. Odbierz liczbę naturalną x. Oblicz największą liczbę naturalną k, taką, że k podniesione do potęgi 7 jest nie większe niż wartość x. Odeślij wartość k.
        var x = client.receiveResponse().asBigDecimal();
        var result = PowerOf.findPower(x, 7);
        client.getLastResponse().reply(result);

        // 5. Odbierz napis. Usuń z niego wszystkie wystąpienia 5 i odeślij wynik.
        var text = client.receiveResponse().asString();
        var resultText = text.replaceAll("5", "");
        client.sendObject(resultText, serverAddr, serverPort);

        // Flag
        var flag = client.receiveResponse().asString();
        System.out.println("--- FLAG below ---");
        System.out.println(flag);
        System.out.println("--- FLAG above ---");
    }
}
