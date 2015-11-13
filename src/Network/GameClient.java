package Network;

import com.esotericsoftware.kryonet.Listener;

import java.util.HashMap;
import java.util.Map;

public class GameClient extends Listener implements Runnable{

    static PlayerStats playerStats = new PlayerStats();
    static Network network = new Network();
    static Map<Integer, PlayerStats> players = new HashMap<Integer, PlayerStats>();
    static boolean nameSent = false;

    @Override
    public void run(){

        network.connect();

        while(true) {
            update();
        }

        //System.exit(0);
    }

    public static void update(){
        playerStats.update();

        if(playerStats.TEMPpoint != playerStats.point){
            PlayerStats Ppacket = new PlayerStats();
            //Ppacket.point = playerStats.point;
            network.client.sendUDP(Ppacket);

            playerStats.TEMPpoint = playerStats.point;

        }
        if(PlayerStats.name != "" && nameSent == false){
            PlayerStats Ppacket = new PlayerStats();
            network.client.sendUDP(Ppacket);
            nameSent=true;
        }
    }
}
