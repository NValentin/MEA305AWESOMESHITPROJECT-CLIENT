package Network;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import mainGame.CreatePlayerState;

import java.io.IOException;

public class Network extends Listener {

    Client client;
    String ip = "192.168.0.100";
    int port = 23820;

    public void connect() {
        client = new Client();
        client.getKryo().register(PlayerStats.class);
        client.getKryo().register(CreatePlayerState.class);
        client.addListener(this);

        client.start();
        try {
            client.connect(5000, ip, port, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void received(Connection c, Object o) {
        if (o instanceof PacketAddPlayer) {
            PacketAddPlayer packet = (PacketAddPlayer) o;
            PlayerStats newPlayerStats = new PlayerStats();
            GameClient.players.put(packet.ID, newPlayerStats);

        } else if (o instanceof PacketRemovePlayer) {
            PacketRemovePlayer packet = (PacketRemovePlayer) o;
            GameClient.players.remove(packet.ID);
        }
    }
}
