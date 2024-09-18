import java.util.Vector;

public class main {
    public static void main(String[] args) {
        Scene scene = new Scene();
        Canvas canvas = new Canvas(new double[]{1920, 1080}, new double[]{25, 25});
        final Point3D point_anchor = new Point3D(0, 0, 0);

        scene.addPoint(new Point3D(5, 5, 5));

        for (int i = 0; i < 9; i++) { 
            for (int j = 0; j < scene.points.size(); j++) {
                scene.points.set(j, scene.rotate_x(scene.points.elementAt(j), point_anchor, Math.PI/4));
            }

            Vector<Point2D> render = canvas.renderCanvas(scene);
            for ( Point2D point : render ) {
                System.out.print(point.x + ", " + point.y + "\n");
            }
        }
    }
}