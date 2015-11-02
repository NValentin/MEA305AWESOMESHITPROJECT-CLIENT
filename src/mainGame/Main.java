package mainGame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame
{
	public static final int ScreenWidth = 800;
	public static final int ScreenHeight = 600;
	
	public static final int GameWindowWidth = ScreenWidth-100;
	public static final int GameWindowHeight = ScreenHeight-100;
	
	public static final int GameWindowOffset = 100;

	public static Texture texture;
    /*
    MainMenu menu;
	GameMap map;*/

	
	public Main(String gameName)
	{
		super(gameName);
	}

	/*@Override
	public void init(GameContainer gc) throws SlickException 
	{
		//Initialize Resources
		texture = new Texture();

		//Initialize Class-Objects
		map = new GameMap();
	}*/

    /*@Override
	public void update(GameContainer gc, int i) throws SlickException
	{
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		map.render(gc, g);
	}*/

	public static void main(String[] args) throws SlickException
	{
        AppGameContainer appgc = new AppGameContainer(new Main("Dick Butt: The Game"));

        appgc.setDisplayMode(ScreenWidth, ScreenHeight, false);
        appgc.setAlwaysRender(true);

		appgc.start();
	}

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new MainMenuState());
        this.addState(new CreateLobbyState());
        this.addState(new JoinLobbyState());
		this.addState(new PlayingWindow());
    }
}