package pokemons;

import ablities.Bite;
import ablities.IronDefence;
import ablities.MetalSound;
import ablities.SandAttack;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Durant extends Pokemon {

    public Durant(String name, int level) {
        super(
            name, level
        );
        super.setStats(58., 109., 112., 48., 48., 109.);
        super.addMove(new Bite());
        super.addMove(new MetalSound());
        super.addMove(new SandAttack());
        super.addMove(new IronDefence());
        super.addType(Type.BUG);
        super.addType(Type.STEEL);
    }
}
