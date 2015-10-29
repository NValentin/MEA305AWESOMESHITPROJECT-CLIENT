package mainGame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Main extends BasicGame
{
	public static final int ScreenWidth = 640;
	public static final int ScreenHeight = 480;
	
	public static final int GameWindowWidth = ScreenWidth-100;
	public static final int GameWindowHeight = ScreenHeight-100;
	
	public static final int GameWindowOffset = 50;
	
	public static Image butt;
	public static Image doge;
    public static Image menuBackground;

    MainMenu menu;

	GameMap map;
	public Shape[] hexagons;
	
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		menu = new MainMenu();

		butt = new Image("resources/butt.png");
		doge = new Image("resources/doge.png");
        //menuBackground = new Image("resources/menuBackground.jpg");

		hexagons = new Shape[19];
		for (int i = 0; i < hexagons.length; i++)
			hexagons[i] = new Polygon();

		map = new GameMap(GameWindowWidth, GameWindowHeight, 13, 10, GameWindowOffset, hexagons);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//butt.draw(50, 50, _screenWidth-100, _screenHeight-100);
		//butt.setFilter(Image.FILTER_LINEAR);
		//map.render(gc, g);
        //menu.render(gc, g);
		
		//map.render(gc, g);

		for (int i = 0; i < hexagons.length; i++)
			g.draw(hexagons[i]);

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