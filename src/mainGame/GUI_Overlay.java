package mainGame;

import mapClasses.GameMap;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import mapClasses.Tile;

/**
 * Created by Kingo on 25-Nov-15.
 */
public class GUI_Overlay {

    boolean DiceRolled, calculateNewDice;
    int die1;
    int die2;
    int combinedValue;
    float[] procentValue = new float [10];
    int rolled = 0;
    String[] buildingMenuText = new String[] {"Road", "House", "City", "Development Card", "Gives 0 Victory Points", "Gives 1 Victory Points", "Gives 2 Victory Points", "Gives ? Victory Points", "1 Lumber and 1 Brink", "1 Lumber, 1 Wool, 1 Grain, and 1 Brick", "3 Ore and 2 Grin", "1 Wool, 1 Ore, and 1 Grain"};

    int theWidth = Main.ScreenWidth;
    int theHeight = Main.ScreenHeight;
    Button accept, decline, showStats, closeStats;
    Button acceptOffer, declineOffer, counterOffer;
    Button rollDice;
    Button[] makeNewTrade = new Button[4];
    Button[] buttons = new Button[20];
    Button[] build_Buttons = new Button[4];
    boolean trade = false;
    boolean tradeWindow = false;
    boolean offerWindow = false;
    boolean showStatsBool;
    int[] tradingReources = new int[10];
    String tradeWithName = "";
    Button endTurn;
    Font font;
    Font regularFont;
    String[] resourceTypes = new String[] {"Wool", "Ore", "Lumber", "Brick", "Grain"};

    GUI_Overlay() throws SlickException {
        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 10), true);

        try {
            InitailizeTexture();
        } catch (SlickException e) {
            System.out.println("Problems loading pictures for the playing window: " + e);
        }
    }

    public void InitailizeTexture() throws SlickException {
        accept = new Button(theWidth - 155, theHeight - 30, 25, 25, Texture.accept);
        decline = new Button(theWidth - 100, theHeight - 30, 25, 25, Texture.decline);
        acceptOffer = new Button(theWidth / 2 - 190, theHeight / 2 + 30, 100, 25, Texture.acceptBig, 15);
        declineOffer = new Button(theWidth / 2 + 50, theHeight / 2 + 30, 100, 25, Texture.declineBig, 15);
        counterOffer = new Button(theWidth / 2 - 65, theHeight / 2 + 30, 100, 25, Texture.counterBig, 15);
        rollDice = new Button(0, 0, 105, 50, Texture.silverButton, 20);
        closeStats = new Button(0, 0, 25, 25, Texture.decline);
        showStats = new Button(0, 0, 140, 35, Texture.silverButton, 20);
        endTurn = new Button(0,0, 150, 50, Texture.templateButton, 35);
        for (int i = 0; i < 4; i++) {
            makeNewTrade[i] = new Button(0, 0, 80, 20, Texture.makeNewTrade, 20);
            build_Buttons[i] = new Button(0, 0, 50, 20, Texture.silverButton, 20);
        }
        for (int i = 0; i < 5; i++) {
            buttons[i] = new Button(theWidth / 2 - 110, theHeight / 2 - 80 + 20 * i, 20, 20, Texture.decrease);
            buttons[i + 5] = new Button(theWidth / 2 + 130, theHeight / 2 - 80 + 20 * i, 20, 20, Texture.increase);
            buttons[i + 10] = new Button(theWidth / 2 - 80, theHeight / 2 - 80 + 20 * i, 20, 20, Texture.decrease);
            buttons[i + 15] = new Button(theWidth / 2 + 160, theHeight / 2 - 80 + 20 * i, 20, 20, Texture.increase);
        }
    }

    public void TradePopupWindow(int x, int y, boolean boo, String _name, Graphics graphics) throws SlickException {
        if (boo) {
            graphics.setColor(Color.black);
            graphics.fill(new Rectangle(x, y, 210, 50));
            graphics.setColor(Color.white);
            graphics.drawRect(x, y, 210, 50);
            graphics.drawString(_name + " wants to trade", x + 5, y);
            accept.SetPos(x + 60, y + 25);
            decline.SetPos(x + 115, y + 25);
            accept.draw();
            decline.draw();
            if (accept.isWithin()) {
                System.out.println("Accept");
                tradeWindow = true;
                PlayerStats.tradingWithyou[PlayerStats.turn] = false;
            }
            if (decline.isWithin()) {
                System.out.println("Decline");
                PlayerStats.tradingResources = new int[10];
                PlayerStats.tradingWithyou =  new boolean[6];
                PlayerStats.tradingWithyou[PlayerStats.turn] = true;
                PlayerStats.tradingWithyou[4] = true;
                PlayerStats.tradingWithyou[5] = true;
                PlayerStats.tradingWithyou[PlayerStats.turn] = false;
            }
        }
    }

    public void BuildingWindow(Graphics g, int x, int y, int sizeX, int sizeY) {
        g.setColor(Color.white);
        g.drawRect(x, y, sizeX, sizeY);
        g.setColor(Color.black);
        g.fillRect(x, y, sizeX, sizeY);
        g.setColor(Color.white);
        g.drawString("Build Menu", x + sizeX/2- 50, y + 10);
        for (int i = 0; i < 4; i ++) {
            g.drawLine(x, y + 30 + 60 * i, x + sizeX, y + 30 + 60 * i);
            build_Buttons[i].SetPos(x + 135, y + 46 + 60 * i);
            build_Buttons[i].draw();
            build_Buttons[i].AddText("Build", Color.white);
            font.drawString(x + 5, y + 35 + 60 * i, buildingMenuText[i]);
            font.drawString(x + 5, y + 50 + 60 * i, buildingMenuText[i+4]);
            if (i+8 == 9) {
                font.drawString(x + 5, y + 65 + 60 * i, buildingMenuText[i+8].substring(0,27));
                font.drawString(x + 5, y + 75 + 60 * i, buildingMenuText[i+8].substring(27));
            } else {
                font.drawString(x + 5, y + 65 + 60 * i, buildingMenuText[i + 8]);
            }
            if (build_Buttons[i].isWithin()) {
                if (PlayerStats.playerturn[PlayerStats.ID-1]) {
                    System.out.println("Building " + buildingMenuText[i]);
                    GameMap.build_buttons[i] = true;
                } else {
                    System.out.println("Can't build when its not your turn");
                }
            }
        }
    }

    public void PlayerList(int x, int y, Graphics graphics, String[] _names, int[] _points, int turn) {
        graphics.setLineWidth(2);
        graphics.drawString("Players:", x + 10, y + 5);
        graphics.drawString("Score:", x + 140, y + 5);

        graphics.drawLine(x + 5, y + 25, x + 195, y + 25);
        graphics.drawRect(x, y, 200, 140);
        for (int i = 0; i < _names.length; i++) {
            graphics.drawString(i + 1 + ") " + _names[i], x + 10, y + 35 + 25 * i);
            regularFont = graphics.getFont();
            graphics.drawString(_points[i]+"", x+160, y + 35 + 25 * i);
            graphics.setFont(font);
            graphics.drawString("pts", x+172, y + 37 + 25*i);
            graphics.setFont(regularFont);

        }
        graphics.drawRect(x + 5, y + 32 + 25 * turn, 190, 25);
    }

    public void ResourceBar(int x, int y, Graphics graphics, int wool, int ore, int lumber, int bricks, int grain) {
        graphics.setLineWidth(2);
        graphics.drawRect(x, y, 500, 25);
        graphics.drawString("Wool:", x + 5, y + 3);
        graphics.drawString(String.valueOf(wool),x+80, y+3);
        graphics.drawString("Ore:", x + 105, y + 3);
        graphics.drawString(String.valueOf(ore),x+180, y+3);
        graphics.drawString("Lumber:", x + 205, y + 3);
        graphics.drawString(String.valueOf(lumber),x+280, y+3);
        graphics.drawString("Bricks:", x + 305, y + 3);
        graphics.drawString(String.valueOf(bricks),x+380, y+3);
        graphics.drawString("Grain:", x + 405, y + 3);
        graphics.drawString(String.valueOf(grain),x+480, y+3);
        graphics.drawLine(x + 100, y, x + 100, y + 25);
        graphics.drawLine(x + 200, y, x + 200, y + 25);
        graphics.drawLine(x + 300, y, x + 300, y + 25);
        graphics.drawLine(x + 400, y, x + 400, y + 25);
    }

    public void IncomingTradeWindow(int x, int y, boolean boo, Graphics g) {
        if (boo) {
            g.setColor(Color.white);
            g.fill(new Rectangle(x, y, 400, 300));
            g.setColor(Color.black);
            g.drawString("Trade with " + tradeWithName, x + 10, y + 20);
            g.drawString("Wool:  " + PlayerStats.tradingResources[0], x + 10, y + 70);
            g.drawString("Ore:   " + PlayerStats.tradingResources[1], x + 10, y + 90);
            g.drawString("Lumber:" + PlayerStats.tradingResources[2], x + 10, y + 110);
            g.drawString("Bricks:" + PlayerStats.tradingResources[3], x + 10, y + 130);
            g.drawString("Grain: " + PlayerStats.tradingResources[4], x + 10, y + 150);
            g.drawString("for", x + 150, y + 110);
            g.drawString("Wool:  " + PlayerStats.tradingResources[5], x + 250, y + 70);
            g.drawString("Ore:   " + PlayerStats.tradingResources[6], x + 250, y + 90);
            g.drawString("Lumber:" + PlayerStats.tradingResources[7], x + 250, y + 110);
            g.drawString("Bricks:" + PlayerStats.tradingResources[8], x + 250, y + 130);
            g.drawString("Grain: " + PlayerStats.tradingResources[9], x + 250, y + 150);
            acceptOffer.SetPos(x + 10, y + 180);
            declineOffer.SetPos(x + 250, y + 180);
            counterOffer.SetPos(x + 135, y + 180);
            acceptOffer.draw();
            acceptOffer.AddText("Accept Offer", Color.black);
            declineOffer.draw();
            declineOffer.AddText("Decline Offer", Color.black);
            if (acceptOffer.isWithin()) {
                System.out.println("Accept");
                //PlayerStats.tradingResources = tradingReources;
                PlayerStats.tradingWithyou =  new boolean[6];
                PlayerStats.tradingWithyou[PlayerStats.turn] = true;
                PlayerStats.tradingWithyou[5] = true;
                tradeWindow = false;
                PlayerStats.refreshResources = false;
                PlayerStats.tradingWithyou[4] = true;
            }
            if (declineOffer.isWithin()) {
                System.out.println("Decline");
                PlayerStats.tradingResources = new int[10];
                PlayerStats.tradingWithyou =  new boolean[6];
                PlayerStats.tradingWithyou[PlayerStats.turn] = true;
                PlayerStats.tradingWithyou[5] = true;
                tradeWindow = false;
                PlayerStats.refreshResources = false;
                PlayerStats.tradingWithyou[4] = true;
            }
            g.setColor(Color.white);
        }
    }

    public void TradeWithWindow(int x, int y, Graphics g, String[] _names) {
        g.setLineWidth(2);
        g.setColor(Color.white);
        g.fill(new Rectangle(x, y, 200, 150));
        g.setColor(Color.black);
        g.drawString("Trade with: ", x + 10, y + 10);
        g.drawLine(x, y + 30, x + 200, y + 30);
        for (int i = 0; i < _names.length; i++) {
            g.drawString(_names[i], x + 10, y + 40 + 25 * i);
        }
        for (int i = 0; i < _names.length; i++) {
            makeNewTrade[i].SetPos(x + 100, y + 40 + 25 * i);
            makeNewTrade[i].draw();
            makeNewTrade[i].AddText("Trade", Color.black);
            if (makeNewTrade[i].isWithin()) {
                System.out.println("Trading with " + _names[i]);
                tradeWithName = _names[i];
                offerWindow = true;
                State_PlayingWindow.tradeId = i;
            }
        }
        g.setColor(Color.white);
    }

    public void ShowStats(Graphics g, int x, int y) {
        showStats.SetPos(x, y);
        closeStats.SetPos(theWidth / 2 + 170, theHeight / 2 - 195);
        if (showStatsBool) {
            State_PlayingWindow.gameInfo = "Close the window to continue playing";
            if (closeStats.isWithin()) {
                State_PlayingWindow.gameInfo = "";
                showStatsBool = false;
            }
            g.setColor(Color.white);
            g.drawRect(theWidth / 2 - 200, theHeight / 2 - 200, 400, 450);
            g.setColor(Color.black);
            g.fillRect(theWidth / 2 - 200, theHeight / 2 - 200, 400, 450);
            g.setColor(Color.white);

            g.drawString("Dice Roll Percentages:", theWidth/2 - 180, theHeight/2 - 190);
            for (int i = 1; i < 13; i++) {
                float procent = (float) PlayerStats.rolledDiceStatistics[i - 1] / (float) rolled;
                procentValue[i - 1] = procent * 120;
                g.setLineWidth(2);
                if (i < 10) {
                    g.drawString(String.valueOf(i), theWidth / 2 - 205 + i * 30, theHeight / 2 - 25);
                } else {
                    g.drawString(String.valueOf(i), theWidth / 2 - 209 + i * 30, theHeight / 2 - 25);
                }
                g.setLineWidth(10);
                g.drawLine(theWidth / 2 - 200 + i * 30, theHeight / 2 - 40, theWidth / 2 - 200 + i * 30, theHeight / 2 - 40 - procentValue[i - 1]);
            }

            g.setLineWidth(4);
            g.drawLine(theWidth / 2 - 195, theHeight / 2, theWidth / 2 + 195, theHeight / 2);
            g.drawString("Resource Types:", theWidth / 2 - 180, theHeight / 2 + 10);

            Texture.tileSprites.getSprite(4, 0).draw(theWidth / 2 - 180, theHeight / 2 + 40, 0.4f);
            g.drawString("Lumber", theWidth / 2 - 110,theHeight/2+60);
            Texture.tileSprites.getSprite(1, 0).draw(theWidth / 2 - 180,theHeight/2+110,0.4f);
            g.drawString("Bricks", theWidth / 2 - 110,theHeight/2+130);
            Texture.tileSprites.getSprite(0, 0).draw(theWidth / 2 - 180,theHeight/2+180,0.4f);
            g.drawString("Ore", theWidth / 2 - 110,theHeight/2+200);

            Texture.tileSprites.getSprite(3, 0).draw(theWidth / 2+20,theHeight/2+40,0.4f);
            g.drawString("Wool", theWidth / 2+90,theHeight/2+60);
            Texture.tileSprites.getSprite(6, 0).draw(theWidth / 2+20,theHeight/2+110,0.4f);
            g.drawString("Grain", theWidth / 2+90,theHeight/2+130);
            Texture.tileSprites.getSprite(2, 0).draw(theWidth / 2+20,theHeight/2+180,0.4f);
            g.drawString("No yield", theWidth / 2+90,theHeight/2+200);

            g.setLineWidth(2);

        }
        showStats.draw();
        showStats.AddText("Show Game Info", Color.white);
        if (showStatsBool)
            closeStats.draw();
        if (showStats.isWithin()) {
            showStatsBool = true;
        }
    }

    public void DisplayDice(Graphics g, int x, int y, GameMap map, boolean yourTurn) {
        int sizeX = 115;
        int sizeY = 115;
        g.setLineWidth(2);
        g.setColor(Color.white);
        g.drawRect(x, y, sizeX, sizeY);
        rollDice.SetPos(x + 5, y + 59);
        rollDice.draw();
        if (yourTurn) {
            //If it your turn
            if (!DiceRolled) {
                State_PlayingWindow.gameInfo = "Your turn! Press 'Roll Dice' to roll the dice";
                rollDice.AddText("Roll Dice", Color.black);
            } else {
                rollDice.AddText("Rolled: " + combinedValue, Color.black);
            }
            if (PlayerStats.die1 != 0 && PlayerStats.die2 != 0) {
                Texture.diceSprites.getSprite(die1, 0).draw(x + 5, y + 5, 50, 50);
                Texture.diceSprites.getSprite(die2, 0).draw(x + 60, y + 5, 50, 50);
            } else {
                Texture.butt.draw(x + 5, y + 5, 50, 50);
                Texture.butt.draw(x + 60, y + 5, 50, 50);
            }
            if (rollDice.isWithin()) { //Add " && !DiceRolled" after testing!!!!

                System.out.println("Dice rolled");
                PlayerStats.diceRoll = true;
                DiceRolled = true;
            }
            if (!PlayerStats.diceUsed && PlayerStats.die1 > 0 && PlayerStats.die2 > 0) {
                System.out.println("Die 1: " + PlayerStats.die1 + " Die 2: " + PlayerStats.die2);
                die1 = PlayerStats.die1 - 1;
                die2 = PlayerStats.die2 - 1;
                combinedValue = die1 + die2 + 2;
                rolled++;
                State_PlayingWindow.currentResources = Addresources(State_PlayingWindow.currentResources, combinedValue, false, map);
                calculateNewDice = false;
                PlayerStats.diceUsed = true;
            }
        } else {
            //If it isn't your turn
            if (!calculateNewDice) {
                rollDice.AddText("Rolled: " + combinedValue, Color.black);
            } else {
                rollDice.AddText("Waiting for roll", Color.black);
            }
            if (PlayerStats.die1 != 0 && PlayerStats.die2 != 0) {
                Texture.diceSprites.getSprite(die1, 0).draw(x + 5, y + 5, 50, 50);
                Texture.diceSprites.getSprite(die2, 0).draw(x + 60, y + 5, 50, 50);
            } else {
                Texture.butt.draw(x + 5, y + 5, 50, 50);
                Texture.butt.draw(x + 60, y + 5, 50, 50);
            }
            if (!PlayerStats.diceUsed && PlayerStats.die1 > 0 && PlayerStats.die2 > 0) {
                System.out.println("Die 1: " + PlayerStats.die1 + " Die 2: " + PlayerStats.die2);
                die1 = PlayerStats.die1-1;
                die2 = PlayerStats.die2-1;
                combinedValue = die1 + die2 + 2;
                rolled++;
                State_PlayingWindow.currentResources = Addresources(State_PlayingWindow.currentResources, combinedValue, false, map);
                calculateNewDice = false;
                PlayerStats.diceUsed = true;
            }
        }
    }


    public void OfferWindow(int x, int y, boolean boo, Graphics g, int tradeId) {
        if (boo) {
            g.setColor(Color.white);
            g.fill(new Rectangle(x, y, 400, 300));
            g.setColor(Color.black);
            g.drawString("Make an offer for " + tradeWithName, x + 10, y + 20);
            for (int i = 0; i < 5; i++) {
                buttons[i].SetPos(x + 90, y + 70 + 20 * i);
                buttons[i + 5].SetPos(x + 120, y + 70 + 20 * i);
                buttons[i + 10].SetPos(x + 330, y + 70 + 20 * i);
                buttons[i + 15].SetPos(x + 360, y + 70 + 20 * i);
            }
            for (int i = 0; i < 20; i++) {
                buttons[i].draw();
            }
            for (int i = 0; i < 5; i++) {
                if (buttons[i].isWithin() && tradingReources[i] > 0)
                    tradingReources[i]--;
                if (buttons[i+5].isWithin() && tradingReources[i] < State_PlayingWindow.currentResources[i])
                    tradingReources[i]++;
                if (buttons[i + 10].isWithin() && tradingReources[i+5] > 0)
                    tradingReources[i + 5]--;
                if (buttons[i+15].isWithin())
                    tradingReources[i+5]++;
            }
            g.drawString("Wool:  " + tradingReources[0], x + 10, y + 70);
            g.drawString("Ore:   " + tradingReources[1], x + 10, y + 90);
            g.drawString("Lumber:" + tradingReources[2], x + 10, y + 110);
            g.drawString("Bricks:" + tradingReources[3], x + 10, y + 130);
            g.drawString("Grain: " + tradingReources[4], x + 10, y + 150);
            g.drawString("for", x + 170, y + 110);
            g.drawString("Wool:  " + tradingReources[5], x + 250, y + 70);
            g.drawString("Ore:   " + tradingReources[6], x + 250, y + 90);
            g.drawString("Lumber:" + tradingReources[7], x + 250, y + 110);
            g.drawString("Bricks:" + tradingReources[8], x + 250, y + 130);
            g.drawString("Grain: " + tradingReources[9], x + 250, y + 150);
            acceptOffer.SetPos(x + 10, y + 180);
            declineOffer.SetPos(x + 250, y + 180);
            acceptOffer.draw();
            acceptOffer.AddText("Send Offer", Color.black);
            declineOffer.draw();
            declineOffer.AddText("Cancel", Color.black);
            if (acceptOffer.isWithin()) {
                offerWindow = false;
                PlayerStats.tradingResources = tradingReources;
                PlayerStats.tradingWithyou =  new boolean[6];
                PlayerStats.tradingWithyou[tradeId] = true;
                PlayerStats.tradingWithyou[4] = true;
            }
            if (declineOffer.isWithin()) {
                offerWindow = false;
            }
            g.setColor(Color.white);
        }
    }

    public void HandleTradeRespons() {
        if (!PlayerStats.tradingComplete) {
            System.out.println("-------Boolean Values--------------------------");
            System.out.println("Trade accepted: " + PlayerStats.tradingWithyou[5]);
            System.out.println("Trade Complete: " + PlayerStats.tradingComplete);
            System.out.println("Trade Handled: " + PlayerStats.tradeHandled);
            if (PlayerStats.tradingWithyou[5]) {
                System.out.println("-------Accpeted the trade----------------------");
                System.out.println("-------Old Resources---------------------------");
                System.out.println("Wool: " + State_PlayingWindow.currentResources[0]);
                System.out.println("Ore: " + State_PlayingWindow.currentResources[1]);
                System.out.println("Lumber: " + State_PlayingWindow.currentResources[2]);
                System.out.println("Bricks: " + State_PlayingWindow.currentResources[3]);
                System.out.println("Grain: " + State_PlayingWindow.currentResources[4]);
                System.out.println("-------Inc Resources---------------------------");
                System.out.println("Wool: " + (PlayerStats.tradingResources[0] - PlayerStats.tradingResources[5]));
                System.out.println("Ore: " + (PlayerStats.tradingResources[1] - PlayerStats.tradingResources[6]));
                System.out.println("Lumber: " + (PlayerStats.tradingResources[2] - PlayerStats.tradingResources[7]));
                System.out.println("Bricks: " + (PlayerStats.tradingResources[3] - PlayerStats.tradingResources[8]));
                System.out.println("Grain: " + (PlayerStats.tradingResources[4] - PlayerStats.tradingResources[9]));

                for (int i = 0; i < 5; i++) {
                    State_PlayingWindow.currentResources[i] = State_PlayingWindow.currentResources[i] + PlayerStats.tradingResources[i] - PlayerStats.tradingResources[i + 5];
                }
                System.out.println("-------New Resources---------------------------");
                System.out.println("Wool: " + State_PlayingWindow.currentResources[0]);
                System.out.println("Ore: " + State_PlayingWindow.currentResources[1]);
                System.out.println("Lumber: " + State_PlayingWindow.currentResources[2]);
                System.out.println("Bricks: " + State_PlayingWindow.currentResources[3]);
                System.out.println("Grain: " + State_PlayingWindow.currentResources[4]);
                System.out.println("-----------------------------------------------");
                PlayerStats.tradingWithyou[5] = false;
            } else {
                System.out.println("-------Declined the trade----------------------");
            }
            tradingReources = new int[10];
            PlayerStats.tradingComplete = true;
            PlayerStats.refreshResources = false;
        }
    }

    public void EndTurn(int x, int y) {
        endTurn.SetPos(x, y);
        endTurn.draw();
        endTurn.AddText("End Turn", Color.white);
        if (endTurn.isWithin()) {
            System.out.println("Ending turn");
            PlayerStats.endTurn = true;
            DiceRolled = false;
        }
    }

    public int[] Addresources(int[] input, int diceRoll,boolean isCity, GameMap map) {
        ArrayList<Tile> tiles = map.tilesYieldingResource(diceRoll);
        for (int i = 0; i < tiles.size(); i++) {
            String tmp_res = tiles.get(i).getTileType();
            for (int j = 0; j < 5; j++) {
                if (tmp_res.equals(resourceTypes[j])) {
                    if (isCity) {
                        input[j] += 2;
                    } else {
                        input[j]++;
                    }
                }
            }
        }
        return input;
    }
}
