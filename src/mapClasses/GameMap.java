package mapClasses;

import mainGame.House;
import mainGame.Main;
import mainGame.Road;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.*;


public class GameMap
{
    private static Layout mapLayout;
    private ArrayList<Tile> map;

    private ArrayList<Circle> housePlots;
    private ArrayList<House> houses;

    private ArrayList<Line> roadPlots;
    private ArrayList<Road> roads;

    public GameMap()
    {
        Random randNum = new Random();
        int mapTileSize = 55;
        buildMap(mapTileSize, Layout.pointy);
        findHousePlots();
        findRoadPlots();

        houses = new ArrayList<>();

        /// JUST FOR TESTING
        for (int i = 0; i < housePlots.size()-1; i++)
        {
             addHouse(housePlots.get(i), new Color(randNum.nextInt(255), randNum.nextInt(255), randNum.nextInt(255)));
        }


        for (int i = 0; i < houses.size() - 1; i += 2)
            houses.get(i).upgradeHouse();

        roads = new ArrayList<>();
        for (Line line : roadPlots)
            roads.add(new Road(line, new Color(randNum.nextInt(255), randNum.nextInt(255), randNum.nextInt(255))));


        //this.debugTileInfo();
        ///
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
                    Circle tmpCircle = new Circle(point.getX(), point.getY(), 10);
                    if (!housePlots.contains(tmpCircle))
                        housePlots.add(tmpCircle);
                }
            }
        }
    }

    public void addHouse(Circle circle, Color playerColor)
    {
        if (canPlaceHouse(circle))
        {
            houses.add(new House(circle, playerColor));
        } else
        {
            System.out.println("cant place house");
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
                for (int i = 0; i < tmpPlots.size() - 1; i++)
                {
                    Line l = new Line(tmpPlots.get(i).getX(), tmpPlots.get(i).getY(),
                            tmpPlots.get(i + 1).getX(), tmpPlots.get(i + 1).getY());

                    if (!roadPlots.contains(l))
                        roadPlots.add(l);
                }
            }
        }
    }

    public void addRoad(Line roadLine, Color playerColor)
    {
        roads.add(new Road(roadLine, playerColor));
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
        Integer[] yieldNumbers = {2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12};
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
                    if (!tile.getTileType().equalsIgnoreCase("desert"))
                    {
                        int yieldNum = listOfYieldNumbers.remove(listOfYieldNumbers.size() - 1);
                        tile.setYieldNumber(yieldNum);
                    }
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

    private boolean canPlaceHouse(Circle desiredHousePlot)
    {
        boolean state = false;
        if (houses.isEmpty())
            state = true;

        for (House house : houses)
        {
            state = new Vector2f(house.getHouseCircle().getCenterX(), house.getHouseCircle().getCenterY()).distance(
                    new Vector2f(desiredHousePlot.getCenterX(), desiredHousePlot.getCenterY())) > 60;
        }
        return state;
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

    private void debugTileInfo()
    {
        for (Tile tile : map)
        {
            if (!(Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3))
                System.out.println("Tile: " + tile.q + ", " + tile.r + ", " + tile.s +
                        " : Got number: " + tile.getYieldNumber() +
                        " : and Type: " + tile.getTileType());
        }
    }
}
