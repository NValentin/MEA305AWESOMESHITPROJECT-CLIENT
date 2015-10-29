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

	Image tile; 
	
	GameMap(Shape[] hexes)
	{
		_centerPoints = new Point[19];
		_centerPoints[0] = new Point(100, 100);

		for (Shape tiles : _tiles)
			createTile(_centerPoints[0].getCenterX(), _centerPoints[0].getCenterY(), (Polygon) (tiles));

		for (Shape shape : hexes)
		{
			Polygon tmp = (Polygon) shape;
			createTile(50,50, tmp);
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

	}

}
