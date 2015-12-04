package mainGame;

/**
 * Created by Kingo on 03-Dec-15.
 */
public class Trading {

    //Method for when making a new trade.
    public void MakeNewTrade(int[] resources) {
        PlayerStats.resourcesTrade = resources;
        PlayerStats.resetTradingResources = false;
        PlayerStats.tradeResourcesToHandle = false;
        PlayerStats.sendTrade = true;
    }

    //Method for when the player make the final accept of the trade, and the resources will be added the the two trading players current resources.
    public void AcceptTrade(int[] resources) {
        PlayerStats.resourcesTrade = resources;
        PlayerStats.resetTradingResources = true;
        PlayerStats.sendTrade = true;
        PlayerStats.tradeResourcesToHandle = true;
        PlayerStats.adjustMyResources = true;
        PlayerStats.targetPlayerTrade = (PlayerStats.turn + 1);
        System.out.println("Send trade back to: " + (PlayerStats.turn + 1));
    }

    //If the receiving player at any point declines the trade.
    public void DeclineTrade(boolean refresh) {
        PlayerStats.resetTradingResources = true;
        PlayerStats.sendTrade = true;
        PlayerStats.tradeResourcesToHandle = false;
    }

    //After the receiving play have accepted the trade, this method will run on both clients, and readjust the resources.
    public static void ReAdjustResources() {
        System.out.println("-------Accpeted the trade----------------------");
        System.out.println("-------Old Resources---------------------------");
        System.out.println("Wool: " + State_PlayingWindow.currentResources[0]);
        System.out.println("Ore: " + State_PlayingWindow.currentResources[1]);
        System.out.println("Lumber: " + State_PlayingWindow.currentResources[2]);
        System.out.println("Bricks: " + State_PlayingWindow.currentResources[3]);
        System.out.println("Grain: " + State_PlayingWindow.currentResources[4]);
        System.out.println("-------Inc Resources---------------------------");
        System.out.println("Wool: " + (PlayerStats.resourcesTrade[0] - PlayerStats.resourcesTrade[5]));
        System.out.println("Ore: " + (PlayerStats.resourcesTrade[1] - PlayerStats.resourcesTrade[6]));
        System.out.println("Lumber: " + (PlayerStats.resourcesTrade[2] - PlayerStats.resourcesTrade[7]));
        System.out.println("Bricks: " + (PlayerStats.resourcesTrade[3] - PlayerStats.resourcesTrade[8]));
        System.out.println("Grain: " + (PlayerStats.resourcesTrade[4] - PlayerStats.resourcesTrade[9]));

        for (int i = 0; i < 5; i++) {
            State_PlayingWindow.currentResources[i] = State_PlayingWindow.currentResources[i] + PlayerStats.resourcesTrade[i + 5] - PlayerStats.resourcesTrade[i];
        }
        System.out.println("-------New Resources---------------------------");
        System.out.println("Wool: " + State_PlayingWindow.currentResources[0]);
        System.out.println("Ore: " + State_PlayingWindow.currentResources[1]);
        System.out.println("Lumber: " + State_PlayingWindow.currentResources[2]);
        System.out.println("Bricks: " + State_PlayingWindow.currentResources[3]);
        System.out.println("Grain: " + State_PlayingWindow.currentResources[4]);
        System.out.println("-----------------------------------------------");
        PlayerStats.tradeResourcesToHandle = false;
        PlayerStats.adjustMyResources = false;
        PlayerStats.resourcesTrade = new int[10];
        GUI_Overlay.tradingReources = new int[10];
    }
}
