package inanimateObjects;

public enum Role {
    KING("Король"),
    QUEEN("Королева");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
