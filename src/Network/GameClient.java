package Network;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import mainGame.PlayerStats;
import mapClasses.GameMap;
import org.lwjgl.Sys;

import java.util.HashMap;
import java.util.Map;

public class GameClient extends Listener implements Runnable
{
    static Network network = new Network();
    boolean nameSent = false;
    boolean lobbyReady = false;

    @Override
    public void run()
    {

        network.connect();
        Log.set(Log.LEVEL_DEBUG);

        while (true)
        {
            update();
        }

        //System.exit(0);
    }

    public void update()
    {
        if (!nameSent)
        {
            updatePlayerName();
        }
        updatePlayerPoints();
        if (!lobbyReady)
        {
            updateLobbyReady();
        }

        updateChat();
        updateHouses();
        updateRoads();
        updateCities();
        updateThief();
        updatePlayerTurn();
        updateDice();
        //updateTrade();
        updateCards();
    }

    void updatePlayerName()
    {
        if (!PlayerStats.name.matches(""))
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            nameSent = true;
        }
    }

    void updateChat()
    {
        if (PlayerStats.textSent)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            PlayerStats.textSent = false;
        }
    }

    void updatePlayerPoints()
    {
        if (PlayerStats.TEMPpoint != PlayerStats.point)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);

            PlayerStats.TEMPpoint = PlayerStats.point;
        }
    }

    void updateLobbyReady()
    {
        if (PlayerStats.lobbyReady)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            lobbyReady = true;
        }
    }

    void updateHouses()
    {
        if (GameMap.serializedHouse[1] != 0)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            GameMap.serializedHouse[1] = 0;
        }
    }

    void updateRoads()
    {
        if (GameMap.serializedRoad[1] != 0)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            GameMap.serializedRoad[1] = 0;
        }
    }

    void updateCities()
    {
        if (GameMap.serializedCity != 0)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            GameMap.serializedCity = 0;
        }
    }

    void updateThief()
    {
        if (GameMap.serializedThief != 0)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            GameMap.serializedThief = 0;
        }
    }

    void updatePlayerTurn() {
        if (PlayerStats.endTurn) {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            PlayerStats.endTurn = false;
        }
    }
    void updateDice() {
        if (PlayerStats.diceRoll) {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            PlayerStats.diceRoll = false;
        }
    }
    void updateTrade() {
        if (PlayerStats.tradingWithyou[4]) {
            System.out.println("Sending trade data");
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            PlayerStats.tradingWithyou[4] = false;
        }
    }
    void updateCards() {
        if (PlayerStats.updateCard) {
            System.out.println("Getting card info");
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            PlayerStats.updateCard = false;
        }
    }
}