package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;

public class MainMenu
{

    Image menuBackground;
    int offset;

	void render(GameContainer gc, Graphics g) throws SlickException
	{
        this.offset = offset;
        menuBackground = Main.menuBackground;

		menuBackground.draw(Main.GameWindowOffset,Main.GameWindowOffset,Main.GameWindowWidth,Main.GameWindowHeight);
	}

}
