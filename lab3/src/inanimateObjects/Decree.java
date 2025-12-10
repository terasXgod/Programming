package inanimateObjects;

public class Decree {

    protected final String topic;
    protected final String content;
    protected double rating;

    public Decree(String topic, String content, double rating) {
        this.topic = topic;
        this.content = content;
        this.rating = rating;
    }
}
