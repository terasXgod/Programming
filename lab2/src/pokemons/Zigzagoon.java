package pokemons;

import abilities.BellyDrum;
import abilities.Swagger;
import abilities.WorkUp;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Zigzagoon extends Pokemon {

    public Zigzagoon(String name, int level) {
        super(
                name, level
        );
        super.setStats(38., 40., 41., 30., 41., 60.);
        super.addMove(new BellyDrum());
        super.addMove(new Swagger());
        super.addMove(new WorkUp());
        super.setType(Type.NORMAL);
    }
}