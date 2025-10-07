package abilities;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class WorkUp extends StatusMove {

    public WorkUp() {
        super(Type.NORMAL, 0., 0.);
    }

    @Override
    protected String describe(){
        return "Использует Work Up";
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_ATTACK, +1);
        pokemon.setMod(Stat.ATTACK, +1);
    }
}
