package mainGame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Main extends BasicGame
{
	public static final int ScreenWidth = 800;
	public static final int ScreenHeight = 800;
	
	public static final int GameWindowWidth = ScreenWidth-100;
	public static final int GameWindowHeight = ScreenHeight-100;
	
	public static final int GameWindowOffset = 100;
	
	public static Image butt;
	public static Image doge;
    public static Image menuBackground;

    MainMenu menu;
	GameMap map;
	GUI_Navigator gui;

	
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		//Initialize Resources
		butt = new Image("resources/butt.png");
		doge = new Image("resources/doge.png");
		menuBackground = new Image("resources/menuBackground.jpg");
		gui = new GUI_Navigator();

		//Initialize Class-Objects
		menu = new MainMenu();
		map = new GameMap();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		menu.render(gc, g);
		map.render(gc, g);


	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Dick Butt: The Game"));
			appgc.setDisplayMode(ScreenWidth, ScreenHeight, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}