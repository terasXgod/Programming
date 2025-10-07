package pokemons;

import ablities.Confide;
import ablities.Growth;
import ablities.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Nuzleaf extends Pokemon {

    public Nuzleaf(String name, int level) {
        super(
                name, level
        );
        super.setStats(70., 70., 40., 60., 40., 60.);
        super.setType(Type.GRASS);
        super.setType(Type.DARK);
        super.addMove(new Swagger());
        super.addMove(new Confide());
        super.addMove(new Growth());
    }
}