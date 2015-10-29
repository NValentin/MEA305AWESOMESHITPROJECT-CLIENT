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


	private Point[] _centerPoints;
	private Shape[] _tiles;
	private byte tileSize = 30;

	Image tile; 
	
	GameMap()
	{
		_centerPoints = new Point[19];
		createCenterPoints();
		for (Point point : _centerPoints)
			System.out.print(point.getCenterX()+" "+point.getCenterY());

		_tiles = new Shape[19];
		for (int i = 0; i < _centerPoints.length; i++)
			_tiles[i] = new Polygon();

		for (int i = 0; i < _tiles.length; i++)
		{
			Polygon tmp = (Polygon) _tiles[i];
			createTile(_centerPoints[i].getCenterX(), _centerPoints[i].getCenterY(), tmp);
		}
	}

	void createCenterPoints()
	{
		int centerPointX = Main.GameWindowWidth/2;
		int centerPointY = Main.GameWindowHeight/2;

		for (int i = 0; i < _centerPoints.length; i++)
		{
			_centerPoints[i] = new Point(centerPointX+(tileSize*2*i), centerPointY);
		}

	}

	void createTile(float centerX, float centerY, Polygon hex)
	{
			for (int i = 0; i < 6; i++)
			{
				float angle_deg = 60 * i;
				float angle_rad = (float) Math.PI / 180 * angle_deg;

				hex.addPoint((int) (centerX + tileSize * Math.cos(angle_rad)),
						(int) (centerY + tileSize * Math.sin(angle_rad)));
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
		for (Shape tile : _tiles)
		{
			g.draw(tile);
			g.fill(tile);
		}
	}

}
