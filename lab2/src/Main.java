import pokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

public class Main {
    public static void main(String[] args) {
        Battle battle = new Battle();
        Durant p1 = new Durant("Yatoro", 1);
        Linoone p2 = new Linoone("Старый Бог", 1);
        Nuzleaf p3 = new Nuzleaf("Miposhka", 1);
        Seedot p4 = new Seedot("Larl", 1);
        Zigzagoon p5 = new Zigzagoon("Collapse", 1);
        Shiftry p6 = new Shiftry("Rue", 1);

        battle.addAlly(p1);
        battle.addAlly(p2);
        battle.addAlly(p3);
        battle.addFoe(p4);
        battle.addFoe(p5);
        battle.addFoe(p6);

        battle.go();
    }
}
