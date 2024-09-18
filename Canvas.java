import java.util.Vector;

public class Canvas {
    ViewPanel viewpanel;
    double width, height;
    Vector<Point2D> content;

    public Canvas (double[] canv_dimensions, double[] viewpanel_dimensions) {
        this.viewpanel = new ViewPanel(viewpanel_dimensions);
        this.width = canv_dimensions[0];
        this.height = canv_dimensions[1];
        this.content = new Vector<> ();
    }

    public Vector<Point2D> renderCanvas (Scene scene) {
        this.content = new Vector<> ();
        for (Point3D point : scene.points) {
            this.content.add(this.viewpanel.projecPoint2D(point));
        }
        return this.content;
    }
}
