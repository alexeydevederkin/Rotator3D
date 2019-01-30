package rotator3d;

import org.junit.jupiter.api.Assertions;


class Rotator3DTest {
    private static final double DELTA = 0.0001;

    private Rotator3D rotator = new Rotator3D();


    @org.junit.jupiter.api.Test
    void rotate3d_1() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 90;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0, 1.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_2() {
        double x = 0, y = 1, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 10;
        double angle_degrees = 180;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0, -1.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_3() {
        double x = 1, y = 1, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 2;
        double angle_degrees = -90;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.0, -1.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_4() {
        double x = 5, y = 1, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 90;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {-1.0, 5.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_5() {
        double x = 7, y = 7, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 10;
        double angle_degrees = 360;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {7.0, 7.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_6() {
        double x = 99, y = 99, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 99.99;
        double angle_degrees = -720;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {99.0, 99.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_7() {
        double x = 10, y = 2, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 30;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {7.6603, 6.7321, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_8() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 45;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.7071, 0.7071, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_9() {
        double x = 5, y = 5, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 10;
        double angle_degrees = -399;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {7.0323, 0.7391, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_10() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 1, axis_z = 0;
        double angle_degrees = 90;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0, 0.0, -1.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_11() {
        double x = 0, y = 0, z = 1;
        double axis_x = 10, axis_y = 0, axis_z = 0;
        double angle_degrees = -90;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0, 1.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_12() {
        double x = 1, y = 1, z = 1;
        double axis_x = 1, axis_y = 1, axis_z = 1;
        double angle_degrees = 90;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.0, 1.0, 1.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_13() {
        double x = 1, y = 1, z = 1;
        double axis_x = -20, axis_y = 20, axis_z = -20;
        double angle_degrees = -90;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {-0.8214, -0.3333, 1.4880};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_14() {
        double x = 434, y = 297, z = -5.45;
        double axis_x = -97.3, axis_y = 24, axis_z = 15.96;
        double angle_degrees = -797.15;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {401.8423, -59.1675, 334.0909};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_15() {
        double x = 1, y = 0, z = 1;
        double axis_x = 0, axis_y = 10, axis_z = 0;
        double angle_degrees = 45;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.4142, 0.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_16() {
        double x = 1, y = 1, z = 1;
        double axis_x = 1, axis_y = 0, axis_z = 0;
        double angle_degrees = -60;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.0, 1.3660, -0.3660};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_17() {
        double x = 0, y = -1, z = -1;
        double axis_x = 0, axis_y = 1, axis_z = 0;
        double angle_degrees = 45;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {-0.7071, -1.0000, -0.7071};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_18() {
        double x = 1.456, y = -3.125, z = 2.125;
        double axis_x = 3.333, axis_y = -4.511, axis_z = 0.189;
        double angle_degrees = -15.244;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.8983, -2.7936, 2.2346};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_19() {
        double x = 0, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 0;
        double angle_degrees = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        });
    }

    @org.junit.jupiter.api.Test
    void rotate3d_20() {
        double x = 1, y = 1, z = 1;
        double axis_x = 0, axis_y = 0, axis_z = 0;
        double angle_degrees = 1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        });
    }

    @org.junit.jupiter.api.Test
    void rotate3d_21() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 0.00001;
        double angle_degrees = 0.00001;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.0000, 0.0000, 0.0000};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_22() {
        double x = 0.0001, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 0.0001;
        double angle_degrees = 10.0001;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0001, 0.0000, 0.0000};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_23() {
        double x = 0.01, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 0.0001;
        double angle_degrees = 10.0001;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0098, 0.0017, 0.0000};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3d_24() {
        double x = 0.01, y = 0.01, z = 0.0003;
        double axis_x = 0.001, axis_y = 0.002, axis_z = 0.0001;
        double angle_degrees = 5.0001;
        double[] x_y_z = rotator.rotate3d(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0100, 0.0100, -0.0001};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }


    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_1() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 90;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0, 1.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_2() {
        double x = 0, y = 1, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 10;
        double angle_degrees = 180;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0, -1.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_3() {
        double x = 1, y = 1, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 2;
        double angle_degrees = -90;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.0, -1.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_4() {
        double x = 5, y = 1, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 90;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {-1.0, 5.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_5() {
        double x = 7, y = 7, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 10;
        double angle_degrees = 360;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {7.0, 7.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_6() {
        double x = 99, y = 99, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 99.99;
        double angle_degrees = -720;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {99.0, 99.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_7() {
        double x = 10, y = 2, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 30;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {7.6603, 6.7321, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_8() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 1;
        double angle_degrees = 45;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.7071, 0.7071, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_9() {
        double x = 5, y = 5, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 10;
        double angle_degrees = -399;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {7.0323, 0.7391, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_10() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 1, axis_z = 0;
        double angle_degrees = 90;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0, 0.0, -1.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_11() {
        double x = 0, y = 0, z = 1;
        double axis_x = 10, axis_y = 0, axis_z = 0;
        double angle_degrees = -90;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0, 1.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_12() {
        double x = 1, y = 1, z = 1;
        double axis_x = 1, axis_y = 1, axis_z = 1;
        double angle_degrees = 90;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.0, 1.0, 1.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_13() {
        double x = 1, y = 1, z = 1;
        double axis_x = -20, axis_y = 20, axis_z = -20;
        double angle_degrees = -90;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {-0.8214, -0.3333, 1.4880};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_14() {
        double x = 434, y = 297, z = -5.45;
        double axis_x = -97.3, axis_y = 24, axis_z = 15.96;
        double angle_degrees = -797.15;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {401.8423, -59.1675, 334.0909};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_15() {
        double x = 1, y = 0, z = 1;
        double axis_x = 0, axis_y = 10, axis_z = 0;
        double angle_degrees = 45;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.4142, 0.0, 0.0};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_16() {
        double x = 1, y = 1, z = 1;
        double axis_x = 1, axis_y = 0, axis_z = 0;
        double angle_degrees = -60;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.0, 1.3660, -0.3660};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_17() {
        double x = 0, y = -1, z = -1;
        double axis_x = 0, axis_y = 1, axis_z = 0;
        double angle_degrees = 45;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {-0.7071, -1.0000, -0.7071};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_18() {
        double x = 1.456, y = -3.125, z = 2.125;
        double axis_x = 3.333, axis_y = -4.511, axis_z = 0.189;
        double angle_degrees = -15.244;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.8983, -2.7936, 2.2346};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_19() {
        double x = 0, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 0;
        double angle_degrees = 0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        });
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_20() {
        double x = 1, y = 1, z = 1;
        double axis_x = 0, axis_y = 0, axis_z = 0;
        double angle_degrees = 1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        });
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_21() {
        double x = 1, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 0.00001;
        double angle_degrees = 0.00001;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {1.0000, 0.0000, 0.0000};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_22() {
        double x = 0.0001, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 0.0001;
        double angle_degrees = 10.0001;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0001, 0.0000, 0.0000};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_23() {
        double x = 0.01, y = 0, z = 0;
        double axis_x = 0, axis_y = 0, axis_z = 0.0001;
        double angle_degrees = 10.0001;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0098, 0.0017, 0.0000};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }

    @org.junit.jupiter.api.Test
    void rotate3dQuaternion_24() {
        double x = 0.01, y = 0.01, z = 0.0003;
        double axis_x = 0.001, axis_y = 0.002, axis_z = 0.0001;
        double angle_degrees = 5.0001;
        double[] x_y_z = rotator.rotate3dQuaternion(x, y, z, axis_x, axis_y, axis_z, angle_degrees);
        double[] expected = {0.0100, 0.0100, -0.0001};
        Assertions.assertArrayEquals(expected, x_y_z, DELTA);
    }
}