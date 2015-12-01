package mainGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

public class Road
{

    private Line roadLine;
    private int playerID;
    private Color roadColor;

    public Road(Line roadLine, int playerID)
    {
        this.playerID = playerID;
        this.roadLine = roadLine;
        roadColor = PlayerStats.playerColors[playerID];
    }

    public void render(Graphics g)
    {
        g.setColor(roadColor);
        g.setLineWidth(8);
        g.draw(roadLine);
        g.setColor(Color.white);
    }

    public Line getRoadLine()
    {
        return roadLine;
    }

    public int getPlayerID()
    {
        return playerID;
    }
}
