package fuck.pjatk.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class DatagramClient extends DatagramSocket {
    private DatagramResponse lastResponse;

    public DatagramClient() throws SocketException {
        super(9090);
    }

    public DatagramPacket createPacket(Object object) {
        var payload = object.toString().getBytes();
        return new DatagramPacket(payload, payload.length);
    }

    public void sendPacket(DatagramPacket packet, String addr, int port) throws IOException {
        packet.setAddress(InetAddress.getByName(addr));
        packet.setPort(port);
        send(packet);
    }

    public void sendObject(Object object, String addr, int port) throws IOException {
        var packet = createPacket(object);
        sendPacket(packet, addr, port);
    }

    public DatagramResponse receiveResponse() {
        lastResponse = new DatagramResponse(this, receivePacket());
        return lastResponse;
    }

    public DatagramPacket receivePacket() {
        var buffer = new byte[1024];
        var packet = new DatagramPacket(buffer, buffer.length);
        try {
            receive(packet);
            return packet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DatagramResponse getLastResponse() {
        return lastResponse;
    }
}
