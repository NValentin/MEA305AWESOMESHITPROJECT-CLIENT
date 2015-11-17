package mainGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

public class Road
{

    Line roadLine;
    Color roadColor;

    public Road(Line roadLine, Color roadColor)
    {
        this.roadLine = roadLine;
        this.roadColor = roadColor;
    }

    public void render(Graphics g)
    {
        g.setColor(roadColor);
        g.setLineWidth(5);
        g.draw(roadLine);
        g.setColor(Color.white);
    }
}
