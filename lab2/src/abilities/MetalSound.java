package abilities;

import ru.ifmo.se.pokemon.*;

public class MetalSound extends StatusMove {

    public MetalSound() {
        super(Type.STEEL, 0., 85.);
    }

    @Override
    protected String describe(){
        return "Издаёт Metal Sound";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_DEFENSE, -2);
    }
}
