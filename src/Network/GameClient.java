package Network;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

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

    public static void update(){
        PlayerStats.update();

        if(PlayerStats.TEMPpoint != PlayerStats.point){
            PlayerStats Ppacket = new PlayerStats();
            network.client.sendUDP(Ppacket);

            PlayerStats.TEMPpoint = PlayerStats.point;

        }
        if(PlayerStats.name != "" && nameSent == false){
            //PlayerStats Ppacket = new PlayerStats();
            network.client.sendUDP(/*Ppacket*/PlayerStats.name);
            nameSent=true;
        }
    }
}
