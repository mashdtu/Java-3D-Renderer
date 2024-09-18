public class Vector3D {
    public double x, y, z, r, theta, phi;
    public Vector3D (Point3D p_start, Point3D p_end) {
        this.x = p_end.x - p_start.x;
        this.y = p_end.y - p_start.y;
        this.z = p_end.z - p_start.z;
        this.r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

        if (this.r == 0) {
            this.theta = 0;
        } else {
            this.theta = Math.acos(this.z / this.r);
        }

        if (this.x == 0) {
            this.phi = Math.PI / 2;
        } else {
            this.phi = Math.atan(this.y / this.x);
        }
    }
}
