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
        final var serverAddr = "172.21.48.41";
        final var serverPort = 15559;

        var boot = new Bootstrapper(serverAddr, serverPort);
        var client = new DatagramClient();
        boot.bootstrap("172.23.129.4", 9090, 131668);
        System.out.println("Packet sent!");

        // 2. W 4 kolejnych liniach odbierz 4 liczb(y) naturalnych(e). Policz sumę tych liczb i odeślij.
        var responses = Stream.generate(client::receiveResponse)
                .limit(4)
                .toList();
        var sum = responses.stream().map(DatagramResponse::asBigDecimal).reduce(BigDecimal.ZERO, BigDecimal::add);
        client.getLastResponse().reply(sum);

        /*

        // 3. Wyślij numer portu z którego się komunikujesz.
        client.sendObject(client.getLocalPort(), serverAddr, serverPort);

        // 4. Odbierz liczbę naturalną x. Oblicz największą liczbę naturalną k, taką, że k podniesione do potęgi 7 jest nie większe niż wartość x. Odeślij wartość k.
        var x = client.receiveBigDecimal();
        var result = PowerOf.findPower(x, 7);
        client.sendObject(result, serverAddr, serverPort);

        // 5. Odbierz napis. Usuń z niego wszystkie wystąpienia 5 i odeślij wynik.
        var text = client.receiveString();
        var resultText = text.replaceAll("5", "");
        client.sendObject(resultText, serverAddr, serverPort);

        // Flag
        var flag = client.receiveString();
        System.out.println(flag);

         */
    }
}
