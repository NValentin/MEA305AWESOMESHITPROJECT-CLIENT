package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

/**
 * Created by Kingo on 25-Nov-15.
 */
public class GUI_Overlay {
    public static boolean popTrade = true;

    public static boolean DiceRolled;
    String[] buildingMenuText = new String[]{"Road", "House", "City", "Development Card", "Gives 0 Victory Points", "Gives 1 Victory Points", "Gives 2 Victory Points", "Gives ? Victory Points", "1 Lumber and 1 Brink", "1 Lumber, 1 Wool, 1 Grain, and 1 Brick", "3 Ore and 2 Grin", "1 Wool, 1 Ore, and 1 Grain"};

    int theWidth = Main.ScreenWidth;
    int theHeight = Main.ScreenHeight;
    Button accept, decline, showStats, closeStats;
    Button acceptOffer, declineOffer, counterOffer;
    Button rollDice;
    Button[] makeNewTrade = new Button[4];
    Button[] buttons = new Button[20];
    Button[] build_Buttons = new Button[4];
    Button[] yearOfPlenty_Buttons =  new Button[12];
    boolean tradeWindow = false;
    boolean offerWindow = false;
    boolean showStatsBool;
    Dice dice;
    public static int[] tradingReources = new int[10];
    DevelopmentCard card;
    Trading trade;
    public static int yearOfPlentyTaken = 0;
    int[] tmp_res = new int[5];
    public static boolean isYearOfPlenty = false;

    String tradeWithName = "";
    Button endTurn;
    Font font;
    Font infoFont;
    Font regularFont;
    String[] resourceTypes = new String[]{"Wool   ", "Ore    ", "Lumber ", "Brick  ", "Grain  "};

    GUI_Overlay() throws SlickException {
        card = new DevelopmentCard();
        dice = new Dice();
        trade = new Trading();
        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 10), true);
        infoFont = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.BOLD, 12), true);

        try {
            InitailizeTexture();
        } catch (SlickException e) {
            System.out.println("Problems loading pictures for the playing window: " + e);
        }
    }

    public void DisplayCards(int x, int y, GameContainer gc) {
        card.DisplayCards(x, y);
        card.Hover(gc);
        card.isPressed(gc);
    }

    //Initailize the textures for all buttons on the GUI for the playing state, from the Texture class.
    public void InitailizeTexture() throws SlickException {
        accept = new Button(theWidth - 155, theHeight - 30, 25, 25, "accept");
        decline = new Button(theWidth - 100, theHeight - 30, 25, 25, "decline");
        acceptOffer = new Button(theWidth / 2 - 190, theHeight / 2 + 30, 100, 25, "acceptBig", 15);
        declineOffer = new Button(theWidth / 2 + 50, theHeight / 2 + 30, 100, 25, "declineBig", 15);
        counterOffer = new Button(theWidth / 2 - 65, theHeight / 2 + 30, 100, 25, "counterBig", 15);
        rollDice = new Button(0, 0, 105, 50, "silverButton", 20);
        closeStats = new Button(0, 0, 25, 25, "decline");
        showStats = new Button(0, 0, 140, 35, "silverButton", 20);
        endTurn = new Button(0, 0, 150, 50, "templateButton", 35);
        for (int i = 0; i < 4; i++) {
            makeNewTrade[i] = new Button(0, 0, 80, 20, "makeNewTrade", 20);
            build_Buttons[i] = new Button(0, 0, 50, 20, "silverButton", 20);
        }
        for (int i = 0; i < 5; i++) {
            buttons[i] = new Button(theWidth / 2 - 110, theHeight / 2 - 80 + 20 * i, 20, 20, "decrease");
            buttons[i + 5] = new Button(theWidth / 2 + 130, theHeight / 2 - 80 + 20 * i, 20, 20, "increase");
            buttons[i + 10] = new Button(theWidth / 2 - 80, theHeight / 2 - 80 + 20 * i, 20, 20, "decrease");
            buttons[i + 15] = new Button(theWidth / 2 + 160, theHeight / 2 - 80 + 20 * i, 20, 20, "increase");
        }
        yearOfPlenty_Buttons[10] = new Button(Main.ScreenWidth/2 + 75, Main.ScreenHeight/2 + 40, 60, 20, "acceptBig", 15);
        yearOfPlenty_Buttons[11] = new Button(Main.ScreenWidth/2 + 5, Main.ScreenHeight/2 + 40, 60, 20, "declineBig", 15);
        for (int i = 0; i < 5; i++) {
            yearOfPlenty_Buttons[i] = new Button(Main.ScreenWidth/2 - 50, Main.ScreenHeight/2 - 45 + i * 20, 20 ,20, "decrease");
            yearOfPlenty_Buttons[i+5] = new Button(Main.ScreenWidth/2 - 25, Main.ScreenHeight/2 - 45 + i * 20, 20 ,20, "increase");
        }
    }

    //The trade popup window, that indicates that someone wants to trade with you.
    public void TradePopupWindow(int x, int y, boolean boo, String _name, Graphics graphics, GameContainer gc) throws SlickException {
        //The the popup should be shown
        if (boo) {
            //Initail text
            State_PlayingWindow.gameInfo = _name + " wants to trade!";
            graphics.setColor(Color.black);
            graphics.fill(new Rectangle(x, y, 210, 50));
            graphics.setColor(Color.white);
            graphics.drawRect(x, y, 210, 50);
            graphics.drawString(_name + " wants to trade", x + 5, y);
            accept.SetPos(x + 60, y + 25);
            decline.SetPos(x + 115, y + 25);
            accept.draw(graphics);
            decline.draw(graphics);
            //If the player accepts the trade.
            if (accept.isPressed(gc)) {
                System.out.println("Accept");
                tradeWindow = true;
                popTrade = false;
            }
            //If the player declines that trade
            if (decline.isPressed(gc)) {
                System.out.println("Decline");
                State_PlayingWindow.gameInfo = "Trade declined";
                popTrade = false;
            }
        }
    }

    //The window that shows the building cost for all building options (Roads, house, cities, and developments cards), with buttons that are used to purchased them.
    public void BuildingWindow(Graphics g, int x, int y, int sizeX, int sizeY, GameContainer gc) {
        g.setColor(Color.white);
        g.drawRect(x, y, sizeX, sizeY);
        g.setColor(Color.black);
        g.fillRect(x, y, sizeX, sizeY);
        g.setColor(Color.white);
        g.drawString("Build Menu", x + sizeX / 2 - 50, y + 10);
        for (int i = 0; i < 4; i++) {
            g.drawLine(x, y + 30 + 60 * i, x + sizeX, y + 30 + 60 * i);
            build_Buttons[i].SetPos(x + 135, y + 46 + 60 * i);
            build_Buttons[i].draw(g);
            build_Buttons[i].AddText("Build", Color.white);
            font.drawString(x + 5, y + 35 + 60 * i, buildingMenuText[i]);
            font.drawString(x + 5, y + 50 + 60 * i, buildingMenuText[i + 4]);
            if (i + 8 == 9) {
                font.drawString(x + 5, y + 65 + 60 * i, buildingMenuText[i + 8].substring(0, 27));
                font.drawString(x + 5, y + 75 + 60 * i, buildingMenuText[i + 8].substring(27));
            } else {
                font.drawString(x + 5, y + 65 + 60 * i, buildingMenuText[i + 8]);
            }
            if (build_Buttons[i].isPressed(gc)) {
                if (PlayerStats.playerturn[PlayerStats.ID - 1]) {
                    System.out.println("Building " + buildingMenuText[i]);
                    BuildMenuResourceCheck(i);
                } else {
                    System.out.println("Can't build when its not your turn");
                }
            }
        }
    }

    //The playerlist, getting names from the server.
    public void PlayerList(int x, int y, Graphics graphics, String[] _names, int[] _points, int turn, GameContainer gc) {
        graphics.setLineWidth(2);
        graphics.drawString("Players:", x + 10, y + 5);
        graphics.drawString("Score:", x + 140, y + 5);

        graphics.drawLine(x + 5, y + 25, x + 195, y + 25);
        //Drawing a rectangle around the player which turn it is.
        graphics.drawRect(x, y, 200, 140);
        for (int i = 0; i < _names.length; i++) {
            graphics.drawString(i + 1 + ") " + _names[i], x + 10, y + 35 + 25 * i);
            regularFont = graphics.getFont();
            graphics.drawString(_points[i] + "", x + 160, y + 35 + 25 * i);
            graphics.setFont(font);
            graphics.drawString("pts", x + 172, y + 37 + 25 * i);
            graphics.setFont(regularFont);

        }
        graphics.drawRect(x + 5, y + 32 + 25 * turn, 190, 25);
    }

    //Displaying your current resources.
    public void ResourceBar(int x, int y, Graphics graphics, int wool, int ore, int lumber, int bricks, int grain, GameContainer gc) {
        graphics.setLineWidth(2);
        graphics.drawRect(x, y, 500, 25);
        graphics.drawString("Wool:", x + 5, y + 3);
        graphics.drawString(String.valueOf(wool), x + 80, y + 3);
        graphics.drawString("Ore:", x + 105, y + 3);
        graphics.drawString(String.valueOf(ore), x + 180, y + 3);
        graphics.drawString("Lumber:", x + 205, y + 3);
        graphics.drawString(String.valueOf(lumber), x + 280, y + 3);
        graphics.drawString("Bricks:", x + 305, y + 3);
        graphics.drawString(String.valueOf(bricks), x + 380, y + 3);
        graphics.drawString("Grain:", x + 405, y + 3);
        graphics.drawString(String.valueOf(grain), x + 480, y + 3);
        graphics.drawLine(x + 100, y, x + 100, y + 25);
        graphics.drawLine(x + 200, y, x + 200, y + 25);
        graphics.drawLine(x + 300, y, x + 300, y + 25);
        graphics.drawLine(x + 400, y, x + 400, y + 25);
    }

    //The trade window that opens after you press accept on the trade popup.
    public void IncomingTradeWindow(int x, int y, boolean boo, Graphics g, GameContainer gc) {
        if (boo) {
            State_PlayingWindow.gameInfo = "Trading with "+tradeWithName;
            g.setColor(Color.white);
            g.fill(new Rectangle(x, y, 400, 300));
            g.setColor(Color.black);
            g.drawString("Trade with " + tradeWithName, x + 10, y + 20);
            g.drawString("Give", x + 30, y + 50);
            g.drawString("Get", x + 270, y + 50);
            g.drawString("for", x + 185, y + 110);
            for (int i = 0; i < 5; i++) {
                g.drawString(resourceTypes[i] + ": " + PlayerStats.resourcesTrade[i], x + 15, y + 70 + 20 * i);
                g.drawString(resourceTypes[i] + ": " + PlayerStats.resourcesTrade[i + 5], x + 255, y + 70 + 20 * i);
            }
            acceptOffer.SetPos(x + 10, y + 180);
            declineOffer.SetPos(x + 250, y + 180);
            counterOffer.SetPos(x + 135, y + 180);
            acceptOffer.draw(g);
            acceptOffer.AddText("Accept Offer", Color.black);
            declineOffer.draw(g);
            declineOffer.AddText("Decline Offer", Color.black);
            if (acceptOffer.isPressed(gc)) {
                //If you accept the trade proposal.
                System.out.println("Accept");
                State_PlayingWindow.gameInfo = "Trade accepted";
                tradeWindow = false;
                trade.AcceptTrade(PlayerStats.resourcesTrade);
            }
            //If you decline
            if (declineOffer.isPressed(gc)) {
                System.out.println("Decline");
                State_PlayingWindow.gameInfo = "Trade declined";
                tradeWindow = false;
                trade.DeclineTrade(true);
            }
            g.setColor(Color.white);
        }
    }

    //This window will be shown if it is your turn. Here you can trade with other players.
    public void TradeWithWindow(int x, int y, Graphics g, String[] _names, GameContainer gc) {
        g.setLineWidth(2);
        g.setColor(Color.white);
        g.fill(new Rectangle(x, y, 200, 150));
        g.setColor(Color.black);
        g.drawString("Trade with: ", x + 10, y + 10);
        g.drawLine(x, y + 30, x + 200, y + 30);
        for (int i = 0; i < _names.length; i++) {
            g.drawString(_names[i], x + 10, y + 40 + 25 * i);
        }
        g.setColor(Color.white);
        for (int i = 0; i < _names.length; i++) {
            makeNewTrade[i].SetPos(x + 100, y + 40 + 25 * i);
            makeNewTrade[i].draw(g);
            makeNewTrade[i].AddText("Trade", Color.black);
            if (makeNewTrade[i].isPressed(gc)) {
                System.out.println("Trading with " + _names[i]);
                PlayerStats.targetPlayerTrade = (i+1);
                tradeWithName = _names[i];
                offerWindow = true;
                State_PlayingWindow.tradeId = i;
            }
        }
    }


    //Show dice statistics, tile information ect.
    public void ShowStats(Graphics g, int x, int y, GameContainer gc) {
        showStats.SetPos(x, y);
        closeStats.SetPos(theWidth / 2 + 320, theHeight / 2 - 195);
        //If the "show stats" buttons is pressed, it will show the stats window.
        if (showStatsBool) {
            State_PlayingWindow.gameInfo = "Close the window by clicking anywhere";
            if (closeStats.isPressed(gc)) {
                State_PlayingWindow.gameInfo = "";
                showStatsBool = false;
            }
            g.setColor(Color.white);
            g.drawRect(theWidth / 2 - 350, theHeight / 2 - 200, 700, 450);
            g.setColor(Color.black);
            g.fillRect(theWidth / 2 - 350, theHeight / 2 - 200, 700, 450);
            g.setColor(Color.white);

            g.drawString("Players:", theWidth / 2 - 340, theHeight / 2 - 190);
            for(int i = 0; i<PlayerStats.names.length; i++) {
                //draw names
                infoFont.drawString(theWidth / 2 - 340, theHeight / 2 - 160 + 95*i, PlayerStats.names[i], PlayerStats.playerColors[i+1]);
                //draw resources
                infoFont.drawString(theWidth / 2 - 340, theHeight / 2 - 143 + 95*i, "Resources: "+PlayerStats.resourcesOnHand[i], Color.white);
                //draw cards
                infoFont.drawString(theWidth / 2 - 340, theHeight / 2 - 126 + 95*i, "Cards on Hand: "+PlayerStats.cardsOnHand[i], Color.white);
                //draw knights played
                infoFont.drawString(theWidth / 2 - 340, theHeight / 2 - 109 + 95*i, "Knights Played: "+PlayerStats.knightsPlayed[i], Color.white);
                //draw points
                infoFont.drawString(theWidth / 2 - 340, theHeight / 2 - 92 + 95*i, "Points: "+PlayerStats.points[i]+"", Color.white);
            }

            g.drawString("Dice Roll Percentages:", theWidth / 2 - 30, theHeight / 2 - 190);
            for (int i = 1; i < 13; i++) {
                g.setLineWidth(2);
                if (i < 10) {
                    g.drawString(String.valueOf(i), theWidth / 2 - 55 + i * 30, theHeight / 2 - 25);
                } else {
                    g.drawString(String.valueOf(i), theWidth / 2 - 59 + i * 30, theHeight / 2 - 25);
                }
                g.setLineWidth(10);
                g.drawLine(theWidth / 2 - 50 + i * 30, theHeight / 2 - 40, theWidth / 2 - 50 + i * 30, theHeight / 2 - 40 - dice.getDicePercentages(i-1));
            }

            g.setLineWidth(4);
            g.drawLine(theWidth / 2 - 40, theHeight / 2, theWidth / 2 + 345, theHeight / 2);
            g.drawLine(theWidth / 2 - 40, theHeight / 2 - 195, theWidth / 2 - 40, theHeight / 2 + 245);
            g.drawString("Resource Types:", theWidth / 2 - 30, theHeight / 2 + 10);

            Texture.tileSprites.getSprite(4, 0).draw(theWidth / 2 - 30, theHeight / 2 + 40, 0.4f);
            g.drawString("Lumber", theWidth / 2 + 40, theHeight / 2 + 60);
            Texture.tileSprites.getSprite(1, 0).draw(theWidth / 2 - 30, theHeight / 2 + 110, 0.4f);
            g.drawString("Bricks", theWidth / 2 + 40, theHeight / 2 + 130);
            Texture.tileSprites.getSprite(0, 0).draw(theWidth / 2 - 30, theHeight / 2 + 180, 0.4f);
            g.drawString("Ore", theWidth / 2 + 40, theHeight / 2 + 200);

            Texture.tileSprites.getSprite(3, 0).draw(theWidth / 2 + 170, theHeight / 2 + 40, 0.4f);
            g.drawString("Wool", theWidth / 2 + 240, theHeight / 2 + 60);
            Texture.tileSprites.getSprite(6, 0).draw(theWidth / 2 + 170, theHeight / 2 + 110, 0.4f);
            g.drawString("Grain", theWidth / 2 + 240, theHeight / 2 + 130);
            Texture.tileSprites.getSprite(2, 0).draw(theWidth / 2 + 170, theHeight / 2 + 180, 0.4f);
            g.drawString("No yield", theWidth / 2 + 240, theHeight / 2 + 200);

            g.setLineWidth(2);

        }
        showStats.draw(g);
        showStats.AddText("Show Game Info", Color.white);
        if (showStatsBool)
            closeStats.draw(g);
        if (showStats.isPressed(gc)) {
            showStatsBool = true;
        }
        if (showStatsBool && gc.getInput().isMousePressed(0)){
            showStatsBool = false;
            State_PlayingWindow.gameInfo = "";
        }
    }

    //display the current rolled dice. It gets the dice information from the server.
    public void DisplayDice(Graphics g, int x, int y, GameMap map, boolean yourTurn, GameContainer gc) {
        Image[] diceImages;
        int sizeX = 115;
        int sizeY = 115;
        g.setLineWidth(2);
        g.setColor(Color.white);
        g.drawRect(x, y, sizeX, sizeY);
        rollDice.SetPos(x + 5, y + 59);
        rollDice.draw(g);
        rollDice.AddText(dice.getButtonText(yourTurn, DiceRolled), Color.black);
        diceImages = dice.getDice(yourTurn);
        diceImages[0].draw(x + 5, y + 5, 50, 50);
        diceImages[1].draw(x + 60, y + 5, 50, 50);
        //If you havn't rolled your dice this turn, and if it is a normal game round, you can  roll the dice
        if (rollDice.isPressed(gc) && !DiceRolled && State_PlayingWindow.isNormalGameRound) {
            System.out.println("Dice rolled");
            PlayerStats.diceRoll = true;
            DiceRolled = true;
        }
        if (!PlayerStats.diceUsed && PlayerStats.die1 > 0 && PlayerStats.die2 > 0)
            dice.DiceRolled(map);
    }

    //If you play the "Year of Plenty" development card, this window will appear. Here you can get 2 of any resources, that will be added to your current resources.
    public void YearOfPlenty(boolean display, Graphics g, GameContainer gc) {
        if (display) {
            g.setColor(Color.white);
            g.fill(new Rectangle(Main.ScreenWidth / 2 - 150, Main.ScreenHeight / 2 - 75, 300, 150));
            g.setColor(Color.black);
            g.drawString("Choose any two resources: " + (2 - yearOfPlentyTaken) + " left", Main.ScreenWidth / 2 - 140, Main.ScreenHeight / 2 - 70);
            for (int i = 0; i < 5; i++) {
                g.setColor(Color.black);
                g.drawString(resourceTypes[i] + ": " + tmp_res[i], Main.ScreenWidth / 2 - 145, Main.ScreenHeight / 2 - 45 + i * 20);
                g.setColor(Color.white);
                yearOfPlenty_Buttons[i].draw(g);
                yearOfPlenty_Buttons[i + 5].draw(g);
                if (yearOfPlenty_Buttons[i].isPressed(gc) && tmp_res[i] > 0) {
                    yearOfPlentyTaken--;
                    tmp_res[i]--;
                }
                if (yearOfPlenty_Buttons[i + 5].isPressed(gc) && yearOfPlentyTaken < 2) {
                    yearOfPlentyTaken++;
                    tmp_res[i]++;
                }
            }
            g.setColor(Color.white);
            yearOfPlenty_Buttons[10].draw(g);
            yearOfPlenty_Buttons[11].draw(g);
            yearOfPlenty_Buttons[10].AddText("Accept", Color.black);
            yearOfPlenty_Buttons[11].AddText("Decline", Color.black);
            if (yearOfPlenty_Buttons[10].isPressed(gc)) {
                isYearOfPlenty = false;
                for (int i = 0; i < 5; i++) {
                    State_PlayingWindow.currentResources[i] += tmp_res[i];
                }
                //Accept selected resources
                tmp_res = new int[5];
                yearOfPlentyTaken = 0;
            }
            //Decline selected resources
            if (yearOfPlenty_Buttons[11].isPressed(gc)) {
                isYearOfPlenty = false;
                yearOfPlentyTaken = 0;
                tmp_res = new int[5];
            }
        }
    }

    //Window that can be used to make a trade offer for other players. Will appear after you click "trade" on the makeNewTrade window
    public void OfferWindow(int x, int y, boolean boo, Graphics g, GameContainer gc) {
        if (boo) {
            State_PlayingWindow.gameInfo = "Choose resources to trade with "+tradeWithName;
            g.setColor(Color.white);
            g.fill(new Rectangle(x, y, 400, 300));
            g.setColor(Color.black);
            g.drawString("Make an offer for " + tradeWithName, x + 10, y + 20);
            g.drawString("Give", x + 45, y + 50);
            g.drawString("Get", x + 290, y + 50);
            for (int i = 0; i < 5; i++) {
                buttons[i].SetPos(x + 110, y + 70 + 20 * i);
                buttons[i + 5].SetPos(x + 130, y + 70 + 20 * i);
                buttons[i + 10].SetPos(x + 350, y + 70 + 20 * i);
                buttons[i + 15].SetPos(x + 370, y + 70 + 20 * i);
            }
            g.setColor(Color.white);
            for (int i = 0; i < 20; i++) {
                buttons[i].draw(g);
            }
            for (int i = 0; i < 5; i++) {
                if (buttons[i].isPressed(gc) && tradingReources[i] > 0)
                    tradingReources[i]--;
                if (buttons[i+5].isPressed(gc) && tradingReources[i] < State_PlayingWindow.currentResources[i])
                    tradingReources[i]++;
                if (buttons[i + 10].isPressed(gc) && tradingReources[i+5] > 0)
                    tradingReources[i + 5]--;
                if (buttons[i+15].isPressed(gc))
                    tradingReources[i+5]++;
            }
            g.drawString("for", x + 185, y + 110);
            acceptOffer.SetPos(x + 10, y + 180);
            declineOffer.SetPos(x + 250, y + 180);
            acceptOffer.draw(g);
            acceptOffer.AddText("Send Offer", Color.black);
            declineOffer.draw(g);
            declineOffer.AddText("Cancel", Color.black);
            g.setColor(Color.black);
            for (int i = 0; i < 5; i ++) {
                g.drawString(resourceTypes[i] + ": " + tradingReources[i], x + 15, y + 70 + i * 20);
                g.drawString(resourceTypes[i] + ": " + tradingReources[i + 5], x + 255, y + 70 + i * 20);
            }
            if (acceptOffer.isPressed(gc)) {
                offerWindow = false;
                trade.MakeNewTrade(tradingReources);
            }
            if (declineOffer.isPressed(gc)) {
                offerWindow = false;
            }
            g.setColor(Color.white);
        }
    }

    //Check for calculating if you have enough resources for puchasing the selected thing (Road, house, city, or development card), and removes the resources from you.
    public void BuildMenuResourceCheck(int index) {
        switch (index) {
            //Road
            case 0:
                if (State_PlayingWindow.currentResources[2] > 0 && State_PlayingWindow.currentResources[3] > 0) {
                    State_PlayingWindow.currentResources[2]--;
                    State_PlayingWindow.currentResources[3]--;
                    GameMap.build_buttons[0] = true;
                } else {
                    System.out.println("Not enough resources to build a road");
                }
                break;
            //House
            case 1:
                if (State_PlayingWindow.currentResources[0] > 0 && State_PlayingWindow.currentResources[2] > 0 && State_PlayingWindow.currentResources[3] > 0 && State_PlayingWindow.currentResources[4] > 0) {
                    State_PlayingWindow.currentResources[0]--;
                    State_PlayingWindow.currentResources[2]--;
                    State_PlayingWindow.currentResources[3]--;
                    State_PlayingWindow.currentResources[4]--;
                    GameMap.build_buttons[1] = true;
                } else {
                    System.out.println("Not enough resources to build a house");
                }
                break;
            //City
            case 2:
                if (State_PlayingWindow.currentResources[1] > 2 && State_PlayingWindow.currentResources[4] > 1) {
                    State_PlayingWindow.currentResources[1] -= 3;
                    State_PlayingWindow.currentResources[4] -= 2;
                    GameMap.build_buttons[2] = true;
                } else {
                    System.out.println("Not enough resources to build a city");
                }
                break;
            //Development Card
            case 3:
                if (State_PlayingWindow.currentResources[0] > 0 && State_PlayingWindow.currentResources[1] > 0 && State_PlayingWindow.currentResources[4] > 0) {
                    State_PlayingWindow.currentResources[0]--;
                    State_PlayingWindow.currentResources[1]--;
                    State_PlayingWindow.currentResources[4]--;
                    card.DrawNewCard();
                } else {
                    System.out.println("Not enough resources to buy a development card");
                }
                break;
            //in case of error
            default:
                System.out.println("Error with building cost");
        }
    }

    //Ending your turn, and handing to the next player.
    public void EndTurn(int x, int y, GameContainer gc, Graphics g) {
        endTurn.SetPos(x, y);
        endTurn.draw(g);
        endTurn.AddText("End Turn", Color.white);
        if (endTurn.isPressed(gc)) {
            System.out.println("Ending turn");
            PlayerStats.endTurn = true;
            DiceRolled = false;
        }
    }
}
