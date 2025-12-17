package pokemons;

import abilities.Confide;
import abilities.Growth;
import abilities.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Nuzleaf extends Seedot {

    public Nuzleaf(String name, int level) {
        super(
                name, level
        );
        super.setStats(70., 70., 40., 60., 40., 60.);
        super.addType(Type.DARK);
        super.addMove(new Growth());
    }
}