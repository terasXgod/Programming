package abilities;

import ru.ifmo.se.pokemon.*;

public class SandAttack extends StatusMove {

    public SandAttack() {
        super(Type.GROUND, 0., 100.);
    }

    @Override
    protected String describe(){
        return "Использует Sand Attack";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.ACCURACY, -1);
    }
}
