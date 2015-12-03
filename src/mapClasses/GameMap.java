package mapClasses;

import Network.Network;
import mainGame.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.*;

/**
 * The GameMap class is more or less doing everything related to maintaining the in-game map. This includes
 * updating objects in the map and rendering them, as well as keeping track of where houses and roads are, as well as
 * where they can be built. This means, also what has to be sent to the server, and buildings that has been
 * received from it.
 */
public class GameMap
{
    private static Layout mapLayout; //Instance of the layout class for the map.
    private ArrayList<Tile> map; //ArrayList containing all the tiles in the map.

    private Circle[] housePlots; //An array of all potential plots houses can be built on.
    private ArrayList<House> houses; //An array list of all built houses.

    private int houseRadius; //Since all houses are of a Circle shape, it has a radius.

    private Line[] roadPlots; //Array containing all possible road plots.
    private ArrayList<Road> roads; //ArrayList containing all built roads.

    private Circle[] thiefPositions; //Array of all potential thief positions.
    private static boolean moveThief = false; // boolean that should be set true, if client rolled a 7.

    public static int[] serializedHouse = new int[]{0, 0}; //int array to "pack" indexpos in housePlots, and PlayerID
    public static int[] deSerializedHouse = new int[]{0, 0}; //int array to "pack" indexpos in housePlots, and PlayerID

    public static int[] serializedRoad = new int[]{0, 0}; //int array to "pack" indexpos in housePlots, and PlayerID
    public static int[] deSerializedRoad = new int[]{0, 0}; //int array to "pack" indexpos in housePlots, and PlayerID

    public static int serializedCity = 0; //Int representing indexPos in houses that has to be upgraded to a city
    public static int deSerializedCity = 0; //Int representing indexPos in houses that has to be upgraded to a city

    //Build buttons as they are in the GUI_Overlay, becomes true depending on which build action a player press.
    public static boolean[] build_buttons = new boolean[]{false, false, false, false};

    private Thief thief; //Instace of the thief.

    //boolean becomes true when player first builds a house (initial requirement for building roads)
    private boolean hasHouse = false;

    public GameMap()
    {
    }

    /**
     * For issues regarding Slick2D and how it runs through code, GameMapGameMap is the "actual" constructor,
     * but GameMap() initialized all variables so they can be received from the server.
     */
    public void GameMapGameMap()
    {
        int mapTileSize = 55;
        houseRadius = 10;
        buildMap(mapTileSize, Layout.pointy);
        findHousePlots();
        findRoadPlots();
        findThiefPlots();

        houses = new ArrayList<>();
        roads = new ArrayList<>();
    }

    /**
     * Method that builds the map, setting orientation and size of the tiles, as well as how the map should look,
     * in this case it will build as a larger Hexagon, that represents the Catan map. Is also calls the
     * assignVariables methods depending on if its connected to a server or not.
     *
     * @param tileSize    determines the pixel-size of each tile.
     * @param orientation determines the orientation, (will rotate the map 90 degrees from pointy to flat)
     */
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
        if (!Network.isConnected)
        {
            assignTileVariablesFromLocal();
        } else
        {
            assignTileVariablesFromServer();
        }
    }

    /**
     * Method to find and add all house plots to the housePlots array, should be 54 in the case of this map.
     */
    private void findHousePlots() //should be 54
    {
        housePlots = new Circle[54];
        int counter = 1;

        for (Tile tile : map)
            if (!(Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3))
            {
                ArrayList<Point> centerPoints = Layout.polygonCorners(mapLayout, tile);

                for (Point point : centerPoints)
                {
                    Circle tmpCircle = new Circle(point.getX(), point.getY(), houseRadius);
                    if (housePlots[0] == null)
                    {
                        housePlots[0] = tmpCircle;
                    } else
                    {
                        boolean canAdd = true;
                        for (int i = 0; i < housePlots.length; i++)
                        {
                            if (housePlots[i] != null && housePlots[i].contains(tmpCircle.getCenterX(), tmpCircle.getCenterY()))
                                canAdd = false;
                        }
                        if (canAdd)
                        {
                            housePlots[counter] = tmpCircle;
                            counter++;
                        }
                    }
                }
            }
    }

    /**
     * Method to find and add all road plots to the roadPlots array, should be 72 in the case of this map.
     */
    private void findRoadPlots() //should be 72
    {
        roadPlots = new Line[72];
        int counter = 0;

        for (Tile tile : map)
        {
            if (!(Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3))
            {
                ArrayList<Point> tmpPlots = Layout.polygonCorners(mapLayout, tile);
                for (int i = 0; i < tmpPlots.size(); i++)
                {
                    int iPlusOne = (i == tmpPlots.size() - 1) ? (-5) : 1;

                    Line l = new Line(tmpPlots.get(i).getX(), tmpPlots.get(i).getY(),
                            tmpPlots.get(i + iPlusOne).getX(), tmpPlots.get(i + iPlusOne).getY());

                    if (roadPlots[0] == null)
                        roadPlots[0] = l;
                    else
                    {
                        boolean canAdd = true;
                        for (int j = 0; j < roadPlots.length; j++)
                        {
                            if (roadPlots[j] != null && roadPlots[j].intersects
                                    (new Circle(l.getCenterX(), l.getCenterY(), 3)))
                            {
                                canAdd = false;
                            }
                        }
                        if (canAdd)
                        {
                            roadPlots[counter] = l;
                            counter++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Finds and adds all possible thief positions, it finds them based on the center point of the tiles, that is not
     * water.
     */
    private void findThiefPlots() //Should be 19
    {
        thiefPositions = new Circle[19];
        int counter = 0;
        for (int i = 0; i < map.size(); i++)
            if (Math.abs(map.get(i).q) != 3 && Math.abs(map.get(i).r) != 3 && Math.abs(map.get(i).s) == 3)
            {
                thiefPositions[counter] = (new Circle(Layout.hexToPixel(mapLayout, map.get(i)).getX(),
                        Layout.hexToPixel(mapLayout, map.get(i)).getY(), 20));
                counter++;
            }
    }

    /**
     * adds a house to the houses arraylist, and calls the remove plot methods.
     *
     * @param indexPos the indexPosition of the plot in housePlots that should be built as a house.
     * @param playerID the ID of the player that built the house.
     */
    public void addHouse(int indexPos, int playerID)
    {
        House tmpHouse = new House(housePlots[indexPos], playerID);
        houses.add(tmpHouse);

        removeHouseNeighborPlots(tmpHouse);
        removeHousePlot(indexPos);
    }

    /**
     * add a road to the roads arraylist, and set the plot it built it on to null, to prevent others from building
     * on top of it.
     *
     * @param indexPos indexPosition of the line in roadPlots it has to built the road as.
     * @param playerID ID of the player that built the road.
     */
    public void addRoad(int indexPos, int playerID)
    {
        roads.add(new Road(roadPlots[indexPos], playerID));
        roadPlots[indexPos] = null;
    }

    /**
     * a method to assign variables to the map, if client is not connected to the server. This is used for testing
     * and debugging without having to be connected to a server.
     */
    private void assignTileVariablesFromLocal()
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

        int counter = 0;
        for (Tile tile : map)
        {
            if (Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3)
            {
                tile.setTileType("Water");


                counter++;
            } else
            {
                String type;

                if (tile.getTileType().equalsIgnoreCase("Default"))
                {
                    if (!listOfTileTypes.isEmpty())
                    {
                        type = listOfTileTypes.remove(listOfTileTypes.size() - 1);
                        tile.setTileType(type);
                        if (tile.getTileType().matches("Desert"))
                        {
                            thief = new Thief(new Point(Layout.hexToPixel(mapLayout, tile).getX(), Layout.hexToPixel(mapLayout, tile).getY()));
                            tile.hasThief = true;
                        }
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

    /**
     * The actual method that assigns variables when the game is played as intended, this receives a shuffled
     * ArrayList from the server and uses it to fill up the map with the proper variables.
     */
    private void assignTileVariablesFromServer()
    {
        ArrayList<String> listOfTileTypes = Network.serverListOfTypes;
        ArrayList<Integer> listOfYieldNumbers = Network.serverListOfYieldNumbers;

        for (Tile tile : map)
        {
            if (Math.abs(tile.q) == 3 || Math.abs(tile.r) == 3 || Math.abs(tile.s) == 3)
            {
                tile.setTileType("Water");
            } else
            {
                String type;

                if (tile.getTileType().equalsIgnoreCase("Default"))
                {
                    if (!listOfTileTypes.isEmpty())
                    {
                        type = listOfTileTypes.remove(listOfTileTypes.size() - 1);
                        tile.setTileType(type);
                        if (tile.getTileType().matches("Desert"))
                        {
                            thief = new Thief(new Point(Layout.hexToPixel(mapLayout, tile).getX(), Layout.hexToPixel(mapLayout, tile).getY()));
                            tile.hasThief = true;
                        }
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

    /**
     * Method for determining all tiles that yields resources depending on diceRoll.
     *
     * @param diceRoll takes an int, diceRoll, that should be from 2-12.
     * @return returns an ArrayList of all the tiles in map, that will yields resources from the diceRoll.
     * @see ArrayList
     */
    public ArrayList<Tile> tilesYieldingResource(int diceRoll)
    {
        ArrayList<Tile> resourceYieldingTiles = new ArrayList<>();
        for (Tile tile : map)
        {
            if (tile.getYieldNumber() == diceRoll)
                resourceYieldingTiles.add(tile);
        }
        return resourceYieldingTiles;
    }

    public void addResources(int diceRoll)
    {
        ArrayList<Tile> tiles = tilesYieldingResource(diceRoll);
        for (Tile tile : tiles)
            for (Point point : Layout.polygonCorners(mapLayout, tile))
                if (!houses.isEmpty())
                    for (House house : houses)
                        if (house.getHouseCircle().contains(point) && house.getPlayerID() == PlayerStats.ID)
                        {
                            int numOfResource = (house.getIsCity()) ? 2 : 1;
                            if (tile.getTileType().matches("Lumber"))
                                State_PlayingWindow.currentResources[2] += numOfResource;

                            if (tile.getTileType().matches("Wool"))
                                State_PlayingWindow.currentResources[0] += numOfResource;

                            if (tile.getTileType().matches("Brick"))
                                State_PlayingWindow.currentResources[3] += numOfResource;

                            if (tile.getTileType().matches("Ore"))
                                State_PlayingWindow.currentResources[1] += numOfResource;

                            if (tile.getTileType().matches("Grain"))
                                State_PlayingWindow.currentResources[4] += numOfResource;

                        }
    }

    /**
     * sets an object to null, in HousePlot array
     *
     * @param indexPos position in the array it sets as null
     */
    private void removeHousePlot(int indexPos)
    {
        if (housePlots[indexPos] != null)
            housePlots[indexPos] = null;
    }

    /**
     * Set neighbouring housePlots to the built house, to null, because of the minimum distance requirement
     * present in Catan.
     *
     * @param house the house that has just been built in addHouse().
     */
    private void removeHouseNeighborPlots(House house)
    {
        for (int i = 0; i < housePlots.length; i++)
            if (housePlots[i] != null)
            {
                int distance = (int)
                        new Vector2f(house.getHouseCircle().getCenterX(), house.getHouseCircle().getCenterY())
                                .distance(new Vector2f(housePlots[i].getCenterX(), housePlots[i].getCenterY()));
                if (distance < 65)
                    housePlots[i] = null;
            }
    }

    /**
     * The update method runs continuously through Slick2D and the Update method in PlayerWindowState.
     * It checks for updates to variables that potentially could change, i.e. new house/road/city, and if thief
     * has to be moved. Also has to redundancy checks for whether the player should actually be able to do a
     * certain action, like build if it's not your turn.
     *
     * @param gc a slick2D thing.
     * @see GameContainer and Slick2D javadoc
     */
    public void update(GameContainer gc)
    {
        while (moveThief)
            for (Circle circle : thiefPositions)
                if (circle.contains(Mouse.getX(), Main.ScreenHeight - Mouse.getY()) && gc.getInput().isKeyPressed(0))
                {
                    thief.moveThief(new Point(circle.getCenterX(), circle.getCenterY()));
                    moveThief = false;
                }

        if (deSerializedHouse[1] != 0)
        {
            deSerializeHouse();
            deSerializedHouse[1] = 0;
        }
        if (deSerializedRoad[1] != 0)
        {
            deSerializeRoad();
            deSerializedRoad[1] = 0;
        }

        if (deSerializedCity != 0)
        {
            deSerializeCity();
            deSerializedCity = 0;
        }

        if (PlayerStats.playerturn[PlayerStats.ID - 1] && build_buttons[1])
            for (int i = 0; i < housePlots.length; i++)
                if (checkMouseOverHousePlot(i) && gc.getInput().isMousePressed(0))
                {
                    serializeHouse(i);
                    if (!Network.isConnected)
                    {
                        addHouse(i, 1);
                    }
                    hasHouse = true;
                    build_buttons[1] = false;
                }

        if (PlayerStats.playerturn[PlayerStats.ID - 1] && build_buttons[0] && hasHouse)
            for (int i = 0; i < roadPlots.length; i++)
                if (checkOverRoadPlot(i) && gc.getInput().isMousePressed(0))
                {
                    serializeRoad(i);
                    if (!Network.isConnected)
                    {
                        addRoad(i, 1);
                    }
                    build_buttons[0] = false;
                }

        if (PlayerStats.playerturn[PlayerStats.ID - 1] && build_buttons[2])
            for (int i = 0; i < houses.size(); i++)
                if (checkMouseOverHouse(i) && gc.getInput().isMousePressed(0)
                        && houses.get(i).getPlayerID() == PlayerStats.ID)
                {
                    serializeCity(i);
                    if (!Network.isConnected)
                        houses.get(i).upgradeHouse();

                    build_buttons[3] = false;
                }
    }

    /**
     * the method for drawing the tiles, and the thief on the tile that has the thief.
     *
     * @param g Graphics component
     * @throws SlickException
     * @see Graphics and Slick2D
     */
    private void drawTiles(Graphics g) throws SlickException
    {
        for (Tile tile : map)
        {
            Polygon tmpPoly = new Polygon();
            ArrayList<Point> tmpPoints = Layout.polygonCorners(mapLayout, tile);
            for (Point point : tmpPoints)
                tmpPoly.addPoint(point.getX(), point.getY());

            Image tmpTexture = tile.returnTextureByType();
            tmpTexture.setFilter(Image.FILTER_LINEAR);
            g.texture(tmpPoly, tmpTexture, true);

            if (tile.getYieldNumber() != 0)
            {
                g.drawString(String.valueOf(tile.getYieldNumber()),
                        Layout.hexToPixel(mapLayout, tile).getX() - 6,
                        Layout.hexToPixel(mapLayout, tile).getY() - 8
                );
            }
            tile.hasThief = tmpPoly.contains(thief.getPoint());
        }
    }

    /**
     * method for drawing the circles in housePlots, and checking to only draw those that should be drawn.
     *
     * @param g Slick2D graphics component
     * @throws SlickException
     * @see Graphics and Slick2D javadoc
     */
    private void drawHousePlots(Graphics g) throws SlickException
    {
        if (PlayerStats.playerturn[PlayerStats.ID - 1] && build_buttons[1])
        {
            if (!hasHouse)
            {
                for (int i = 0; i < housePlots.length; i++)
                {
                    if (housePlots[i] != null && checkMouseOverHousePlot(i))
                        g.fill(housePlots[i]);
                    else if (housePlots[i] != null)
                        g.draw(housePlots[i]);

                }
            } else
            {
                for (int i = 0; i < housePlots.length; i++)
                    if (!roads.isEmpty() && housePlots[i] != null)
                        for (Road road : roads)
                            if (road.getPlayerID() == PlayerStats.ID)
                            {
                                Line roadLine = road.getRoadLine();
                                Circle c1 = new Circle(roadLine.getX1(), roadLine.getY1(), 5);
                                Circle c2 = new Circle(roadLine.getX2(), roadLine.getY2(), 5);
                                if (housePlots[i].intersects(c1) || housePlots[i].intersects(c2))
                                {
                                    if (checkMouseOverHousePlot(i))
                                        g.fill(housePlots[i]);
                                    else
                                        g.draw(housePlots[i]);
                                }
                            }
            }
        }
    }

    /**
     * method for drawing the lines in roadPlots, and checking to only draw those that should be drawn.
     *
     * @param g Slick2D Graphics component
     * @throws SlickException
     * @see Graphics and Slick2D javadoc
     */
    private void drawRoadPlots(Graphics g) throws SlickException
    {
        if (PlayerStats.playerturn[PlayerStats.ID - 1] && build_buttons[0] && hasHouse)
        {
            for (int i = 0; i < roadPlots.length; i++)
            {
                if (!houses.isEmpty() && roadPlots[i] != null)
                {
                    for (House house : houses)
                    {
                        if (house.getHouseCircle().intersects(roadPlots[i]) && house.getPlayerID() == PlayerStats.ID)
                        {
                            if (checkOverRoadPlot(i))
                            {
                                g.setLineWidth(8);
                                g.draw(roadPlots[i]);
                            } else if (roadPlots[i] != null)
                            {
                                g.setLineWidth(3);
                                g.draw(roadPlots[i]);
                            }
                        }
                    }
                }

                if (!roads.isEmpty() && roadPlots[i] != null)
                    for (Road road : roads)
                        if (road.getPlayerID() == PlayerStats.ID)
                        {
                            Line roadLine = road.getRoadLine();
                            Circle c1 = new Circle(roadLine.getX1(), roadLine.getY1(), 5);
                            Circle c2 = new Circle(roadLine.getX2(), roadLine.getY2(), 5);
                            if (c1.intersects(roadPlots[i]) || c2.intersects(roadPlots[i]))
                                if (checkOverRoadPlot(i))
                                {
                                    g.setLineWidth(8);
                                    g.draw(roadPlots[i]);
                                } else if (roadPlots[i] != null)
                                {
                                    g.setLineWidth(3);
                                    g.draw(roadPlots[i]);
                                }
                        }

            }
        }
    }

    /**
     * The main render method, it will call drawTiles(), drawHousePlots(), and drawRoadPlots(), along with that
     * does not take up a lot of checks.
     *
     * @param g slick2D graphics component
     * @throws SlickException
     * @see Graphics and Slick2d javadoc
     */
    public void render(Graphics g) throws SlickException
    {
        drawTiles(g);
        drawHousePlots(g);
        drawRoadPlots(g);

        thief.render(g);

        if (!roads.isEmpty())
            for (Road road : roads)
            {
                road.render(g);
            }

        if (!houses.isEmpty())
            for (House house : houses)
            {
                house.render(g);
            }

        if (moveThief)
            for (Circle circle : thiefPositions)
                g.draw(circle);
    }

    /**
     * Sets the variables in serializedHouse array, this is the array that is sent to the server, if its second
     * variable is not 0.
     *
     * @param housePlotPos int that represents the index position in housePlots array, that a player build on.
     */
    private void serializeHouse(int housePlotPos)
    {
        serializedHouse[0] = housePlotPos;
        serializedHouse[1] = PlayerStats.ID;
    }

    /**
     * "decodes" what the client has received from the server, and builds the house on the proper plot, and adds
     * it to the houses ArrayList.
     */
    public void deSerializeHouse()
    {
        if (housePlots[deSerializedHouse[1]] != null)
            addHouse(deSerializedHouse[0], deSerializedHouse[1]);
    }

    /**
     * takes the indexPos in the houses ArrayList a player wants to upgrade to a city
     *
     * @param indexPos the int representing the position in houses
     */
    public void serializeCity(int indexPos)
    {
        serializedCity = indexPos;
    }

    /**
     * "decodes" the received int from the server and upgrades the proper house to a city.
     */
    public void deSerializeCity()
    {
        houses.get(deSerializedCity).upgradeHouse();
    }

    /**
     * sets the variables in serializedRoad, which will be sent to the server if there are changes.
     * sets the indexPosition, and playerID
     *
     * @param roadPlotPos int that represents the indexPosition in roadPlots a player wants to place a road on.
     */
    private void serializeRoad(int roadPlotPos)
    {
        serializedRoad[0] = roadPlotPos;
        serializedRoad[1] = PlayerStats.ID;
    }

    /**
     * "decodes" the array received from the server, and adds the proper road to the roads arrayList.
     */
    public void deSerializeRoad()
    {
        if (roadPlots[deSerializedRoad[1]] != null)
            addRoad(deSerializedRoad[0], deSerializedRoad[1]);
    }

    /**
     * checks if the mouse position is within a circle in housePlots, dependant on the indexPos param.
     *
     * @param indexPos the int representing the indexPos in housePlots.
     * @return returns true/false, true if mouse pos is inside, and vice versa.
     */
    private boolean checkMouseOverHousePlot(int indexPos)
    {
        return housePlots[indexPos] != null &&
                housePlots[indexPos].contains(Mouse.getX(), Main.ScreenHeight - Mouse.getY());
    }

    /**
     * checks if the mouse position is within a circle in houses, dependant on the indexPos param.
     *
     * @param indexPos the int representing the indexPos in houses.
     * @return returns true/false, true if mouse pos is inside, and vice versa.
     */
    private boolean checkMouseOverHouse(int indexPos)
    {
        return houses.get(indexPos).getHouseCircle().contains(Mouse.getX(), Main.ScreenHeight - Mouse.getY());
    }

    /**
     * checks if a circle made from the mousePosition intersects a line in roadPlots, dependant on the indexPos param.
     *
     * @param indexPos the int representing the indexPos in roadPlots.
     * @return returns true/false, true if mouse pos is inside, and vice versa.
     */
    private boolean checkOverRoadPlot(int indexPos)
    {
        Circle mouseC = new Circle(Mouse.getX(), Main.ScreenHeight - Mouse.getY(), 5);
        return roadPlots[indexPos] != null &&
                roadPlots[indexPos].intersects(mouseC);
    }

    /**
     * a method for debugging the information on every tile, if there is a wrong type or number, or weird positions.
     * this provides all information about a tile. (except for its pixel coordinates, they don't know those)
     */
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
