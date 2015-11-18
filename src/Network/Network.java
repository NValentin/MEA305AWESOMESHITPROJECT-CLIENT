package Network;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import mainGame.CreatePlayerState;
import mainGame.SharedData;
import mainGame.SharedPlayerStats;

import java.io.IOException;
import java.net.InetAddress;

public class Network extends Listener {

    Client client;
    int port = 23820;
    SharedData data;
    InetAddress ip;

    public void connect() {
        client = new Client(16384,2048);
        client.getKryo().register(PlayerStats.class);
        client.getKryo().register(int[].class);
        client.getKryo().register(SharedData.class);
        client.getKryo().register(SharedPlayerStats.class);
        client.getKryo().register(boolean[].class);
        client.getKryo().register(String[].class);

        client.addListener(this);

        ip = client.discoverHost(port,port);
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
            data.updateStats();
            PlayerStats.names = data.names;
            PlayerStats.lobbyReadyAll = data.lobbyReadyAll;
            System.out.println("ARE U PRINTIN HERE??");
            for (int i =0; i<PlayerStats.names.length; i++){
                System.out.println(PlayerStats.names[i]);
            }
        }
    }
}
