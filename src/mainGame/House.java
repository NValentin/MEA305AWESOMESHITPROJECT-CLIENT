package mainGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

public class House
{
    public Boolean getIsCity()
    {
        return isCity;
    }

    private Boolean isCity;
    private int playerID;
    private Color buildingColor;
    private Circle houseCircle;

    public House(Circle circle, int playerID)
    {
        isCity = false;
        this.playerID = playerID;
        houseCircle = circle;
        buildingColor = PlayerStats.playerColors[playerID];
        if (playerID == PlayerStats.ID)
            PlayerStats.point +=1;

    }

    public void render(Graphics g)
    {
        g.setColor(buildingColor);
        g.texture(houseCircle, getTexture(), true);
        g.setColor(Color.white);
    }

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

    public void upgradeHouse()
    {
        isCity = true;
        if (playerID == PlayerStats.ID)
            PlayerStats.point +=1;
    }

    public int getPlayerID()
    {
        return playerID;
    }

    public Circle getHouseCircle()
    {
        return houseCircle;
    }
}