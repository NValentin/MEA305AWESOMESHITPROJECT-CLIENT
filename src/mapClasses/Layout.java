package mapClasses;

import org.newdawn.slick.geom.Point;

import java.util.ArrayList;

/**
 * Part of DIY Hex-library from Author of: http://www.redblobgames.com/grids/hexagons/
 * Methods are derived directly from library, documentation comments if not.
 */
class Layout
{
    public Layout(Orientation orientation, Point size, Point origin)
    {
        this.orientation = orientation;
        this.size = size;
        this.origin = origin;
    }

    public final Orientation orientation;
    public final Point size;
    public final Point origin;
    static public Orientation pointy = new Orientation(Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0, Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0, 0.5);
    static public Orientation flat = new Orientation(3.0 / 2.0, 0.0, Math.sqrt(3.0) / 2.0, Math.sqrt(3.0), 2.0 / 3.0, 0.0, -1.0 / 3.0, Math.sqrt(3.0) / 3.0, 0.0);

    static public Point hexToPixel(Layout layout, Hex h)
    {
        Orientation M = layout.orientation;
        Point size = layout.size;
        Point origin = layout.origin;
        double x = (M.f0 * h.q + M.f1 * h.r) * size.getCenterX();
        double y = (M.f2 * h.q + M.f3 * h.r) * size.getCenterY();
        return new Point((float) x + origin.getX(), (float) y + origin.getY());
    }

    static public FractionalHex pixelToHex(Layout layout, Point p)
    {
        Orientation M = layout.orientation;
        Point size = layout.size;
        Point origin = layout.origin;
        Point pt = new Point((p.getX() - origin.getY()) / size.getX(), (p.getY() - origin.getX()) / size.getY());
        double q = M.b0 * pt.getX() + M.b1 * pt.getY();
        double r = M.b2 * pt.getX() + M.b3 * pt.getY();
        return new FractionalHex(q, r, -q - r);
    }

    static public Point hexCornerOffset(Layout layout, int corner)
    {
        Orientation M = layout.orientation;
        Point size = layout.size;
        double angle = 2.0 * Math.PI * (corner + M.start_angle) / 6;
        return new Point((float) (size.getX() * Math.cos(angle)), (float) (size.getY() * Math.sin(angle)));
    }

    static public ArrayList<Point> polygonCorners(Layout layout, Hex h)
    {
        ArrayList<Point> corners = new ArrayList<>();

        Point center = Layout.hexToPixel(layout, h);
        for (int i = 0; i < 6; i++)
        {
            Point offset = Layout.hexCornerOffset(layout, i);
            corners.add(new Point(center.getX() + offset.getX(), center.getY() + offset.getY()));
        }
        return corners;
    }
}

