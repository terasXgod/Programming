package pokemons;

import abilities.BellyDrum;
import abilities.Slash;
import abilities.Swagger;
import abilities.WorkUp;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

final public class Linoone extends Zigzagoon {

    public Linoone(String name, int level) {
        super(
                name, level
        );
        setStats(78., 70., 61., 50., 61., 100.);
        addMove(new BellyDrum());
        addMove(new Swagger());
        addMove(new WorkUp());
        addMove(new Slash());
        setType(Type.NORMAL);
    }
}