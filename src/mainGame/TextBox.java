import org.newdawn.slick.Font;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.ShapeRenderer;

public class TextBox {

    private float x;
    private float y;
    private float width;
    private String text;
    private Font font;
    private Rectangle rectangle;

    private String drawText;

    public TextBox(float x, float y, float width, String text, Font font, float margin)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.text = text;
        this.font = font;

        // Break the text into words
        String[] wordArray = text.split(" ");
        drawText = "";
        String curText = "";
        for (int i = 0; i < wordArray.length; i++)
        {
            if (font.getWidth(curText + " " + wordArray[i]) > width)
            {
                drawText += "\n";
                curText = "";
            }

            drawText += wordArray[i] + " ";
            curText += wordArray[i] + " ";
        }

        rectangle = new Rectangle(x-margin, y-margin, width + (margin * 2), font.getHeight(drawText) + (margin * 2));
    }

    public void draw()
    {
        font.drawString(x, y, drawText);
        ShapeRenderer.draw(rectangle);
    }
}