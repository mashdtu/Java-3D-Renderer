import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Vector;
import javax.swing.*;

public class GUI extends JPanel implements  ActionListener {
    Timer t = new Timer(5, this);
    double x = 0;
    double y = 0;
    Scene scene = new Scene();
    Canvas canvas = new Canvas(new double[]{1920, 1080}, new double[]{25, 25});
    final Point3D point_anchor = new Point3D(5, 5, 5);
    private final double n = 5;
    Vector<Point2D> render = new Vector<>();

    public GUI () {
        canvas.viewpanel.moveCamera(-250, -5, 0);

        scene.addPoint(new Point3D(0, 0, 0));
        scene.addPoint(new Point3D(0, 0, 10));
        scene.addPoint(new Point3D(0, 10, 0));
        scene.addPoint(new Point3D(0, 10, 10));
        scene.addPoint(new Point3D(10, 0, 0));
        scene.addPoint(new Point3D(10, 0, 10));
        scene.addPoint(new Point3D(10, 10, 0));
        scene.addPoint(new Point3D(10, 10, 10));
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for ( Point2D point : render ) {
            Ellipse2D circle = new Ellipse2D.Double(point.x, point.y, 10, 10);
            g2.fill(circle);
        }
        t.start();
    }

    public void actionPerformed (ActionEvent e) {
        for (int j = 0; j < scene.points.size(); j++) {
            scene.points.set(j, scene.rotate_z(scene.points.elementAt(j), point_anchor, Math.PI/256));
        }
        render = canvas.renderCanvas(scene);
        for ( Point2D point : render ) {
            point.x = 600 + point.x * n;
            point.y = 300 + point.y * n;
        }
        repaint();
    }
    
}