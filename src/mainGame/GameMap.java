package mainGame;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameMap
{
	Random rand = new Random();
	
	char[] tiles;
	char[][] Map;
	
	int offset;
	
	int tilesWide;
	int tilesHigh;
	
	int tileWidth;
	int tileHeight;

	Image tile; 
	
	GameMap(int width, int height, int tilesWide, int tilesHigh, int offset)
	{
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
