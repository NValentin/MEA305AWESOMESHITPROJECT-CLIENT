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
        this.roadLine = roadLine;
        this.playerID = playerID;
        roadColor = getPlayerColor();
    }

    public void render(Graphics g)
    {
        g.setColor(roadColor);
        g.setLineWidth(5);
        g.draw(roadLine);
        g.setColor(Color.white);
    }
    private Color getPlayerColor()
    {
        Color playerColor = Color.pink;
        switch (playerID)
        {
            case(1):
                playerColor = Color.blue;
                break;
            case(2):
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
