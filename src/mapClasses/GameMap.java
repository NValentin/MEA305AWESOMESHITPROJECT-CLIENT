package mapClasses;

import mainGame.Main;
import mainGame.PlayingWindow;
import mainGame.Texture;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameMap
{

    private ArrayList<Tile> map;
    private static Layout mapLayout;

    public GameMap()
    {
        int tileSize = 40;
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
        ArrayList<String> listOfTileTypes = new ArrayList<>(
                Arrays.<String>asList(
                        "Grain", "Grain", "Grain", "Grain",
                        "Lumber", "Lumber", "Lumber", "Lumber",
                        "Wool", "Wool", "Wool", "Wool",
                        "Ore", "Ore", "Ore",
                        "Brick", "Brick", "Brick",
                        "Desert"));
        Collections.shuffle(listOfTileTypes);
        for (Tile tile : map)
        {
            if(Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3)
            {
                tile.setTileType("Water");
            } else {
                String type = "Default";
                if (!listOfTileTypes.isEmpty())
                {
                    type = listOfTileTypes.remove(listOfTileTypes.size() - 1);
                }
                tile.setTileType(type);
            }
        }
    }

    public void render(Graphics g) throws SlickException
    {
        for (Tile tile : map)
        {
            Polygon tmpPoly = new Polygon();
            ArrayList<Point> tmpPoints = Layout.polygonCorners(mapLayout, tile);
            for (Point point : tmpPoints)
                tmpPoly.addPoint(point.getX(), point.getY());

            Image tmpTexture = tile.returnTextureByType();
            g.texture(tmpPoly, tmpTexture, true);
            tmpTexture.setFilter(Image.FILTER_LINEAR);
        }
    }
}
