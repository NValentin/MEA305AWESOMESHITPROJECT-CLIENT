package Network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import mainGame.PlayerStats;
import mapClasses.GameMap;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class Network extends Listener
{

    Client client;
    int port = 23820;
    ServerData serverData;
    InetAddress ip;

    public static Boolean isConnected = false;
    public static ArrayList<String> serverListOfTypes;
    public static ArrayList<Integer> serverListOfYieldNumbers;

    public void connect()
    {
        client = new Client(16384, 2048);
        client.getKryo().register(int[].class);
        client.getKryo().register(ServerData.class);
        client.getKryo().register(ClientData.class);
        client.getKryo().register(boolean[].class);
        client.getKryo().register(String[].class);
        client.getKryo().register(ArrayList.class);
        client.getKryo().register(Integer.class);
        client.getKryo().register(Integer[].class);

        client.addListener(this);

        ip = client.discoverHost(port, port);
        client.start();
        try
        {
            client.connect(5000, ip, port, port);
            isConnected = true;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void received(Connection c, Object o)
    {
        if (o instanceof ServerData)
        {
            serverData = (ServerData) o;
            serverData.unpack(c);

            serverListOfTypes = serverData.listOfTileTypes;
            serverListOfYieldNumbers = serverData.listOfYieldNumbers;

            GameMap.deSerializedHouse = serverData.serializedHouse;
            GameMap.deSerializedRoad = serverData.serializedRoad;

            PlayerStats.playerturn = serverData.playerturn;
        }
    }
}
