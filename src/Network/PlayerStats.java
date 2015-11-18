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
    public static int TEMPpoint=0;
    public static boolean lobbyReady = false;

    public static String[] names = new String[]{" "," "," "," "};
    public static int[] points = new int[]{0,0,0,0};
    public static int[] knightsPlayed = new int[]{0,0,0,0};
    public static int[] resourcesOnHand = new int[]{0,0,0,0};
    public static boolean[] lobbyReadyAll = new boolean[]{false,false,false,false};
    public static int [] longestRoad = new int[]{0,0,0,0};
    public static int turn;
    public static boolean StartGame;



    public PlayerStats(){

    }
}
