public class ViewPanel {
    public double width, height, camera_distance;
    public double[] rotation, viewpanel_coords;
    public Point3D point3d_camera, point3d_viewpanel;
    public Vector3D vector3d_viewpanel;

    public ViewPanel (double[] dimensions) {
        this.width = dimensions[0];
        this.height = dimensions[1];
        this.rotation = new double[] {0, Math.PI / 2};  // stnd
        this.camera_distance = this.width * 9.0 / 7.0;
        this.point3d_camera = new Point3D(-75, 5, 5);
        this.viewpanel_coords = sph_to_cart(this.camera_distance, this.rotation[0], this.rotation[1]);

        this.point3d_viewpanel = new Point3D(
            this.point3d_camera.x + this.viewpanel_coords[0],
            this.point3d_camera.y + this.viewpanel_coords[1],
            this.point3d_camera.z + this.viewpanel_coords[2]
        );
    
        this.vector3d_viewpanel = new Vector3D(this.point3d_camera, this.point3d_viewpanel);
    }

    public void moveCamera (double x, double y, double z) {
        this.point3d_camera.x += x;
        this.point3d_camera.y += y;
        this.point3d_camera.z += z;

        this.point3d_viewpanel.x += x;
        this.point3d_viewpanel.y += y;
        this.point3d_viewpanel.z += z;
    }

    public void rotateCamera (double[] rot) {
        this.rotation[0] += rot[0];
        this.rotation[1] += rot[1];

        this.viewpanel_coords = sph_to_cart(this.camera_distance, this.rotation[0], this.rotation[1]);

        this.point3d_viewpanel = new Point3D(
            this.point3d_camera.x + this.viewpanel_coords[0],
            this.point3d_camera.y + this.viewpanel_coords[1],
            this.point3d_camera.z + this.viewpanel_coords[2]
        );
    
        this.vector3d_viewpanel = new Vector3D(this.point3d_camera, this.point3d_viewpanel);
    }

    public Point2D projecPoint2D (Point3D point3d) {
        Vector3D vector3D_camera = new Vector3D(this.point3d_camera, point3d);

        double rel_phi = 2 * Math.PI - (vector3D_camera.phi + this.rotation[0]);
        double rel_theta = 2 * Math.PI - (vector3D_camera.theta + this.rotation[1]);

        double angle_x = Math.PI/2 - Math.abs(rel_phi);
        double angle_y = Math.PI/2 - Math.abs(rel_theta);

        double x = this.camera_distance * (Math.sin(rel_phi) / Math.sin(angle_x));
        double y = this.camera_distance * (Math.sin(rel_theta) / Math.sin(angle_y));

        return new Point2D(x * this.width, y * this.height);
    }

    public static double[] sph_to_cart (double r, double theta, double phi) {
        double x = r * Math.sin(theta) * Math.cos(phi);
        double y = r * Math.sin(theta) * Math.sin(phi);
        double z = r * Math.cos(theta);

        return new double[] {x, y , z};
    }

    public static double[] cart_to_sph (double x, double y, double z) {
        double theta, phi;
        double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

        if (r == 0) {
            theta = 0;
        } else {
            theta = Math.acos(z/r);
        }

        if (x == 0) {
            phi = Math.PI/2;
        } else {
            phi = Math.atan(y/x);
        }

        return new double[] {r, theta, phi};
    }
}
