package mapClasses;

import mainGame.House;
import mainGame.Main;
import mainGame.Road;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

import java.util.*;


public class GameMap
{
    private ArrayList<Tile> map;

    private ArrayList<Point> housePlots;
    private ArrayList<House> houses;

    private ArrayList<Line> roadPlots;
    private ArrayList<Road> roads;

    private static Layout mapLayout;

    public GameMap()
    {
        buildMap(55, Layout.pointy);
        findHousePlots();
        findRoadPlots();

        houses = new ArrayList<>();
        if (!housePlots.isEmpty())
        for (Point point : housePlots)
            houses.add(new House(point.getX(), point.getY(), new Color(Color.blue)));

        roads = new ArrayList<>();
        for (Line line : roadPlots)
            roads.add(new Road(line, new Color(Color.blue)));
    }

    private void findHousePlots()
    {
        housePlots = new ArrayList<>();

        for (Tile tile : map)
        {
            if (!(Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3))
            {
                ArrayList<Point> centerPoints = Layout.polygonCorners(mapLayout, tile);
                for (Point point : centerPoints)
                {
                    if (!housePlots.contains(point))
                        housePlots.add(point);
                }
            }
        }
    }

    private void findRoadPlots()
    {
        roadPlots = new ArrayList<>();
        for (Tile tile : map)
        {
            if (!(Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3))
            {
                ArrayList<Point> tmpPlots = Layout.polygonCorners(mapLayout, tile);
                for (int i = 0; i < tmpPlots.size()-1; i++)
                {
                    Line l = new Line(tmpPlots.get(i).getX(), tmpPlots.get(i).getY(),
                            tmpPlots.get(i+1).getX(), tmpPlots.get(i+1).getY());

                    if (!roadPlots.contains(l))
                        roadPlots.add(new Line(tmpPlots.get(i).getX(), tmpPlots.get(i).getY(),
                                tmpPlots.get(i+1).getX(), tmpPlots.get(i+1).getY()));
                }
            }
        }
    }

    private void buildMap(int tileSize, Orientation orientation)
    {
        mapLayout = new Layout(orientation,
                new Point(tileSize, tileSize),
                new Point(Main.ScreenWidth / 2, Main.ScreenHeight / 2));

        map = new ArrayList<>();
        for (int q = -3; q <= 3; q++)
        {
            int r1 = Math.max(-3, -q - 3);
            int r2 = Math.min(3, -q + 3);
            for (int r = r1; r <= r2; r++)
            {
                map.add(new Tile(q, r, -q - r));
            }
        }
        assignTileVariables();
    }

    private void assignTileVariables()
    {
        Integer[] yieldNumbers = {2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12};
        ArrayList<Integer> listOfYieldNumbers = new ArrayList<>(Arrays.asList(yieldNumbers));
        Collections.shuffle(listOfYieldNumbers);

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
            if (Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3)
            {
                tile.setTileType("Water");
            } else
            {
                String type = tile.getTileType();

                if (tile.getTileType().equalsIgnoreCase("Default"))
                {
                    if (!listOfTileTypes.isEmpty())
                    {
                        type = listOfTileTypes.remove(listOfTileTypes.size() - 1);
                        tile.setTileType(type);
                    }
                }

                if (!listOfYieldNumbers.isEmpty())
                {
                    int yieldNum = listOfYieldNumbers.remove(listOfYieldNumbers.size() - 1);
                    if (!tile.getTileType().equalsIgnoreCase("desert"))
                        tile.setYieldNumber(yieldNum);
                }
            }
        }
    }

    public ArrayList tilesYieldingResource(int diceRoll)
    {
        ArrayList<Tile> resourceYieldingTiles = new ArrayList<>();
        for (Tile tile : map)
        {
            if (tile.getYieldNumber() == diceRoll)
                resourceYieldingTiles.add(tile);
        }
        return resourceYieldingTiles;
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
        for (Road road : roads)
        {
            road.render(g);
        }

        for (House house : houses)
        {
            house.render(g);
        }

    }
}
