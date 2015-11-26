package Network;

import com.esotericsoftware.kryonet.Connection;
import mainGame.PlayerStats;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bjørn on 16-11-2015.
 */
public class ServerData
{
    public String[] names = new String[]{"", "", "", ""};
    public int[] points = new int[]{0, 0, 0, 0};
    public int[] knightsPlayed = new int[]{0, 0, 0, 0};
    public int[] resourcesOnHand = new int[]{0, 0, 0, 0};
    public boolean[] lobbyReadyAll = new boolean[]{false, false, false, false};
    public int longestRoad[] = new int[]{0, 0, 0, 0};
    public int turn, die1, die2, ID, cardID;
    public boolean StartGame = false;
    public String[] textToRender = new String[]{"", "", ""};
    public String[] oldText = new String[10];
    public ArrayList<Integer> cards = new ArrayList<Integer>();
    public int[] serializedHouse = new int[]{0, 0};
    public boolean endTurn = false, diceRoll;
    public int turnorderturn = 1;
    public boolean gameEnded = false;
    public boolean[] playerturn = new boolean[]{false,false,false,false};
    public boolean gamestart =false;
    public Connection c;

    Integer[] yieldNumbers = {2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12};
    ArrayList<Integer> listOfYieldNumbers = new ArrayList<Integer>(Arrays.asList(yieldNumbers));
    ArrayList<String> listOfTileTypes = new ArrayList<String>(Arrays.<String>asList(
            "Grain", "Grain", "Grain", "Grain",
            "Lumber", "Lumber", "Lumber", "Lumber",
            "Wool", "Wool", "Wool", "Wool",
            "Ore", "Ore", "Ore",
            "Brick", "Brick", "Brick",
            "Desert"));

    public ServerData()
    {
    }

    public void unpack(Connection c)
    {
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
        PlayerStats.ID = c.getID();
        PlayerStats.die1 = die1;
        PlayerStats.die2 = die2;
        PlayerStats.endTurn = endTurn;
        PlayerStats.diceRoll = diceRoll;
    }
}