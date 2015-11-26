package mainGame;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

public class House
{
    private Boolean isCity;

    private int playerID;

    private Color buildingColor;
    private Circle houseCircle;

    public House(Circle circle, int playerID)
    {
        isCity = false;
        houseCircle = circle;
        this.playerID = playerID;
        this.buildingColor = this.getPlayerColor();
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
    }

    public Circle getHouseCircle()
    {
        return houseCircle;
    }

    private Color getPlayerColor()
    {
        Color playerColor = Color.black;
        switch (playerID)
        {
            case (1):
                playerColor = Color.blue;
                break;
            case (2):
                playerColor = Color.red;
                break;
            case (3):
                playerColor = Color.white;
                break;
            case (4):
                playerColor = Color.orange;
                break;
            default:
                break;
        }
        return playerColor;
    }
}