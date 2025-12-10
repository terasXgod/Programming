package inanimateObjects;

public record Bridge(String name, double length) {
    @Override
    public String toString() {
        return name;
    }
}
