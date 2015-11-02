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
    int[] numbers = new int[] {0, 1, 2, 4};
    int turn = 1;
    Image buildingCost = null;
    Image[] cards = new Image[4];
    Image increase_button;
    Image decrease_button;
    Button accept, decline;
    Button[] buttons = new Button[10];
    boolean trade = true;
    boolean tradeWindow = false;
    int [] resources = new int[5];

    GameMap map;
    public static Texture textures;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        buildingCost = new Image("resources/Buildingcost.jpg");
        increase_button = new Image("resources/increase.png");
        decrease_button = new Image("resources/decrease.png");
        for (int i = 1; i <= cards.length; i++) {
            //cards[i-1] = new Image("resources/Cards/"+i+".jpg");
        }

        map = new GameMap();
        textures = new Texture(this.getID());
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        PlayerList(graphics, names, turn);
        ResourceBar(graphics, 100, 100, 100, 100, 100);
        buildingCost.draw(593, 345, 200, 250);
        CardHolder(numbers);
        Trade(trade, names[turn], graphics);
        TradeWindow(tradeWindow, names[turn], graphics);
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

    public void CardHolder(int[] cardId) {
        for (int i = 0; i < cardId.length; i++) {
            //cards[i].draw(100 + 55*i, 500, 100, 200);
        }
    }

    public void Trade(boolean boo, String _name, Graphics graphics) throws SlickException{
        if (boo) {
            graphics.setColor(Color.black);
            graphics.fill(new Rectangle(580, 290, 210, 50));
            graphics.setColor(Color.white);
            graphics.drawRect(580, 290, 210, 50);
            graphics.drawString(_name + " wants to trade", 585, 290);
            accept = new Button(645, 310, 25, 25, new Image("resources/accept.png"));
            decline = new Button(700, 310, 25, 25, new Image("resources/decline.png"));
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
            g.fill(new Rectangle(200, 100, 300, 400));
            g.setColor(Color.black);
            g.drawString(_name + " wants to trade", 210, 120);
            g.drawString("Your offer", 210, 150);
            g.drawString(_name + "'s offer", 210, 340);
            for (int i = 0; i < 5; i ++) {
                buttons[i] = new Button(290, 170 + 20 * i, 20, 20, increase_button);
                buttons[i+5] = new Button(320, 170 + 20 * i, 20, 20, decrease_button);
            }
            for (int i = 0; i < 5; i ++) {
                if (buttons[i].isWithin())
                    resources[i] ++;
                if (buttons[i+5].isWithin())
                    resources[i] --;
            }
            g.drawString("Wool:  " + resources[0], 210, 170);
            g.drawString("Stone: " + resources[1], 210, 190);
            g.drawString("Wood:  " + resources[2], 210, 210);
            g.drawString("Clay:  " + resources[3], 210, 230);
            g.drawString("Wheat: " + resources[4], 210, 250);
        }
    }

    @Override
    public int getID() {
        return 3;
    }

}
