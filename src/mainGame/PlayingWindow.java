package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Created by Kingo on 30-10-2015.
 */
public class PlayingWindow extends BasicGameState{
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
        buildingCost = new Image("resources/Buildingcost.jpg");
        increase_button = new Image("resources/increase.png");
        decrease_button = new Image("resources/decrease.png");
        accept_button = new Image("resources/accept.png");
        decline_button = new Image("resources/decline.png");
        acceptOffer = new Button(210, 280, 100, 25, new Image("resources/acceptOffer.png"));
        declineOffer = new Button(450, 280, 100, 25, new Image("resources/declineOffer.png"));
        counterOffer = new Button(335, 280, 100, 25, new Image("resources/counterOffer.png"));
        accept = new Button(645, 310, 25, 25, accept_button);
        decline = new Button(700, 310, 25, 25, decline_button);
        for (int i = 0; i < 5; i ++) {
            buttons[i] = new Button(290, 170 + 20 * i, 20, 20, increase_button);
            buttons[i+5] = new Button(530, 170 + 20 * i, 20, 20, increase_button);
            buttons[i+10] = new Button(320, 170 + 20 * i, 20, 20, decrease_button);
            buttons[i+15] = new Button(560, 170 + 20 * i, 20, 20, decrease_button);
        }

        map = new GameMap();
        textures = new Texture(this.getID());
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        PlayerList(graphics, names, turn);
        ResourceBar(graphics, 100, 100, 100, 100, 100);
        buildingCost.draw(593, 345, 200, 250);
        Trade(trade, names[turn], graphics);
        TradeWindow(tradeWindow, names[turn], graphics);
        OfferWindow(offerWindow, names[turn], graphics);
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
    }

    public void PlayerList (Graphics graphics, String[] _names, int turn) {
        graphics.drawString("Players:", 45, 40);
        graphics.drawLine(40, 60, 180, 60);
        graphics.drawRect(35, 35, 150, 200);
        for (int i = 0; i < 4; i++) {
            graphics.drawString(i+1 + ") " + _names[i], 45, 70 + 25*i);
        }
        graphics.drawRect(40, 67 + 25*turn, 140, 25);
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
            graphics.fill(new Rectangle(580, 290, 210, 50));
            graphics.setColor(Color.white);
            graphics.drawRect(580, 290, 210, 50);
            graphics.drawString(_name + " wants to trade", 585, 290);
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
            g.fill(new Rectangle(200, 100, 400, 300));
            g.setColor(Color.black);
            g.drawString(_name + " wants to trade", 210, 120);
            g.drawString(_name + "'s offer", 210, 150);
            /*for (int i = 0; i < 10; i ++) {
                buttons[i].draw();
            } */
            /*for (int i = 0; i < 5; i ++) {
                if (buttons[i].isWithin())
                    resources[i] ++;
                if (buttons[i+5].isWithin() && resources[i] > 0)
                    resources[i] --;
            } */
            g.drawString("Wool:  " + resources[0], 210, 170);
            g.drawString("Stone: " + resources[1], 210, 190);
            g.drawString("Wood:  " + resources[2], 210, 210);
            g.drawString("Clay:  " + resources[3], 210, 230);
            g.drawString("Wheat: " + resources[4], 210, 250);
            g.drawString("for", 350, 210);
            g.drawString("Wool:  " + resources[5], 450, 170);
            g.drawString("Stone: " + resources[6], 450, 190);
            g.drawString("Clay:  " + resources[8], 450, 210);
            g.drawString("Wheat: " + resources[9], 450, 230);
            g.drawString("Wood:  " + resources[7], 450, 250);
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
        }
    }

    public void OfferWindow(boolean boo, String _name, Graphics g) {
        if (boo) {
            g.setColor(Color.white);
            g.fill(new Rectangle(200, 100, 400, 300));
            g.setColor(Color.black);
            g.drawString("You offer to " + _name, 210, 120);
            g.drawString("Your offer", 210, 150);
            for (int i = 0; i < 20; i ++) {
                buttons[i].draw();
            }
            for (int i = 0; i < 10; i ++) {
                if (buttons[i].isWithin())
                    resources[i] ++;
                if (buttons[i+10].isWithin() && resources[i] > 0)
                    resources[i] --;
            }
            g.drawString("Wool:  " + resources[0], 210, 170);
            g.drawString("Stone: " + resources[1], 210, 190);
            g.drawString("Wood:  " + resources[2], 210, 210);
            g.drawString("Clay:  " + resources[3], 210, 230);
            g.drawString("Wheat: " + resources[4], 210, 250);
            g.drawString("for", 350, 210);
            g.drawString("Wool:  " + resources[5], 450, 170);
            g.drawString("Stone: " + resources[6], 450, 190);
            g.drawString("Clay:  " + resources[7], 450, 210);
            g.drawString("Wheat: " + resources[8], 450, 230);
            g.drawString("Wood:  " + resources[9], 450, 250);
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
        }
    }

    @Override
    public int getID() {
        return 3;
    }

}
