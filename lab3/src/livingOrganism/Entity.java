package livingOrganism;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import utils.RandomGenerator;

abstract public class Entity {
    protected double HP;
    private final double luck;
    protected String name;
    protected boolean consciousness = true;
    protected int dnaCode = RandomGenerator.getIntRandom(1, 65536);
    protected LocalDateTime deathTime;
    protected final double MAX_HP = 100.;

    public Entity(double hp, double luck, String name) {
        this.HP = Math.min(hp, MAX_HP);
        this.luck = luck;
        this.name = name;
        this.deathTime = null;

    }

    public void resurrect() {
        if (deathTime == null) {
            System.out.println("Entity " + name + " жив");
            return ;
        }

        LocalDateTime now = LocalDateTime.now();
        long secondsPassed = ChronoUnit.SECONDS.between(deathTime, now);

        if (secondsPassed <= 5) {
            this.deathTime = null;
            this.HP = MAX_HP;
            System.out.println("УСПЕХ: Entity" + name + " воскрешен!");
        } else {
            System.out.println("ПРОВАЛ: Слишком поздно. Душа уже покинула тело.");
        }
    }

    protected double getHP() {
        return HP;
    }

    protected double getLuck() {
        return luck;
    }

    public void setConsciousness(boolean status) {
        consciousness = status;
    }

    public boolean isAlive() {
        return (HP > 0);
    }

    protected boolean isConsciousness() {
        return consciousness;
    }

    protected void die() {
        this.deathTime = LocalDateTime.now();
        System.out.println("Entity умер");
        this.HP = 0;
    }

    protected void loseConsciousness() {
        setConsciousness(false);
    }

    protected void wake() {
        setConsciousness(true);
    }

    @Override
    public int hashCode() {
        return dnaCode;
    }

}
