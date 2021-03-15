package animals.model;

public class Animal implements Node {
    public final String name;
    private final String preposition;

    public Animal(String preposition, String name) {
        this.preposition = preposition;
        this.name = name;
    }

    public String getNameWithPreposition() {
        return String.format("%s %s", preposition, name);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
