package mainGame;

/**
 * Created by Bjørn on 16-11-2015.
 */
public class SharedData {
    public String[] names = new String[]{"","","",""};
    public int[] points = new int[]{0,0,0,0};
    public int[] knightsPlayed = new int[]{0,0,0,0};
    public int[] resourcesOnHand = new int[]{0,0,0,0};
    public boolean[] lobbyReady = new boolean[]{false,false,false,false};
    public int longestRoad;
    public int turn;
}