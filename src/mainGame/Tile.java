package mainGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

/**
 * Created by Niels on 01-11-2015.
 */
public class Tile extends Polygon
{
    private char tileType;

    private float centerX, centerY;

    private int tileSize = 50;
    private int tileHeight = tileSize * 2;
    private float tileWidth = (float) Math.sqrt(3) / 2 * tileHeight;

    Tile(float centerX, float centerY, char tileType)
    {
        this.tileType = tileType;

        this.centerX = centerX;
        this.centerY = centerY;
        createCorners();
    }

    private void createCorners()
    {
        for (int i = 0; i < 6; i++)
        {
            float angle_deg = 60 * i + 30;
            float angle_rad = (float) Math.PI / 180 * angle_deg;

            this.addPoint((int) (centerX + tileSize * Math.cos(angle_rad)),
                    (int) (centerY + tileSize * Math.sin(angle_rad)));
        }
    }

    public Image returnTextureByType()
    {
        Image tmpTexture;
        switch (tileType)
        {
            case ('G'):
                tmpTexture = PlayingWindow.textures.getGrassTex();
                break;
            case ('R'):
                tmpTexture = PlayingWindow.textures.getDoge();
                break;
            default:
                tmpTexture = PlayingWindow.textures.getButt();
                break;
        }

        return tmpTexture;
    }
}
