package ablities;

import ru.ifmo.se.pokemon.*;

public class Slash extends PhysicalMove {

    public Slash() {
        super(Type.NORMAL, 70., 100.);
    }

    @Override
    protected String describe(){
        return "Использует Slash";
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        if (Math.random() < (1.0 / 8.0)) {
            System.out.println("Критический удар!");
            return 2.0;
        }
        return 1.0;
    }
}
