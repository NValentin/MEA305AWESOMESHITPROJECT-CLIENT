package Network;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import mainGame.PlayerStats;
import mainGame.GameMap;

/**
 * This class handles data transmission to the server.
 */
public class GameClient extends Listener implements Runnable
{
    static Network network = new Network();
    boolean nameSent = false;
    boolean lobbyReady = false;

    /**
     * Run is called, when a player first presses join game. This method establishes the connection with the server.
     */
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

    /**
     * Standard update method. Runs methods and checks for conditionals, updates accordingly.
     * The following methods are all various instances in which data needs to be sent to the server, in order to update the game
     */
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
        updateTrade();
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
        if (GameMap.serializedCity != 99)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            GameMap.serializedCity = 99;
        }
    }

    void updateThief()
    {
        if (GameMap.serializedThief != 99)
        {
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            GameMap.serializedThief = 99;
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
        if (PlayerStats.sendTrade) {
            System.out.println("Sending trade data");
            ClientData clientData = new ClientData();
            clientData.pack();
            network.client.sendUDP(clientData);
            PlayerStats.sendTrade = false;
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