package collections;

public class Coordinates {
/**
     * X-coordinate (max 82); cannot be {@code null}.
     */
    private Integer x;
    /**
     * Y-coordinate; cannot be {@code null}.
     */
    private Integer y;
    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(x: " + x + ";y: " + y + ")";
    }
}