package mainGame;

import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class GameMap
{
    private ArrayList<Tile> map;
    private static Layout mapLayout;

    private int tileSize = 40;
    private int tileHeight = tileSize * 2;
    private float tileWidth = (float) Math.sqrt(3) / 2 * tileHeight;

    GameMap()
    {
        mapLayout = new Layout(Layout.pointy,
                new Point(tileSize, tileSize),
                new Point(Main.ScreenWidth/2, Main.ScreenHeight/2));

        map = new ArrayList<>();
        for (int q = -3; q <= 3; q++)
        {
            int r1 = Math.max(-3, -q - 3);
            int r2 = Math.min(3, -q + 3);
            for (int r = r1; r <= r2; r++)
            {
                map.add(new Tile(q, r, -q-r));
            }
        }
    }

    void render(GameContainer gc, Graphics g) throws SlickException
    {
        for (Tile tile : map)
        {
            Polygon tmpPoly = new Polygon();
            ArrayList<Point> tmpPoints = Layout.polygonCorners(mapLayout, tile);

            for (int i = 0; i < 6; i++)
            {
                float angle_deg = 60 * i + 30;
                float angle_rad = (float) Math.PI / 180 * angle_deg;

                tmpPoly.addPoint((int) (Layout.hexToPixel(mapLayout, tile).getX() + tileSize * Math.cos(angle_rad)),
                        (int) (Layout.hexToPixel(mapLayout, tile).getY() + tileSize * Math.sin(angle_rad)));
            }
            g.draw(tmpPoly);
        }
    }
}
