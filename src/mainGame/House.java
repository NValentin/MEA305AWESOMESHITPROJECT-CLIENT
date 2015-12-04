package mainGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

/**
 * The house class is used for representing where players have built houses. They are build from circles received
 * from the housePlots in GameMap
 */
public class House
{
    //Variables
    private Boolean isCity; //bool for if the house has been upgraded to a city
    private int playerID; //the id of the player that built the house
    private Color buildingColor; //the color of the houses, dependant on the playerID
    private Circle houseCircle; //the house circle represents the circle on screen space where it gets drawn

    /**
     * Constructor for the house class, instantiate a new house
     * @param circle the circle representing the house
     * @param playerID the int ID representing the players ID (1-4)
     */
    public House(Circle circle, int playerID)
    {
        //Initialize a new house variables
        isCity = false;
        this.playerID = playerID;
        houseCircle = circle;
        buildingColor = PlayerStats.playerColors[playerID];
        if (playerID == PlayerStats.ID)
            PlayerStats.point +=1;

    }

    /**
     * How a house is rendered, sets its color, textures it, and resets color to default(white)
     * @param g graphics component
     *          @see Graphics and Slick2d javadoc
     */
    public void render(Graphics g)
    {
        g.setColor(buildingColor);
        g.texture(houseCircle, getTexture(), true);
        g.setColor(Color.white);
    }

    /**
     * Method for finding the texture a house should have. Use one if it's a house, another if it's a city.
     * @return Image type
     */
    private Image getTexture()
    {
        if (isCity)
        {
            return Texture.butt;
        } else
        {
            return Texture.doge;
        }
    }

    /**
     * Upgrades a house to a city
     */
    public void upgradeHouse()
    {
        isCity = true;
        if (playerID == PlayerStats.ID)
            PlayerStats.point +=1;
    }

    /**
     * Determines if a house has been upgraded to a city
     * @return Boolean
     */
    public Boolean getIsCity()
    {
        return isCity;
    }

    /**
     * Determines a house's playerID
     * @return int
     */
    public int getPlayerID()
    {
        return playerID;
    }

    /**
     * Determines a house's circle
     * @return Circle
     * @see Circle and Slick2D javadoc
     */
    public Circle getHouseCircle()
    {
        return houseCircle;
    }
}