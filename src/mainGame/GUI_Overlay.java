package mainGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.Random;

/**
 * Created by Kingo on 25-Nov-15.
 */
public class GUI_Overlay {

    Random random = new Random();
    boolean DiceRolled;
    int die1;
    int die2;
    int combinedValue;
    int[] rolledDice = new int[12];
    float[] procentValue = new float []{0,0,0,0,0,0,0,0,0,0,0,0};
    int rolled = 0;

    Texture texture;
    int theWidth = Main.ScreenWidth;
    int theHeight = Main.ScreenHeight;
    Button accept, decline, showStats, closeStats;
    Button acceptOffer, declineOffer, counterOffer;
    Button rollDice;
    Button[] makeNewTrade = new Button[4];
    Button[] buttons = new Button[20];
    boolean trade = false;
    boolean tradeWindow = false;
    boolean offerWindow = false;
    boolean showStatsBool;
    int[] currentResources = new int[5];
    int[] resources = new int[10];
    String tradeWithName = "";
    Button endTurn;

    GUI_Overlay() throws SlickException {
        texture = new Texture();
        try {
            InitailizeTexture();
        } catch (SlickException e) {
            System.out.println("Problems loading pictures for the playing window: " + e);
        }
    }

    public void InitailizeTexture() throws SlickException {
        accept = new Button(theWidth - 155, theHeight - 30, 25, 25, texture.accept);
        decline = new Button(theWidth - 100, theHeight - 30, 25, 25, texture.decline);
        acceptOffer = new Button(theWidth / 2 - 190, theHeight / 2 + 30, 100, 25, texture.acceptBig);
        declineOffer = new Button(theWidth / 2 + 50, theHeight / 2 + 30, 100, 25, texture.declineBig);
        counterOffer = new Button(theWidth / 2 - 65, theHeight / 2 + 30, 100, 25, texture.counterBig);
        rollDice = new Button(0, 0, 105, 50, texture.silverButton, 20);
        closeStats = new Button(0, 0, 25, 25, texture.decline);
        showStats = new Button(0, 0, 140, 35, texture.silverButton, 20);
        endTurn = new Button(0,0, 150, 50, texture.templateButton, 35);
        for (int i = 0; i < makeNewTrade.length; i++) {
            makeNewTrade[i] = new Button(0, 0, 80, 20, texture.makeNewTrade, 20);
        }
        for (int i = 0; i < 5; i++) {
            buttons[i] = new Button(theWidth / 2 - 110, theHeight / 2 - 80 + 20 * i, 20, 20, texture.decrease);
            buttons[i + 5] = new Button(theWidth / 2 + 130, theHeight / 2 - 80 + 20 * i, 20, 20, texture.increase);
            buttons[i + 10] = new Button(theWidth / 2 - 80, theHeight / 2 - 80 + 20 * i, 20, 20, texture.decrease);
            buttons[i + 15] = new Button(theWidth / 2 + 160, theHeight / 2 - 80 + 20 * i, 20, 20, texture.increase);
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
                trade = false;
            }
            if (decline.isWithin()) {
                System.out.println("Decline");
                trade = false;
            }
        }
    }

    public void BuildingWindow(int x, int y, int sizeX, int sizeY) {
        texture.buildingCost.draw(x, y, sizeX, sizeY);
    }

    public void PlayerList(int x, int y, Graphics graphics, String[] _names, int turn) {
        graphics.setLineWidth(2);
        graphics.drawString("Players:", x + 10, x + 5);
        graphics.drawLine(x + 5, y + 25, x + 145, y + 25);
        graphics.drawRect(x, y, 150, 200);
        for (int i = 0; i < _names.length; i++) {
            graphics.drawString(i + 1 + ") " + _names[i], x + 10, y + 35 + 25 * i);
        }
        graphics.drawRect(x + 5, y + 32 + 25 * turn, 140, 25);
    }

    public void ResourceBar(int x, int y, Graphics graphics, int wool, int stone, int wood, int clay, int wheat) {
        graphics.setLineWidth(2);
        graphics.drawRect(x, y, 500, 25);
        graphics.drawString("Wood: " + wood, x + 5, y + 3);
        graphics.drawString("Stone: " + stone, x + 105, y + 3);
        graphics.drawString("Wool: " + wool, x + 205, y + 3);
        graphics.drawString("Clay: " + clay, x + 305, y + 3);
        graphics.drawString("Wheat: " + wheat, x + 405, y + 3);
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
            //g.drawString(_name + "'s offer", x + 10, y +50);
            g.drawString("Wool:  " + resources[0], x + 10, y + 70);
            g.drawString("Stone: " + resources[1], x + 10, y + 90);
            g.drawString("Wood:  " + resources[2], x + 10, y + 110);
            g.drawString("Clay:  " + resources[3], x + 10, y + 130);
            g.drawString("Wheat: " + resources[4], x + 10, y + 150);
            g.drawString("for", x + 150, y + 110);
            g.drawString("Wool:  " + resources[5], x + 250, y + 70);
            g.drawString("Stone: " + resources[6], x + 250, y + 90);
            g.drawString("Clay:  " + resources[8], x + 250, y + 110);
            g.drawString("Wheat: " + resources[9], x + 250, y + 130);
            g.drawString("Wood:  " + resources[7], x + 250, y + 150);
            acceptOffer.SetPos(x + 10, y + 180);
            declineOffer.SetPos(x + 250, y + 180);
            counterOffer.SetPos(x + 135, y + 180);
            acceptOffer.draw();
            declineOffer.draw();
            counterOffer.draw();
            if (acceptOffer.isWithin()) {
                tradeWindow = false;
                trade = true;
            }
            if (declineOffer.isWithin()) {
                tradeWindow = false;
                trade = true;
            }
            if (counterOffer.isWithin()) {
                tradeWindow = false;
                offerWindow = true;
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
            }
        }
        g.setColor(Color.white);
    }

    public void ShowStats(Graphics g, int x, int y) {
        showStats.SetPos(x, y);
        closeStats.SetPos(theWidth / 2 + 270, theHeight / 2 - 195);
        if (showStatsBool) {
            if (closeStats.isWithin()) {
                showStatsBool = false;
            }
            g.setColor(Color.white);
            g.drawRect(theWidth / 2 - 100, theHeight / 2 - 200, 400, 200);
            g.setColor(Color.black);
            g.fillRect(theWidth / 2 - 100, theHeight / 2 - 200, 400, 200);
            g.setColor(Color.white);
            g.drawString("Statistics in procent", theWidth/2 - 25, theHeight/2 - 190);
            for (int i = 1; i < 13; i++) {
                float procent = (float) rolledDice[i - 1] / (float) rolled;
                procentValue[i - 1] = procent * 120;
                g.setLineWidth(2);
                if (i < 10) {
                    g.drawString(String.valueOf(i), theWidth / 2 - 105 + i * 30, theHeight / 2 - 25);
                } else {
                    g.drawString(String.valueOf(i), theWidth / 2 - 109 + i * 30, theHeight / 2 - 25);
                }
                g.setLineWidth(10);
                g.drawLine(theWidth / 2 - 100 + i * 30, theHeight / 2 - 40, theWidth / 2 - 100 + i * 30, theHeight / 2 - 40 - procentValue[i - 1]);
            }
        }
        showStats.draw();
        showStats.AddText("Show Statistics", Color.white);
        if (showStatsBool)
            closeStats.draw();
        if (showStats.isWithin()) {
            showStatsBool = true;
        }
    }

    public void DisplayDice(Graphics g, int x, int y) {
        int sizeX = 115;
        int sizeY = 115;
        DiceRolled = PlayerStats.diceRoll;
        g.setLineWidth(2);
        g.setColor(Color.white);
        g.drawRect(x, y, sizeX, sizeY);
        rollDice.SetPos(x + 5, y + 59);
        rollDice.draw();
        if (!DiceRolled) {
            rollDice.AddText("Roll Dice", Color.black);
            texture.diceSprites.getSprite(0, 0).draw(x + 5, y + 5, 50, 50);
            texture.diceSprites.getSprite(0, 0).draw(x + 60, y + 5, 50, 50);
        }
        if (rollDice.isWithin() && !DiceRolled) {
            DiceRolled = true;
            die1 = PlayerStats.die1;
            die2 = PlayerStats.die2;
            combinedValue = die1 + die2 + 2;
            rolledDice[combinedValue-1]++;
            rolled++;
        }
        if (DiceRolled) {
            rollDice.AddText("Rolled: " + combinedValue, Color.black);
            texture.diceSprites.getSprite(die1, 0).draw(x + 5, y + 5, 50, 50);
            texture.diceSprites.getSprite(die2, 0).draw(x + 60, y + 5, 50, 50);
        }
    }


    public void OfferWindow(int x, int y, boolean boo, Graphics g) {
        if (boo) {
            g.setColor(Color.white);
            g.fill(new Rectangle(x, y, 400, 300));
            g.setColor(Color.black);
            g.drawString("Make an offer for " + tradeWithName, x + 10, y + 20);
            //g.drawString(tradeWithName + "'s offer", x + 10, y +50);
            for (int i = 0; i < 5; i++) {
                buttons[i].SetPos(x + 90, y + 70 + 20 * i);
                buttons[i + 5].SetPos(x + 330, y + 70 + 20 * i);
                buttons[i + 10].SetPos(x + 120, y + 70 + 20 * i);
                buttons[i + 15].SetPos(x + 360, y + 70 + 20 * i);
            }
            for (int i = 0; i < 20; i++) {
                buttons[i].draw();
            }
            for (int i = 0; i < 10; i++) {
                if (buttons[i].isWithin())
                    resources[i]++;
                if (buttons[i + 10].isWithin() && resources[i] > 0)
                    resources[i]--;
            }
            for (int i = 0; i < 5; i++) {
                resources[i] = currentResources[i];
            }
            g.drawString("Wool:  " + resources[0], x + 10, y + 70);
            g.drawString("Stone: " + resources[1], x + 10, y + 90);
            g.drawString("Wood:  " + resources[2], x + 10, y + 110);
            g.drawString("Clay:  " + resources[3], x + 10, y + 130);
            g.drawString("Wheat: " + resources[4], x + 10, y + 150);
            g.drawString("for", x + 170, y + 110);
            g.drawString("Wool:  " + resources[5], x + 250, y + 70);
            g.drawString("Stone: " + resources[6], x + 250, y + 90);
            g.drawString("Clay:  " + resources[8], x + 250, y + 110);
            g.drawString("Wheat: " + resources[9], x + 250, y + 130);
            g.drawString("Wood:  " + resources[7], x + 250, y + 150);
            acceptOffer.SetPos(x + 10, y + 180);
            declineOffer.SetPos(x + 250, y + 180);
            acceptOffer.draw();
            declineOffer.draw();
            if (acceptOffer.isWithin()) {
                offerWindow = false;
                trade = true;
            }
            if (declineOffer.isWithin()) {
                offerWindow = false;
                trade = true;
            }
            g.setColor(Color.white);
        }
    }

    public void EndTurn(int x, int y) {
        endTurn.SetPos(x, y);
        endTurn.draw();
        endTurn.AddText("End Turn", Color.white);
        if (endTurn.isWithin()) {
            System.out.println("Ending turn");
            PlayerStats.endTurn = true;
        }
    }
}
