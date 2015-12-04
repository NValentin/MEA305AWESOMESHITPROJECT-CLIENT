package mainGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

/**
 * Road class represents roads in the game
 */
public class Road
{
    //Variables belonging to a road
    private Line roadLine;
    private int playerID;
    private Color roadColor;

    /**
     * Constructor of a road
     * @param roadLine Line representing the road
     * @param playerID int representing the ID of the player that built the road
     */
    public Road(Line roadLine, int playerID)
    {
        //Instantiate a road's variables
        this.playerID = playerID;
        this.roadLine = roadLine;
        roadColor = PlayerStats.playerColors[playerID];
    }

    /**
     * A roads render class, draws the road
     * @param g Graphics component
     *          @see Graphics and Slick2D javadoc
     */
    public void render(Graphics g)
    {
        g.setColor(roadColor);
        g.setLineWidth(8);
        g.draw(roadLine);
        g.setColor(Color.white);
    }

    /**
     * Line representing a road's line
     * @return Line
     * @see Line
     */
    public Line getRoadLine()
    {
        return roadLine;
    }

    /**
     * Int representing the ID of the player that built the houses
     * @return int
     */
    public int getPlayerID()
    {
        return playerID;
    }
}
