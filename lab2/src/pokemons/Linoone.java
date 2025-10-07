package pokemons;

import ablities.BellyDrum;
import ablities.Slash;
import ablities.Swagger;
import ablities.WorkUp;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Linoone extends Pokemon {

    public Linoone(String name, int level) {
        super(
                name, level
        );
        super.setStats(78., 70., 61., 50., 61., 100.);
        super.addMove(new BellyDrum());
        super.addMove(new Swagger());
        super.addMove(new WorkUp());
        super.addMove(new Slash());
        super.setType(Type.NORMAL);
    }
}