package Network;

import com.esotericsoftware.kryonet.Listener;
import java.util.HashMap;
import java.util.Map;

public class GameClient extends Listener {

    static PlayerStats playerStats = new PlayerStats();
    static Network network = new Network();
    static Map<Integer, PlayerStats> players = new HashMap<Integer, PlayerStats>();

    public static void main(String[] args) {

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
            Ppacket.point = playerStats.point;
            network.client.sendUDP(Ppacket);

            playerStats.TEMPpoint = playerStats.point;

        }
    }
}
