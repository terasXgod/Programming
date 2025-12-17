package pokemons;

import abilities.Confide;
import abilities.Growth;
import abilities.LowSweep;
import abilities.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

final public class Shiftry extends Nuzleaf {

    public Shiftry(String name, int level) {
        super(
                name, level
        );
        super.setStats(90., 100., 60., 90., 60., 80.);
        addMove(new LowSweep());
    }
}