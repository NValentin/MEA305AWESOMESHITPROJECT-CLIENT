package mainGame;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public class House
{

    private Boolean isCity;
    private Color buildingColor;
    private Point centerPoint;

    public House(float centerPointX, float centerPointY, Color buildingColor)
    {
        isCity = false;
        centerPoint = new Point(centerPointX, centerPointY);
        this.buildingColor = buildingColor;
    }

    public void render(Graphics g)
    {
        Circle houseC = new Circle(centerPoint.getX(), centerPoint.getCenterY(), 10);
        g.texture(houseC, Texture.doge, true);
    }
}