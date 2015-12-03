package mainGame;

/**
 * Created by Kingo on 03-Dec-15.
 */
public class Trading {

    public void MakeNewTrade(int[] resources) {
        PlayerStats.resourcesTrade = resources;
        PlayerStats.resetTradingResources = true;
        PlayerStats.tradeResourcesToHandle = false;
        PlayerStats.sendTrade = true;
    }

    public void AcceptTrade(int[] resources) {
        PlayerStats.resourcesTrade = resources;
        PlayerStats.resetTradingResources = false;
        PlayerStats.sendTrade = true;
        PlayerStats.tradeResourcesToHandle = false;
    }

    public void DeclineTrade(boolean refresh) {
        PlayerStats.resetTradingResources = true;
        PlayerStats.sendTrade = true;
        PlayerStats.tradeResourcesToHandle = false;
    }

    public static int[] ReAdjustResources(int[] tradingReources) {
        if (!PlayerStats.tradeResourcesToHandle) {
            int[] newResources = new int[5];
            if (PlayerStats.targetPlayerTrade == PlayerStats.ID) {
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
                    newResources[i] = State_PlayingWindow.currentResources[i] + PlayerStats.resourcesTrade[i] - PlayerStats.resourcesTrade[i + 5];
                }
                System.out.println("-------New Resources---------------------------");
                System.out.println("Wool: " + State_PlayingWindow.currentResources[0]);
                System.out.println("Ore: " + State_PlayingWindow.currentResources[1]);
                System.out.println("Lumber: " + State_PlayingWindow.currentResources[2]);
                System.out.println("Bricks: " + State_PlayingWindow.currentResources[3]);
                System.out.println("Grain: " + State_PlayingWindow.currentResources[4]);
                System.out.println("-----------------------------------------------");
            } else {
                System.out.println("-------Declined the trade----------------------");
            }
            PlayerStats.tradeResourcesToHandle = false;
            return newResources;
        } else {
            PlayerStats.resourcesTrade =  new int[10];
            GUI_Overlay.tradingReources = new int[10];
            return State_PlayingWindow.currentResources;
        }
    }
}
