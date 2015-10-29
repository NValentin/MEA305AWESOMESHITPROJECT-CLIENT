package mainGame;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class GameMap
{
	Random rand = new Random();

	private Shape[][] mapGrid;
	private int tileSize = 50;
	private int offSet = tileSize*2;

	private int startX = Main.ScreenWidth/10;
	private int startY = Main.ScreenHeight/10;

	Image tile; 
	
	GameMap()
	{
		mapGrid = new Shape[7][7];
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				mapGrid[i][j] = addTile(offSet*(i)+startX, offSet*(j)+startY);
			}
		}
	}

	private Polygon addTile(float centerX, float centerY)
	{
		Polygon tmp = new Polygon();

		for (int i = 0; i < 6; i++)
		{
			float angle_deg = 60 * i;
			float angle_rad = (float) Math.PI / 180 * angle_deg;

			tmp.addPoint((int) (centerX + tileSize * Math.cos(angle_rad)),
					(int) (centerY + tileSize * Math.sin(angle_rad)));
		}
		return tmp;
	}
	
	char returnTileType()
	{
		int tmpNum = rand.nextInt(2);
		
		char tileType = '0';
		
		if(tmpNum == 0)
			tileType = '0';
		else if (tmpNum == 1)
			tileType = 'D';
		
		return tileType;
	}
	
	void render(GameContainer gc, Graphics g) throws SlickException
	{
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				g.draw(mapGrid[i][j]);
				g.fill(mapGrid[i][j]);
			}
		}
	}

}
