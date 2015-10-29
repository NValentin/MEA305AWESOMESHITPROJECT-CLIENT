package mainGame;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class GameMap
{
	Random rand = new Random();

	private Point[] centerPoints;
	
	char[] tiles;
	char[][] Map;
	
	int offset;
	
	int tilesWide;
	int tilesHigh;
	
	int tileWidth;
	int tileHeight;

	Image tile; 
	
	GameMap(int width, int height, int tilesWide, int tilesHigh, int offset, Shape[] hexes)
	{
		centerPoints = new Point[19];
		centerPoints[0] = new Point(100, 100);

		for (Shape shape : hexes)
		{
			Polygon tmp = (Polygon) shape;
			createTile(50,50, tmp);
		}

		this.tilesWide = tilesWide;
		this.tilesHigh = tilesHigh;
		this.offset = offset;
		
		tileWidth = width/tilesWide;
		tileHeight = height/tilesHigh;
		
		tiles = new char[] {'0', 'D'};
		
		Map = new char[tilesWide][tilesHigh];
		for (int i = 0; i < tilesWide; i++)
		{
			for(int j = 0; j < tilesHigh; j++)
			{
				Map[i][j] = returnTileType();
			}
		}
		
		
	}

	void createTile(float centerX, float centerY, Polygon hex)
	{
			for (int i = 0; i < 6; i++)
			{
				float size = 30;
				float angle_deg = 60 * i;
				float angle_rad = (float) Math.PI / 180 * angle_deg;

				hex.addPoint((int) (centerX + size * Math.cos(angle_rad)),
						(int) (centerY + size * Math.sin(angle_rad)));
			}
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
		int xPos, yPos;
		char arrayContent;
		
		for (int i = 0; i < tilesWide; i++)
		{
			for(int j = 0; j < tilesHigh; j++)
			{
				xPos = i;
				yPos = j;
				
				arrayContent = Map[xPos][yPos];
				if (arrayContent == '0')
				{
					tile = Main.butt;
					tile.draw(xPos*tileWidth+offset, yPos*tileHeight+offset, 
							tileWidth, tileHeight);
				}
				else if (arrayContent == 'D')
				{
					tile = Main.doge;
					tile.draw(xPos*tileWidth+offset, yPos*tileHeight+offset, 
							tileWidth, tileHeight);
				}
			}
		}
	}

}
