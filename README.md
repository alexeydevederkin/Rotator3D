# Rotator3D

Rotation in 3D space with quaternions.

## Usage

```(init_x, init_y, init_z)``` — coordinates of the point to rotate.

```(0, 0, 0) -> (axis_x, axis_y, axis_z)``` — axis, around which to rotate.

```angle_degrees``` — angle of rotation around the axis (clockwise).

Method 
```java
double[] rotate3d(double init_x, double init_y, double init_z, 
                  double axis_x, double axis_y, double axis_z, 
                  double angle_degrees)
```
will return array of 3 double values = x, y, z coordinates of the point after rotation.
