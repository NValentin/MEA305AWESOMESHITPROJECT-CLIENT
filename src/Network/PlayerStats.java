package Network;


import com.esotericsoftware.kryonet.Connection;

public class PlayerStats {

    public static int ID;
    public static String name = "";
    public static int point = 0;
    public static int knights_played = 0;
    public static int length_of_road = 0;
    public static int resources_on_hand = 0;
    public Connection c;
    public static int TEMPpoint=1;
    public static boolean lobbyReady = false;

    public static String[] names;
    public static int[] points;
    public static int[] knightsPlayed = new int[]{0,0,0,0};
    public static int[] resourcesOnHand;
    public static boolean[] lobbyReadyAll;
    public static int longestRoad;
    public static int turn;



    public PlayerStats(){
        names = new String[]{" "," "," "," "};
        points = new int[]{0,0,0,0};
        knightsPlayed = new int[]{0,0,0,0};
        lobbyReadyAll = new boolean[]{false,false,false,false};

    }
}
