import java.util.Vector;

public class Scene {
    Vector<Point3D> points;
    public Scene () {
        this.points = new Vector<> ();
    }

    public void addPoint (Point3D point3d) {
        this.points.add(point3d);
    }

    public Point3D multiplyMatrices (Point3D vec, double[][] matrix) {
        double x = vec.x * matrix[0][0] + vec.y * matrix[1][0] + vec.z * matrix[2][0];
        double y = vec.x * matrix[0][1] + vec.y * matrix[1][1] + vec.z * matrix[2][1];
        double z = vec.x * matrix[0][2] + vec.y * matrix[1][2] + vec.z * matrix[2][2];
        return new Point3D (x, y, z);
    }

    public Point3D translate (Point3D point, double[] anchor) {
        double x = point.x - anchor[0];
        double y = point.y - anchor[1];
        double z = point.z - anchor[2];
        return new Point3D (x, y, z);
    }

    public Point3D rotate_x (Point3D point, Point3D anchor, double theta) {
        double[][] rotation_matrix = new double[][] {
            {1.0, 0.0, 0.0}, {0.0, Math.cos(theta), -Math.sin(theta)}, {0.0, Math.sin(theta), Math.cos(theta)}
        };
        return this.rotate(point, anchor, rotation_matrix);
    }
    
    public Point3D rotate_y (Point3D point, Point3D anchor, double theta) {
        double[][] rotation_matrix = new double[][] {
            {Math.cos(theta), 0.0, Math.sin(theta)}, {0.0, 1.0, 0.0}, {-Math.sin(theta), 0.0, Math.cos(theta)}
        };
        return this.rotate(point, anchor, rotation_matrix);
    }
    
    public Point3D rotate_z (Point3D point, Point3D anchor, double theta) {
        double[][] rotation_matrix = new double[][] {
            {Math.cos(theta), -Math.sin(theta), 0.0}, {Math.sin(theta), Math.cos(theta), 0.0}, {0.0, 0.0, 1.0}
        };
        return this.rotate(point, anchor, rotation_matrix);
    }
    
    private Point3D rotate (Point3D point, Point3D anchor, double[][]matr_rot) {
        double[] neg_anchor_values = new double[]{-anchor.x, -anchor.y, -anchor.z};
        double[] pos_anchor_values = new double[]{anchor.x, anchor.y, anchor.z};

        Point3D point_translated = this.translate(point, pos_anchor_values);
        Point3D point_rotated = this.multiplyMatrices(point_translated, matr_rot);

        return this.translate(point_rotated, neg_anchor_values);        
    }
}
