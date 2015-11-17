package Network;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import mainGame.SharedPlayerStats;

import java.util.HashMap;
import java.util.Map;

public class GameClient extends Listener implements Runnable{

    //static PlayerStats playerStats = new PlayerStats();
    static Network network = new Network();
    static Map<Integer, PlayerStats> players = new HashMap<Integer, PlayerStats>();
    static boolean nameSent = false;

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

        if(PlayerStats.TEMPpoint != PlayerStats.point){
            SharedPlayerStats sps = new SharedPlayerStats();
            sps.updateStats();
            network.client.sendUDP(sps);

            PlayerStats.TEMPpoint = PlayerStats.point;

        }
        if(PlayerStats.name != "" && nameSent == false){
            SharedPlayerStats sps = new SharedPlayerStats();
            sps.updateStats();
            network.client.sendUDP(sps);
            nameSent=true;
        }
    }
}