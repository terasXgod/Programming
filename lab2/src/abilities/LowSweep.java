package abilities;

import ru.ifmo.se.pokemon.*;

public class LowSweep extends PhysicalMove {

    public LowSweep() {
        super(Type.FIGHTING, 65., 100.);
    }

    @Override
    protected String describe() {
        return "использует Low Sweep";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPEED, -1);
    }
}
