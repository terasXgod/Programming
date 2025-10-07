package pokemons;

import abilities.Confide;
import abilities.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Seedot extends Pokemon {

    public Seedot(String name, int level) {
        super(
                name, level
        );
        super.setStats(58., 109., 112., 48., 48., 109.);
        super.setType(Type.GRASS);
        super.addMove(new Swagger());
        super.addMove(new Confide());
    }

}