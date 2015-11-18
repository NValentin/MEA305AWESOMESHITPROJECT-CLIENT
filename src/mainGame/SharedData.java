package mainGame;

import Network.PlayerStats;

/**
 * Created by Bjørn on 16-11-2015.
 */
public class SharedData {
    public String[] names = new String[]{"","","",""};
    public int[] points = new int[]{0,0,0,0};
    public int[] knightsPlayed = new int[]{0,0,0,0};
    public int[] resourcesOnHand = new int[]{0,0,0,0};
    public boolean[] lobbyReadyAll = new boolean[]{false,false,false,false};
    public int longestRoad[] = new int[]{0,0,0,0};
    public int turn;
    public boolean StartGame = false;

    public SharedData(){

    }

    public void updateStats(){
        PlayerStats.names = names;
        PlayerStats.points = points;
        PlayerStats.knightsPlayed = knightsPlayed;
        PlayerStats.resourcesOnHand = resourcesOnHand;
        PlayerStats.lobbyReadyAll = lobbyReadyAll;
        PlayerStats.longestRoad = longestRoad;
        PlayerStats.turn = turn;
        PlayerStats.StartGame = StartGame;

    }
}