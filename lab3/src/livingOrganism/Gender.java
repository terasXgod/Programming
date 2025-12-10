package livingOrganism;

public enum Gender {

    MALE("Мужчина"),
    FEMALE("Девушка");

    private final String sexName;

    Gender(String sexName) {
        this.sexName = sexName;
    }

    @Override
    public String toString() {
        return sexName;
    }

}
