package common.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * Simple value object for storing 2D coordinates.
 */
public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * X-coordinate (max 82); cannot be {@code null}.
     */
    private Integer x;
    /**
     * Y-coordinate; cannot be {@code null}.
     */
    private Integer y;

    /**
     * Creates coordinates from integer components.
     *
     * @param x x-axis value, max 82
     * @param y y-axis value
     */
    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates coordinates from string components.
     *
     * @param x string representation of x
     * @param y string representation of y
     */
    public Coordinates(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    @Override
    public String toString() {
        return "(x: " + x + ";y: " + y + ")";
    }

    /**
     * Returns the x coordinate.
     *
     * @return x value
     */
    public Integer getX() {
        return x;
    }

    /**
     * Returns the y coordinate.
     *
     * @return y value
     */
    public Integer getY() {
        return y;
    }
}