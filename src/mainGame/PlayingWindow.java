package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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

    /*public static void main(String[] args) throws SlickException {
        AppGameContainer appGameContainer = new AppGameContainer(new PlayingWindow());
        appGameContainer.setDisplayMode(800, 600, false);
        appGameContainer.start();
    } */

    public void PlayerList (Graphics graphics, String[] _names, int turn) {
        graphics.drawString("Players:", 45, 40);
        graphics.drawLine(40, 60, 180, 60);
        graphics.drawRect(35, 35, 150, 200);
        for (int i = 0; i < 4; i++) {
            graphics.drawString(i+1 + ") " + _names[i], 45, 70 + 25*i);
        }
        graphics.drawRect(40, 67 + 25*turn, 140, 25);
    }

    public void ResourceBar (Graphics graphics, int wool, int stone, int wood, int clay, int hay) {
        graphics.drawRect(200, 10, 500, 25);
        graphics.drawString("Wood: " + wood, 205, 13);
        graphics.drawString("Stone: " + stone, 305, 13);
        graphics.drawString("Wool: " + wool, 405, 13);
        graphics.drawString("Clay: " + clay, 505, 13);
        graphics.drawString("Hay: " + hay, 605, 13);
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

    public void Trade(boolean boo, String _name, Graphics graphics) {
        if (boo) {
            graphics.drawRect(580, 290, 210, 50);
            graphics.drawString(_name + " wants to trade", 585, 290);
            graphics.drawRect(650, 310, 25, 25);
            graphics.drawRect(700, 310, 25, 25);
        }
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        buildingCost = new Image("resources/Buildingcost.jpg");
        for (int i = 1; i <= cards.length; i++) {
            //cards[i-1] = new Image("resources/Cards/"+i+".jpg");
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        PlayerList(graphics, names, turn);
        ResourceBar(graphics, 100, 100, 100, 100, 100);
        buildingCost.draw(593, 345, 200, 250);
        CardHolder(numbers);
        Trade(true, names[1], graphics);
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
}
