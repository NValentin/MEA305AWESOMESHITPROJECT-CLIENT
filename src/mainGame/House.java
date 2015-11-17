package mainGame;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public class House
{

    private Boolean isCity;
    private Color buildingColor;
    private Circle houseCircle;

    public House(Circle circle, Color buildingColor)
    {
        isCity = false;
        houseCircle = circle;
        this.buildingColor = buildingColor;
    }

    public void render(Graphics g)
    {
        g.setColor(buildingColor);
        g.texture(houseCircle, this.getTexture(), true);
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
}