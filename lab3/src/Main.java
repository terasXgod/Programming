import inanimateObjects.*;
import livingOrganism.*;

public class Main {
    public static void main(String[] args) {
        Human character1 = new Human(1., 10., "Bebra", 192., 192., Gender.MALE, 13);
        Car car1 = new Car("Tesla", "blue", 12., 12., 1000., 150., 2000.);
        Bridge bridge1 = new Bridge("Троицкий мост", 582.);
        Ruler ruler2 = new Ruler(1., 10., "Bebrichka", 154., 192., Gender.FEMALE, 13, Role.QUEEN);
        Ruler ruler1 = new Ruler(1., 10., "Bebrik", 192., 192., Gender.MALE, 13, Role.KING);
        Nobleman noble1 = new Nobleman(1., 10., "Bober", 192., 192., Gender.MALE, 13);
        Nobleman noble2 = new Nobleman(1., 10., "Bebriha", 192., 192., Gender.MALE, 13);
        Decree decree1 = ruler1.issueDecree("довезти до дома Bebra", "Необходимо довезти меня, Bebrik, до дома Bebra.\nСРОЧНО!!!", 52.);
        Home home1 = new Home(character1);
        car1.addPassenger(character1);
        car1.addPassenger(ruler1);
        car1.addPassenger(ruler2);
        character1.eatRandomFood();
        if (!character1.isAlive()) {
            Medic medic1 = ruler1.callEmergency();
            medic1.revive(character1);
        }
        car1.startEngine();
        car1.cross(bridge1);
        character1.resurrect();
        if (!car1.isBroken()) {
            ruler1.loseConsciousness();
            ruler2.loseConsciousness();
            car1.comeTo(home1);
            character1.removeHumanFromCar(car1);
            noble1.removeHumanFromCar(ruler1, car1);
            noble1.removeHumanFromCar(ruler2, car1);
            noble2.blowing(ruler1);
            noble2.blowing(ruler2);
        } else {
            System.out.println("Мы не можем поехать, анлак(((");
            System.out.println("История окончена(((");
        }
    }
}
