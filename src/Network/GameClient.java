package Network;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import mainGame.PlayerStats;

import java.util.HashMap;
import java.util.Map;

public class GameClient extends Listener implements Runnable{

    //static PlayerStats playerStats = new PlayerStats();
    static Network network = new Network();
    static Map<Integer, PlayerStats> players = new HashMap<Integer, PlayerStats>();
    boolean nameSent = false;

    @Override
    public void run(){

        network.connect();
        Log.set(Log.LEVEL_DEBUG);

        while(true) {
            update();
        }

        //System.exit(0);
    }

    public void update(){
        updatePlayerName();
        updatePlayerPoints();
        updateLobbyReady();
        updateChat();
    }
    void updatePlayerName(){
        if(!PlayerStats.name.matches("") && !nameSent) {
            System.out.println("PlayerStats.name is not null, SEND STUFF");
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            nameSent = true;
        }
    }
    void updateChat(){
        if(PlayerStats.textSent){
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            PlayerStats.textSent = false;
        }
    }

    void updatePlayerPoints(){
        if(PlayerStats.TEMPpoint != PlayerStats.point){
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);

            PlayerStats.TEMPpoint = PlayerStats.point;
        }
    }
    void updateLobbyReady(){
        if(PlayerStats.lobbyReady){
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            PlayerStats.lobbyReady=false;
        }
    }
}