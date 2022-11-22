package fuck.pjatk.bootsrtap;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klasa odpowiadająca za uruchomienie zdalnego serwera, który będzie wysyłał komunikaty do klienta.
 */
public class Bootstrapper extends Socket {
    /**
     *
     * @param serverAddress adres serwera podanego w zadaniu
     * @param serverPort port serwera podany w zadaniu
     */
    public Bootstrapper(String serverAddress, int serverPort) throws IOException {
        super(serverAddress, serverPort);
    }

    /**
     * Wymagane przez 1. zadanie.
     *
     * @param clientAddress adres twojego komputera
     * @param clientPort port twojego klienta
     * @param flag flaga podana w zadaniu
     */
    public void bootstrap(String clientAddress, int clientPort, int flag) throws IOException {
        var writer = new PrintStream(getOutputStream());
        var payload = String.format("%s:%d", clientAddress, clientPort);
        writer.println(flag);
        writer.println(payload);
    }
}
