package Network;

import mainGame.PlayerStats;
import mapClasses.GameMap;

public class ClientData
{
    public int nsID;
    public String nsname;
    public int nspoint;
    public int nsknights_played;
    public int nslength_of_road;
    public int nsresources_on_hand;
    public boolean nslobbyReady;
    public String[] nstextPackage = new String[]{"", "", ""};
    public boolean nsTextSent;
    public int[] serializedHouse = new int[]{0, 0};
    public boolean endTurn = false, diceRoll;
    public boolean gameEnded = false;
    public boolean[] playerturn = new boolean[]{false,false,false,false};
    public boolean gamestart =false;

    public ClientData()
    {
    }

    public void pack()
    {
        nsID = PlayerStats.ID;
        nsname = PlayerStats.name;
        nspoint = PlayerStats.point;
        nsknights_played = PlayerStats.knights_played;
        nslength_of_road = PlayerStats.length_of_road;
        nsresources_on_hand = PlayerStats.resources_on_hand;
        nslobbyReady = PlayerStats.lobbyReady;
        nstextPackage = PlayerStats.textPackage;
        nsTextSent = PlayerStats.textSent;
        endTurn = PlayerStats.endTurn;
        diceRoll = PlayerStats.diceRoll;
        serializedHouse = GameMap.serializedHouse;
    }

}
