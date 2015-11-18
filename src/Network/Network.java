package Network;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import mainGame.CreatePlayerState;
import mainGame.SharedData;
import mainGame.SharedPlayerStats;

import java.io.IOException;

public class Network extends Listener {

    Client client;
    String ip = "192.168.0.100";
    int port = 23820;
    SharedData data;

    public void connect() {
        client = new Client(16384,2048);
        client.getKryo().register(PlayerStats.class);
        client.getKryo().register(int[].class);
        client.getKryo().register(SharedData.class);
        client.getKryo().register(SharedPlayerStats.class);
        client.getKryo().register(boolean[].class);
        client.getKryo().register(String[].class);

        client.addListener(this);

        client.start();
        try {
            client.connect(5000, ip, port, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void received(Connection c, Object o) {
        if (o instanceof PacketAddPlayer) {
            PacketAddPlayer packet = (PacketAddPlayer) o;
            PlayerStats newPlayerStats = new PlayerStats();
            GameClient.players.put(packet.ID, newPlayerStats);
        }
        if (o instanceof PacketRemovePlayer) {
            PacketRemovePlayer packet = (PacketRemovePlayer) o;
            GameClient.players.remove(packet.ID);
        }
        if (o instanceof SharedData){
            data = (SharedData) o;
            PlayerStats.names = data.names;
            System.out.println("ARE U PRINTIN HERE??");
        }
    }
}
