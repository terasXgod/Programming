package pokemons;

import ablities.Confide;
import ablities.Growth;
import ablities.LowSweep;
import ablities.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Shiftry extends Pokemon {

    public Shiftry(String name, int level) {
        super(
                name, level
        );
        super.setStats(90., 100., 60., 90., 60., 80.);
        super.setType(Type.GRASS);
        super.setType(Type.DARK);
        super.addMove(new Swagger());
        super.addMove(new Confide());
        super.addMove(new Growth());
        super.addMove(new LowSweep());
    }
}