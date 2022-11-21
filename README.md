# UDP

## Konfiguracja templatki

```java
public class Main {
    public static void main(String[] args) throws IOException {
        final var serverAddr = "172.21.48.41"; // adres serwera z zadania
        final var serverPort = 15559;

        var boot = new Bootstrapper(serverAddr, serverPort);
        var client = new DatagramClient();
        boot.bootstrap("172.23.129.4", 9090, 131668); // adres komputera + flaga z zadania
        System.out.println("Packet sent!");
        
        // ...
    }
}
```

`serverAddr`, `serverPort` - adres i port **serwera** z zadania

`Bootstrapper` - klasa odpowiedzialna za wysłanie pakietu z flagą i rozpoczęcie komunikacji z serwerem
`DatagramClient` - klasa odpowiedzialna za komunikację z serwerem
`bootstrap` - metoda odpowiedzialna za wysłanie pakietu z flagą i rozpoczęcie komunikacji z serwerem
sout - potwierdzenie wysłania pakietu z flagą

## FUC (frequently used code) 

### Wysyłanie pakietu udp

Wysłanie pakietu UDP jako pierwszy
```
client.sendObject("twoja widaomosc", "172.21.adres.serwera", port.serwera);
```

Odpowiedź gdy serwer wysyła pakiet
```
client.getLastResponse().reply("twoja odpowiedz");
```

### Odbieranie pakietu UDP (datagramu)

```
var txt = client.receiveResponse().asString();
```

```
var bd = client.receiveResponse().asBigDecimal();
```

### GCD

```
var n1 = client.receiveResponse().asBigDecimal();
var n2 = client.receiveResponse().asBigDecimal();
var n3 = client.receiveResponse().asBigDecimal();
var n4 = client.receiveResponse().asBigDecimal();

var gcd = GDC.gcd(n1, n2, n3, n4);
client.getLastResponse().reply(gcd);
```

### Podaj liczbę k...

```
var x = client.receiveResponse().asBigDecimal();
var k = PowerOf.findPower(x, potęgaZPolecenia);
client.getLastResponse().reply(k);
```

### Powtarzanie tekstu (konkatenacja)

```
var txt = client.receiveResponse().asString();
var repeated = txt.repeat(liczbaPowtorzen);
client.getLastResponse().reply(repeated);
```
