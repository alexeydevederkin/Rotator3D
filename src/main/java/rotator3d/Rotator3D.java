package rotator3d;

import java.util.Locale;

class Quaternion {
    private static final double EPSILON = 1e-9;

    double r, i, j, k;

    static Quaternion fromVector(double x, double y, double z, double angle_degrees) {
        double angle_radians = angle_degrees * Math.PI / 180.0;
        double cos_a = Math.cos(angle_radians / 2.0);
        double sin_a = Math.sin(angle_radians / 2.0);
        return new Quaternion(cos_a, x * sin_a, y * sin_a, z * sin_a);
    }

    static Quaternion fromUnitVector(double x, double y, double z, double angle_degrees) {
        // normalize vector (make length == 1)
        double mag = Math.sqrt(x*x + y*y + z*z);
        if (mag < EPSILON) {
            throw new IllegalArgumentException("Axis length == 0. Rotation is undefined.");
        }
        x /= mag;
        y /= mag;
        z /= mag;

        // and make quaternion
        double angle_radians = angle_degrees * Math.PI / 180.0;
        double cos_a = Math.cos(angle_radians / 2.0);
        double sin_a = Math.sin(angle_radians / 2.0);
        return new Quaternion(cos_a, x * sin_a, y * sin_a, z * sin_a);
    }

    Quaternion(double r, double i, double j, double k) {
        this.r = r;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    double magnitude() {
        return Math.sqrt(r*r + i*i + j*j + k*k);
    }

    Quaternion inverse() {
        double mag = this.magnitude();
        if (mag < EPSILON) {
            throw new IllegalArgumentException("Quaternion magnitude == 0. Inverse quaternion is undefined.");
        }
        return new Quaternion(r/mag, -i/mag, -j/mag, -k/mag);
    }

    public Quaternion unit() {
        double mag = this.magnitude();
        if (mag < EPSILON) {
            throw new IllegalArgumentException("Quaternion magnitude == 0. Unit quaternion is undefined.");
        }
        return new Quaternion(r/mag, i/mag, j/mag, k/mag);
    }

    Quaternion multiply(Quaternion q) {
        double r = this.r * q.r - this.i * q.i - this.j * q.j - this.k * q.k;
        double i = this.r * q.i + this.i * q.r + this.j * q.k - this.k * q.j;
        double j = this.r * q.j - this.i * q.k + this.j * q.r + this.k * q.i;
        double k = this.r * q.k + this.i * q.j - this.j * q.i + this.k * q.r;
        return new Quaternion(r, i, j, k);
    }
}


public class Rotator3D {

    /*
     *  Rotation in 3D space with quaternions
     *
     *  (x, y, z) - point to rotate
     *  (0, 0, 0) -> (axis_x, axis_y, axis_z) - axis, around which to rotate
     *  angle_degrees - angle of rotation around the axis (clockwise)
     *
     *
     *  Rotation with quaternions:
     *  v' = q * v * q^-1
     *  v'   - resulting quaternion after rotation
     *  v    - quaternion of initial point = Q(0, x*i, y*j, z*k),
     *           where (x, y, z) - initial point in 3D space
     *  q    - quaternion Q(cos(θ/2), sin(θ/2)*ax*i, sin(θ/2)*ay*j, sin(θ/2)*az*k),
     *           where (ax, ay, az) - unit rotation axis, θ - rotation angle
     *  q^-1 - inverted quaternion q
     */
    public double[] rotate3dQuaternion (double x, double y, double z,
                                        double axis_x, double axis_y, double axis_z,
                                        double angle_degrees) {

        double[] x_y_z = new double[3];

        Quaternion v = new Quaternion(0, x, y, z);
        Quaternion q = Quaternion.fromUnitVector(axis_x, axis_y, axis_z, angle_degrees);
        Quaternion q_inv = q.inverse();

        // v' = q * v * q^-1
        Quaternion v_res = q.multiply(v.multiply(q_inv));

        // Getting rotated point (x, y, z) from resulting quaternion
        x_y_z[0] = v_res.i;     // x
        x_y_z[1] = v_res.j;     // y
        x_y_z[2] = v_res.k;     // z

        return x_y_z;
    }

    /*
     *  Rotation in 3D space with quaternions. Optimized computations.
     *
     *  (init_x, init_y, init_z) - point to rotate
     *  (0, 0, 0) -> (axis_x, axis_y, axis_z) - axis, around which to rotate
     *  angle_degrees - angle of rotation around the axis (clockwise)
     */
    public double[] rotate3d (double init_x, double init_y, double init_z,
                              double axis_x, double axis_y, double axis_z,
                              double angle_degrees) {

        final double epsilon = 1e-9;

        double[] x_y_z = new double[3];

        // Axis normalization (make vector's length == 1)
        double mag = Math.sqrt(axis_x * axis_x + axis_y * axis_y + axis_z * axis_z);

        if (mag < epsilon) {
            throw new IllegalArgumentException("Axis length == 0. Rotation is undefined.");
        }

        axis_x /= mag;
        axis_y /= mag;
        axis_z /= mag;

        double angle_radians = angle_degrees * Math.PI / 180.0;
        double cos_a = Math.cos(angle_radians / 2.0);
        double sin_a = Math.sin(angle_radians / 2.0);

        // main.java.rotator3d.Quaternion components
        double w = cos_a;
        double x = axis_x * sin_a;
        double y = axis_y * sin_a;
        double z = axis_z * sin_a;

        // main.java.rotator3d.Quaternion-derived rotation matrix
        x_y_z[0] = init_x * (1 - 2*y*y - 2*z*z) + init_y * (2*x*y - 2*w*z) + init_z * (2*x*z + 2*w*y);
        x_y_z[1] = init_x * (2*x*y + 2*w*z) + init_y * (1 - 2*x*x - 2*z*z) + init_z * (2*y*z - 2*w*x);
        x_y_z[2] = init_x * (2*x*z - 2*w*y) + init_y * (2*y*z + 2*w*x) + init_z * (1 - 2*x*x - 2*y*y);

        return x_y_z;
    }


    public void run() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 90;
        double[] x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 0.0000, y = 1.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 0; y = 1; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 10;
        angle_degrees = 180;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = -0.0000, y = -1.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 1; y = 1; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 2;
        angle_degrees = -90;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 1.0000, y = -1.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 5; y = 1; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 1;
        angle_degrees = 90;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = -1.0000, y = 5.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 7; y = 7; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 10;
        angle_degrees = 360;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 7.0000, y = 7.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 99; y = 99; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 99.99;
        angle_degrees = -720;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 99.0000, y = 99.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 10; y = 2; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 1;
        angle_degrees = 30;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 7.6603, y = 6.7321, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 1; y = 0; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 1;
        angle_degrees = 45;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 0.7071, y = 0.7071, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 5; y = 5; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 10;
        angle_degrees = -399;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 7.0323, y = 0.7391, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        // ----------------------

        x = 1; y = 0; z = 0;
        axis_x = 0; axis_y = 1; axis_z = 0;
        angle_degrees = 90;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 0.0000, y = 0.0000, z = -1.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 0; y = 0; z = 1;
        axis_x = 10; axis_y = 0; axis_z = 0;
        angle_degrees = -90;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 0.0000, y = 1.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 1; y = 1; z = 1;
        axis_x = 1; axis_y = 1; axis_z = 1;
        angle_degrees = 90;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 1.0000, y = 1.0000, z = 1.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 1; y = 1; z = 1;
        axis_x = -20; axis_y = 20; axis_z = -20;
        angle_degrees = -90;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = -0.8214, y = -0.3333, z = 1.4880  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 434; y = 297; z = -5.45;
        axis_x = -97.3; axis_y = 24; axis_z = 15.96;
        angle_degrees = -797.15;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 401.8423, y = -59.1675, z = 334.0909  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 1; y = 0; z = 1;
        axis_x = 0; axis_y = 10; axis_z = 0;
        angle_degrees = 45;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 1.4142, y = 0.0000, z = -0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 1; y = 1; z = 1;
        axis_x = 1; axis_y = 0; axis_z = 0;
        angle_degrees = -60;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 1.0000, y = 1.3660, z = -0.3660  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 0; y = -1; z = -1;
        axis_x = 0; axis_y = 1; axis_z = 0;
        angle_degrees = 45;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = -0.7071, y = -1.0000, z = -0.7071  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 1.456; y = -3.125; z = 2.125;
        axis_x = 3.333; axis_y = -4.511; axis_z = 0.189;
        angle_degrees = -15.244;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 1.8983, y = -2.7936, z = 2.2346  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 0; y = 0; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 0;
        angle_degrees = 0;
        try {
            System.out.println("Rotation is undefined.  <- expected");
            x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
            System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);
        } catch (IllegalArgumentException e) {
            System.out.println("Rotation is undefined.\n");
        }

        x = 1; y = 1; z = 1;
        axis_x = 0; axis_y = 0; axis_z = 0;
        angle_degrees = 1;
        try {
            System.out.println("Rotation is undefined.  <- expected");
            x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
            System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);
        } catch (IllegalArgumentException e) {
            System.out.println("Rotation is undefined.\n");
        }

        x = 1; y = 0; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 0.00001;
        angle_degrees = 0.00001;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 1.0000, y = 0.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 0.0001; y = 0; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 0.0001;
        angle_degrees = 10.0001;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 0.0001, y = 0.0000, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 0.01; y = 0; z = 0;
        axis_x = 0; axis_y = 0; axis_z = 0.0001;
        angle_degrees = 10.0001;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 0.0098, y = 0.0017, z = 0.0000  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);

        x = 0.01; y = 0.01; z = 0.0003;
        axis_x = 0.001; axis_y = 0.002; axis_z = 0.0001;
        angle_degrees = 5.01;
        x_y_z = rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        System.out.println("x = 0.0100, y = 0.0100, z = -0.0001  <- expected");
        System.out.printf("x = %.4f, y = %.4f, z = %.4f\n\n", x_y_z[0], x_y_z[1], x_y_z[2]);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new Rotator3D().run();
    }
}
