package mapClasses;

import mainGame.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;

import java.util.*;

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

        Integer [] yieldNumbers = {2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12};
        ArrayList<Integer> listOfYieldNumbers = new ArrayList<>(Arrays.asList(yieldNumbers));
        Collections.shuffle(listOfYieldNumbers);

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

                if (!listOfYieldNumbers.isEmpty())
                {
                    int yieldNum =  listOfYieldNumbers.remove(listOfYieldNumbers.size() - 1);
                    tile.setYieldNumber(yieldNum);
                }
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
            if (tile.getYieldNumber() != 0)
            {
                g.drawString(String.valueOf(tile.getYieldNumber()),
                        Layout.hexToPixel(mapLayout, tile).getX() - 6,
                        Layout.hexToPixel(mapLayout, tile).getY() - 8
                );
            }
        }
    }
}
