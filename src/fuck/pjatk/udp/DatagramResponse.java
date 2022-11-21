package fuck.pjatk.udp;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class DatagramResponse {
    private final InetAddress replyAddress;
    private final int replyPort;
    private final DatagramClient client;
    private final DatagramPacket packet;

    public DatagramResponse(DatagramClient client, DatagramPacket packet) {
        this.client = client;
        this.packet = packet;
        this.replyAddress = packet.getAddress();
        this.replyPort = packet.getPort();
    }

    public void reply(Object object) throws IOException {
        client.sendObject(object, String.valueOf(replyAddress), replyPort);
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    public String asString() {
        return new String(packet.getData(), packet.getOffset(), packet.getLength());
    }

    public BigDecimal asBigDecimal() {
        return new BigDecimal(asString());
    }
}
