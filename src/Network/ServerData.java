package Network;

import mainGame.PlayerStats;

/**
 * Created by Bjørn on 16-11-2015.
 */
public class ServerData {
    public String[] names = new String[]{"","","",""};
    public int[] points = new int[]{0,0,0,0};
    public int[] knightsPlayed = new int[]{0,0,0,0};
    public int[] resourcesOnHand = new int[]{0,0,0,0};
    public boolean[] lobbyReadyAll = new boolean[]{false,false,false,false};
    public int longestRoad[] = new int[]{0,0,0,0};
    public int turn, dice1, dice2;
    public boolean StartGame = false;
    public String[] textToRender = new String[]{"","",""};
    public String[] oldText = new String[10];
    public int ID;

    public ServerData(){
    }
    public void unpack(){
        PlayerStats.names = names;
        PlayerStats.points = points;
        PlayerStats.knightsPlayed = knightsPlayed;
        PlayerStats.resourcesOnHand = resourcesOnHand;
        PlayerStats.lobbyReadyAll = lobbyReadyAll;
        PlayerStats.longestRoad = longestRoad;
        PlayerStats.turn = turn;
        PlayerStats.StartGame = StartGame;
        PlayerStats.textToRender = textToRender;
        PlayerStats.oldText = oldText;
        PlayerStats.ID = ID;

    }
}