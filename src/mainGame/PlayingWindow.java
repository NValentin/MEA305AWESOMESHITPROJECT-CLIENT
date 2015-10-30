package mainGame;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.opengl.SlickCallable;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kingo on 30-10-2015.
 */
public class PlayingWindow extends BasicGame{
    String[] names = new String [] {"Fred", "George", "Ron", "Ginny"};
    int turn = 1;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_1)) {
            System.out.println(turn+1);
            turn = 0;
        }
        if (input.isKeyDown(Input.KEY_2)) {
            System.out.println(turn+1);
            turn = 1;
        }
        if (input.isKeyDown(Input.KEY_3)) {
            System.out.println(turn+1);
            turn = 2;
        }
        if (input.isKeyDown(Input.KEY_4)) {
            System.out.println(turn+1);
            turn = 3;
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        PlayerList(graphics, names, turn);
        ResourceBar(graphics, 100, 100, 100, 100, 100);
    }

    public PlayingWindow(String title) {
        super(title);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer appGameContainer = new AppGameContainer(new PlayingWindow("Playing"));
        appGameContainer.setDisplayMode(800, 600, false);
        appGameContainer.start();
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
}
