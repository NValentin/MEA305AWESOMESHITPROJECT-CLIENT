package mainGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class GameMap
{
	private Shape[][] mapGrid;

	GameMap()
	{
		mapGrid = new Shape[7][7];
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				mapGrid[i][j] = new Tile(Main.ScreenWidth/2, Main.ScreenHeight/2, 'G');
			}
		}
	}
	
	void render(GameContainer gc, Graphics g) throws SlickException
	{
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
                Tile tile = (Tile) mapGrid[i][j];
				g.texture(tile, tile.returnTextureByType(), true);
			}
		}
	}

}
