package mainGame;

import mapClasses.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Created by Kingo on 30-10-2015.
 */
public class PlayingWindow extends BasicGameState{
    int theWidth = Main.ScreenWidth;
    int theHeight = Main.ScreenHeight;
    String[] names = new String [] {"Fred", "George", "Ron", "Ginny"};
    int turn = 1;
    Image buildingCost = null;
    Image increase_button, decrease_button, accept_button, decline_button;
    Button accept, decline;
    Button acceptOffer, declineOffer, counterOffer;
    Button[] buttons = new Button[20];
    boolean trade = true;
    boolean tradeWindow = false;
    boolean offerWindow = false;
    int [] resources = new int[10];

    GameMap map;
    public static Texture textures;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        textures = new Texture(this.getID());
        map = new GameMap();

        buildingCost = new Image("resources/Buildingcost.jpg");
        increase_button = new Image("resources/increase.png");
        decrease_button = new Image("resources/decrease.png");
        accept_button = new Image("resources/accept.png");
        decline_button = new Image("resources/decline.png");
        acceptOffer = new Button(theWidth/2 - 190, theHeight/2 + 30, 100, 25, new Image("resources/acceptOffer.png"));
        declineOffer = new Button(theWidth/2 + 50, theHeight/2 + 30, 100, 25, new Image("resources/declineOffer.png"));
        counterOffer = new Button(theWidth/2 - 65, theHeight/2 + 30, 100, 25, new Image("resources/counterOffer.png"));
        accept = new Button(theWidth - 155, theHeight - 30, 25, 25, accept_button);
        decline = new Button(theWidth - 100, theHeight - 30, 25, 25, decline_button);
        for (int i = 0; i < 5; i ++) {
            buttons[i] = new Button(theWidth/2 - 110, theHeight/2 - 80 + 20 * i, 20, 20, increase_button);
            buttons[i+5] = new Button(theWidth/2 + 130, theHeight/2 - 80 + 20 * i, 20, 20, increase_button);
            buttons[i+10] = new Button(theWidth/2 - 80, theHeight/2 - 80 + 20 * i, 20, 20, decrease_button);
            buttons[i+15] = new Button(theWidth/2 + 160, theHeight/2 - 80 + 20 * i, 20, 20, decrease_button);
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render(graphics);
        PlayerList(graphics, names, turn);
        ResourceBar(graphics, 100, 100, 100, 100, 100);
        buildingCost.draw(theWidth - 205, theHeight - 255, 200, 250);
        Trade(trade, names[turn], graphics);
        TradeWindow(tradeWindow, names[turn], graphics);
        OfferWindow(offerWindow, names[turn], graphics);
        ChatWindow(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyPressed(Input.KEY_1)) {
            turn = 0;
        }
        if (input.isKeyPressed(Input.KEY_2)) {
            turn = 1;
        }
        if (input.isKeyPressed(Input.KEY_3)) {
            turn = 2;
        }
        if (input.isKeyPressed(Input.KEY_4)) {
            turn = 3;
        }
        if (input.isKeyPressed(Input.KEY_5)) {
            trade = true;
        }
    }

    public void PlayerList (Graphics graphics, String[] _names, int turn) {
        graphics.drawString("Players:", 45, 40);
        graphics.drawLine(40, 60, 180, 60);
        graphics.drawRect(35, 35, 150, 200);
        for (int i = 0; i < 4; i++) {
            graphics.drawString(i+1 + ") " + _names[i], 45, 70 + 25*i);
        }
        graphics.drawRect(40, 67 + 25 * turn, 140, 25);
    }

    public void ResourceBar (Graphics graphics, int wool, int stone, int wood, int clay, int wheat) {
        graphics.drawRect(200, 10, 500, 25);
        graphics.drawString("Wood: " + wood, 205, 13);
        graphics.drawString("Stone: " + stone, 305, 13);
        graphics.drawString("Wool: " + wool, 405, 13);
        graphics.drawString("Clay: " + clay, 505, 13);
        graphics.drawString("Wheat: " + wheat, 605, 13);
        graphics.drawLine(300, 10, 300, 35);
        graphics.drawLine(400, 10, 400, 35);
        graphics.drawLine(500, 10, 500, 35);
        graphics.drawLine(600, 10, 600, 35);
    }

    public void Trade(boolean boo, String _name, Graphics graphics) throws SlickException{
        if (boo) {
            graphics.setColor(Color.black);
            graphics.fill(new Rectangle(theWidth - 215, theHeight - 55, 210, 50));
            graphics.setColor(Color.white);
            graphics.drawRect(theWidth - 215, theHeight - 55, 210, 50);
            graphics.drawString(_name + " wants to trade", theWidth - 210, theHeight - 55);
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

    public void TradeWindow(boolean boo, String _name, Graphics g) {
        if (boo) {
            g.setColor(Color.white);
            g.fill(new Rectangle(theWidth/2 - 200, theHeight/2 - 150, 400, 300));
            g.setColor(Color.black);
            g.drawString(_name + " wants to trade", theWidth/2 - 190, theHeight/2 - 130);
            g.drawString(_name + "'s offer", theWidth/2 - 190, theHeight/2 - 100);
            g.drawString("Wool:  " + resources[0], theWidth/2 - 190, theHeight/2 - 80);
            g.drawString("Stone: " + resources[1], theWidth/2 - 190, theHeight/2 - 60);
            g.drawString("Wood:  " + resources[2], theWidth/2 - 190, theHeight/2 - 40);
            g.drawString("Clay:  " + resources[3], theWidth/2 - 190, theHeight/2 - 20);
            g.drawString("Wheat: " + resources[4], theWidth/2 - 190, theHeight/2);
            g.drawString("for", theWidth/2 - 50, theHeight/2 - 40);
            g.drawString("Wool:  " + resources[5], theWidth/2 + 50, theHeight/2 - 80);
            g.drawString("Stone: " + resources[6], theWidth/2 + 50, theHeight/2 - 60);
            g.drawString("Clay:  " + resources[8], theWidth/2 + 50, theHeight/2 - 40);
            g.drawString("Wheat: " + resources[9], theWidth/2 + 50, theHeight/2 - 20);
            g.drawString("Wood:  " + resources[7], theWidth/2 + 50, theHeight/2);
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

    public void OfferWindow(boolean boo, String _name, Graphics g) {
        if (boo) {
            g.setColor(Color.white);
            g.fill(new Rectangle(theWidth/2 - 200, theHeight/2 - 150, 400, 300));
            g.setColor(Color.black);
            g.drawString(_name + " wants to trade", theWidth/2 - 190, theHeight/2 - 130);
            g.drawString(_name + "'s offer", theWidth/2 - 190, theHeight/2 - 100);
            for (int i = 0; i < 20; i ++) {
                buttons[i].draw();
            }
            for (int i = 0; i < 10; i ++) {
                if (buttons[i].isWithin())
                    resources[i] ++;
                if (buttons[i+10].isWithin() && resources[i] > 0)
                    resources[i] --;
            }
            g.drawString("Wool:  " + resources[0], theWidth/2 - 190, theHeight/2 - 80);
            g.drawString("Stone: " + resources[1], theWidth/2 - 190, theHeight/2 - 60);
            g.drawString("Wood:  " + resources[2], theWidth/2 - 190, theHeight/2 - 40);
            g.drawString("Clay:  " + resources[3], theWidth/2 - 190, theHeight/2 - 20);
            g.drawString("Wheat: " + resources[4], theWidth/2 - 190, theHeight/2);
            g.drawString("for", theWidth/2 - 50, theHeight/2 - 40);
            g.drawString("Wool:  " + resources[5], theWidth/2 + 50, theHeight/2 - 80);
            g.drawString("Stone: " + resources[6], theWidth/2 + 50, theHeight/2 - 60);
            g.drawString("Clay:  " + resources[8], theWidth/2 + 50, theHeight/2 - 40);
            g.drawString("Wheat: " + resources[9], theWidth/2 + 50, theHeight/2 - 20);
            g.drawString("Wood:  " + resources[7], theWidth/2 + 50, theHeight/2);
            acceptOffer.draw();
            declineOffer.draw();
            if (acceptOffer.isWithin()) {
                offerWindow = false;
                trade =  true;
            }
            if (declineOffer.isWithin()) {
                offerWindow = false;
                trade =  true;
            }
            g.setColor(Color.white);
        }
    }

    public void ChatWindow (GameContainer gameContainer, Graphics graphics) {
        Font f_chat = new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 12), true);
        graphics.fill(new Rectangle(10, 390, 200, 200));
        TextField chat_textfield = new TextField(gameContainer, f_chat, 10, 550, 200, 40);
        chat_textfield.render(gameContainer, graphics);
    }

    @Override
    public int getID() {
        return 3;
    }

}
