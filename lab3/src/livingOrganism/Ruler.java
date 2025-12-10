package livingOrganism;

import inanimateObjects.Decree;
import inanimateObjects.Role;

public class Ruler  extends Human{

    protected Role role;

    public Ruler(double HP, double luck, String name, double height, double weight, Gender gender, int age, Role role) {
        super(HP, luck, name, height, weight, gender, age);
        this.role = role;
    }

    public Decree issueDecree(String topic, String content, double rating) { //красивый вывод сделан нейронкой
        String formattedContent = "| " + content.replace("\n", "\n| ");
        System.out.printf(
                "%n========================================%n" +
                        "👑 Правитель: %s%n" +
                        "📜 Тема:      %s%n" +
                        "⭐ Рейтинг:   %.1f%n" +
                        "----------------------------------------%n" +
                        "Текст указа:%n" +
                        "%s%n" +
                        "========================================%n",
                name, topic, rating, formattedContent
        );
        return new Decree(topic, content, rating);
    }

    @Override
    public String toString() {
        return "Ruler " + name;
    }

    @Override
    public Medic callEmergency() {
        System.out.println("Ruler " + name + " вызывает скорую.");
        Medic medic = new Medic("Тамара");
        return medic;
    }
}
