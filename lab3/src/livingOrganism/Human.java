package livingOrganism;

import inanimateObjects.Food;
import inanimateObjects.Car;
import utils.EventMaker;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Human extends Entity{
    protected int age;
    protected double height;
    protected double weight;
    protected Gender gender;

    public Human(double HP, double luck, String name, double height, double weight, Gender gender, int age) {
        super(HP, luck, name);
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
    }

    public Medic callEmergency() {
        System.out.println("Human " + name + " вызывает скорую.");
        Medic medic = new Medic("Тамара");
        return medic;
    }

    @Override
    public String toString() {
        return "Human { Имя:'" + name + "', возраст: " + age + ", пол: " + gender + " }";
    }

    @Override
    protected void die() {
        deathTime = LocalDateTime.now();
        System.out.println("Human " + name + " умер(((");
        this.HP = 0;
    }

    @Override
    public void resurrect() {
        if (deathTime == null) {
            System.out.println("Human " + name + " жив");
            return ;
        }

        LocalDateTime now = LocalDateTime.now();
        long secondsPassed = ChronoUnit.SECONDS.between(deathTime, now);

        if (secondsPassed <= 5) {
            this.deathTime = null;
            this.HP = MAX_HP;
            System.out.println("УСПЕХ: Human " + name + " воскрешен!");
        } else {
            System.out.println("ПРОВАЛ: Слишком поздно. Душа уже покинула тело.");
        }
    }

    public void removeHumanFromCar(Car car) {
        removeHumanFromCar(this, car);
    }

    public void removeHumanFromCar(Human passenger, Car car) {
        car.removePassenger(passenger);
        if (passenger == this) {
            System.out.printf("Human %s вышел из машины%n", name);
        } else {
            System.out.printf("Human %s выкинул из машины Human %s%n", name, passenger.getName());
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public void loseConsciousness() {
        super.loseConsciousness();
        System.out.println("Human " + name + " потерял сознание");
    }

    @Override
    public void wake() {
        super.wake();
        System.out.println("Human " + name + " пришёл в себя");
    }

    public void eatRandomFood() {
        if (HP == 0) {
            System.out.printf("Human %s не может есть, так как он умир(((%n", name);
            return ;
        }
        Food food = EventMaker.getFood();
        double benefit = food.getBenefit();
        if (benefit > 0) {
            System.out.printf("Human %s съел %s и восполнил %.2f здоровья%n", name, food, benefit);
            HP = HP + benefit;
        } else {
            System.out.printf("Human %s съел %s и потерял %.2f здоровья%n", name, food, -benefit);
            if (getLuck() > 57 && HP + benefit <= 0) {
                System.out.println("у Human" + name + " срабатывает удача, и он не умирает!!! Его HP равно 0.01");
                HP = 0.01;
            } else {
                die();
            }
        }
    }

}
