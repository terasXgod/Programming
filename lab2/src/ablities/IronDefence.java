package ablities;

import ru.ifmo.se.pokemon.*;

public class IronDefence extends StatusMove {

    public IronDefence() {
        super(Type.STEEL, 0., 0.);
    }

    @Override
    protected String describe(){
        return "Издаёт Iron Defence";
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.DEFENSE, +2);
    }
}
