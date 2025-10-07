package abilities;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class BellyDrum extends StatusMove {

    public BellyDrum() {
        super(Type.NORMAL, 0., 0.);
    }

    @Override
    protected String describe(){
        return "Издаёт Belly Drum";
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        double halfHP = pokemon.getStat(Stat.HP) / 2;
        pokemon.setMod(Stat.HP, -(int) halfHP);
        pokemon.setMod(Stat.ATTACK, +6);
    }
}
