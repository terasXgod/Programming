package livingOrganism;

public class Nobleman extends Human{

    public Nobleman(double HP, double luck, String name, double height, double weight, Gender gender, int age) {
        super(HP, luck, name, height, weight, gender, age);
    }

    public void blowing(Human human) {
        System.out.printf("Nobleman %s машет поддувалами на %s%n", name, human);
        if (!human.consciousness) {
            human.wake();
        }
    }
}
