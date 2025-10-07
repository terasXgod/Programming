package ablities;

import ru.ifmo.se.pokemon.*;

public class Bite extends PhysicalMove {

    public Bite() {
        super(Type.DARK, 60., 100.);
    }

    @Override
    protected String describe(){
        return "использует Bite";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() <= 0.3) {
            Effect.flinch(pokemon);
        }
    }
}
