package mainGame;

import mapClasses.GameMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Kingo on 25-Nov-15.
 */
public class GameObjects {
    Texture textures;

    int theWidth = Main.ScreenWidth;
    int theHeight = Main.ScreenHeight;
    Image buildingCost, makeNewTrade_image;
    Button accept, decline;
    Button acceptOffer, declineOffer, counterOffer;
    Button[] makeNewTrade =  new Button[4];
    Button[] buttons = new Button[20];
    Button[] trade_buttons = new Button[4];
    boolean trade = true;
    boolean tradeWindow = false;
    boolean offerWindow = false;
    int[] currentResources = new int[5];
    int [] resources = new int[10];
    GatheringResources gathering;
    GameMap map;

    GameObjects(){
        try {
            InitailizeTexture();
        } catch (SlickException e) { System.out.println("Problems loading pictures for the playing window: " + e); }
    }

    public void InitailizeTexture() throws SlickException{
        buildingCost = new Image("resources/Buildingcost.jpg");
        makeNewTrade_image = new Image("resources/trade_button.png");
        accept = new Button(theWidth - 155, theHeight - 30, 25, 25, new Image("resources/accept.png"));
        decline = new Button(theWidth - 100, theHeight - 30, 25, 25, new Image("resources/decline.png"));
        acceptOffer = new Button(theWidth / 2 - 190, theHeight / 2 + 30, 100, 25, new Image("resources/acceptOffer.png"));
        declineOffer = new Button(theWidth / 2 + 50, theHeight / 2 + 30, 100, 25, new Image("resources/declineOffer.png"));
        counterOffer = new Button(theWidth / 2 - 65, theHeight / 2 + 30, 100, 25, new Image("resources/counterOffer.png"));
        for (int i = 0; i < trade_buttons.length; i ++) {
            trade_buttons[i] = new Button(0, 0, 0, 0, new Image("resources/trade.png"));
        }
        for (int i = 0; i < makeNewTrade.length; i ++) {
            makeNewTrade[i] = new Button(0,0, 80, 20, makeNewTrade_image, 20);
        }
        for (int i = 0; i < 5; i++) {
            buttons[i] = new Button(theWidth / 2 - 110, theHeight / 2 - 80 + 20 * i, 20, 20, new Image("resources/increase.png"));
            buttons[i + 5] = new Button(theWidth / 2 + 130, theHeight / 2 - 80 + 20 * i, 20, 20, new Image("resources/increase.png"));
            buttons[i + 10] = new Button(theWidth / 2 - 80, theHeight / 2 - 80 + 20 * i, 20, 20, new Image("resources/decrease.png"));
            buttons[i + 15] = new Button(theWidth / 2 + 160, theHeight / 2 - 80 + 20 * i, 20, 20, new Image("resources/decrease.png"));
        }
        textures = new Texture();
        textures.initPlayingWindowTextures();
        map = new GameMap();
        gathering = new GatheringResources(map);
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

    public void PlayerList (int x, int y, Graphics graphics, String[] _names, int turn) {
        graphics.drawString("Players:", x + 10, x + 5);
        graphics.drawLine(x + 5, y + 25, x + 145, y + 25);
        graphics.drawRect(x, y, 150, 200);
        for (int i = 0; i < _names.length; i++) {
            graphics.drawString(i + 1 + ") " + _names[i], x + 10, y + 35 + 25 * i);
        }
        graphics.drawRect(x + 5, y + 32 + 25 * turn, 140, 25);
    }

    public void ResourceBar (int x, int y, Graphics graphics, int wool, int stone, int wood, int clay, int wheat) {
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

    public void IncomingTradeWindow(int x, int y, boolean boo, String _name, Graphics g) {
        if (boo) {
            g.setColor(Color.white);
            g.fill(new Rectangle(x, y, 400, 300));
            g.setColor(Color.black);
            g.drawString(_name + " wants to trade", x + 10, y + 20);
            g.drawString(_name + "'s offer", x + 10, y +50);
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
        g.setColor(Color.white);
        g.fill(new Rectangle(x, y, 200, 150));
        g.setColor(Color.black);
        g.drawString("Trade with: ", x + 10, y + 10);
        g.drawLine(x, y + 30, x + 200, y + 30);
        for (int i = 0; i < _names.length; i++) {
            g.drawString(_names[i], x + 10, y + 40 + 25 * i);
        }
        for (int i = 0; i < _names.length; i ++) {
            trade_buttons[i].SetPos(x + 100, y + 40 + 25 * i);
            trade_buttons[i].draw();
            makeNewTrade[i].SetPos(x + 100, y + 40 + 25 * i);
            makeNewTrade[i].draw();
            makeNewTrade[i].AddText("Trade", Color.black);
            if (makeNewTrade[i].isWithin()) {
                System.out.println("Trading with " + _names[i]);
            }
        }
        g.setColor(Color.white);
    }

    public void OfferWindow(int x, int y, boolean boo, String _name, Graphics g) {
        if (boo) {
            g.setColor(Color.white);
            g.fill(new Rectangle(x, y, 400, 300));
            g.setColor(Color.black);
            g.drawString(_name + " wants to trade", x + 10, y + 20);
            g.drawString(_name + "'s offer", x + 10, y +50);
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
            g.drawString("for", x + 150, y + 110);
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
}
