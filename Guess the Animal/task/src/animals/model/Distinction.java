package animals.model;

public class Distinction {
    public final Fact fact;
    public final Animal animalPositiveForFact;
    public final Animal animalNegativeForFact;

    public Distinction(Fact fact, Animal animalPositiveForFact, Animal animalNegativeForFact) {
        this.fact = fact;
        this.animalPositiveForFact = animalPositiveForFact;
        this.animalNegativeForFact = animalNegativeForFact;
    }
}
