package inanimateObjects;

import barriers.Barrier;
import livingOrganism.Human;
import utils.EventMaker;
import utils.RandomGenerator;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Car {

    protected String model;
    protected String color;
    protected double length;
    protected double width;
    protected double height;
    protected double speed;
    protected double HP;
    protected List<Human> passengers = new ArrayList<>();

    public Car(String model, String color, double length, double width, double height, double speed, double HP) {
        this.model = model;
        double MAX_HP = 100.;
        this.HP = Math.min(HP, MAX_HP);
        this.color = color;
        this.length = length;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    protected void whistle() {
        System.out.println("Машина " + model + " засвистела,");
    }

    protected void makeNoise() {
        System.out.println("Машина " + model + " зашумела,");
    }

    protected void buzz() {
        System.out.println("Машина " + model + " зажужжала,");
    }

    protected void ringBell() {
        System.out.println("Машина " + model + " зазвонила в звонок,");
    }

    protected void puffWithHeatAndSteam() {
        System.out.println("Машина " + model + " жаром-паром запыхтела,");
    }

    protected void shake() {
        System.out.println("Машина " + model + " задрожала,");
    }

    protected void leap() {
        System.out.println("Машина " + model + " запрыгала,");
    }

    protected void ride() {
        System.out.println("Машина " + model + " поехала,");
    }

    protected void sway() {
        System.out.println("Машина " + model + " заколыхала.");
    }

    public boolean isBroken() {
        return HP <= 0;
    }

    public void startEngine() {
        System.out.println("Машина " + model + " запускает двигатель:");
        whistle();
        makeNoise();
        buzz();
        ringBell();
        puffWithHeatAndSteam();
        shake();
        leap();
        ride();
        sway();
    }

    public void addPassenger(Human passenger) {
        if (!passengers.contains(passenger)) {
            passengers.add(passenger);
            System.out.println("Human " + passenger.getName() + " был посажен в Car " + model);
        } else {
            throw new IllegalStateException("Пассажир уже есть в машине");
        }
    }

    public void removePassenger(Human passenger) {
        if (passengers.contains(passenger)) {
            passengers.remove(passenger);
        } else {
            throw new IllegalStateException("Пассажир нет в машине");
        }
    }

    @Override
    public String toString() {
        return "Car { модель: " + model + ", здоровье: " + HP + ", цвет: " + color + " }";
    }

    public void setHP(double HP) {
        if (HP < 0) HP = 0;
        this.HP = HP;
    }

    private boolean move() {
        return move(600);
    }

    private boolean move(double distance) {
        if (passengers.isEmpty()) {
            System.out.println("Car " + model + " не может ехать без водителя");
            return false;
        }
        int iters = (int) (distance / speed) + 1;
        for (int i = 0; i < iters; i++) {
            System.out.println("Car " + model + " едет.");
            double chance = RandomGenerator.getDoubleRandom(0., 100.);
            if (chance > 70) {
                Barrier barrier = EventMaker.getBarrier();
                double damage = breakCar(barrier);
                System.out.printf("Car %s врезалась в %s и получила %.2f урона.%n", model, barrier, damage);
            }
            if (isBroken()) {
                System.out.println("Car " + model + " сломана!!!");
                return false;
            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {}
        }
        return true;
    }

    public void cross(Bridge bridge) {
        boolean isFinish = move(bridge.length());
        if (isFinish) {
            System.out.println("Car " + model + " успешно пересекла " + bridge + ".");
        } else {
            System.out.println("Car " + model + " не смогла пересечь " + bridge + ".");
        }
    }

    public void comeTo(Home home) {
        boolean isFinish = move();
        if (isFinish) {
            System.out.println("Car " + model + " успешно приехала к " + home + ".");
        } else {
            System.out.println("Car " + model + " не смогла приехать к " + home + ".");
        }
    }

    private double breakCar(Barrier barrier) {
        double damage = barrier.getDamage();
        setHP(HP - damage);
        return damage;
    }
}
