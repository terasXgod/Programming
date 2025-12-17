package pokemons;

import abilities.Bite;
import abilities.IronDefence;
import abilities.MetalSound;
import abilities.SandAttack;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

final public class Durant extends Pokemon {

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
