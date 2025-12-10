package livingOrganism;

public class Medic{
    protected String name;

    public Medic(String name) {
        System.out.println("Medic " + name + " прибыл");
        this.name = name;
    }

    public void revive(Human human) {
        System.out.println("Medic " + name + " пробует воскрешить Human " + human.getName());
        human.resurrect();
    }
}
