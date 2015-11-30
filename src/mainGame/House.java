package mainGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

public class House
{
    private Boolean isCity;

    private Color buildingColor;
    private Circle houseCircle;

    public House(Circle circle, int playerID)
    {
        isCity = false;
        houseCircle = circle;
        buildingColor = PlayerStats.playerColors[playerID];
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
        PlayerStats.point +=1;
    }

    public Circle getHouseCircle()
    {
        return houseCircle;
    }
}