package animals.model;

public class Distinction implements Node {
    public final Fact fact;
    public final Node positiveForFact;
    public final Node negativeForFact;

    public Distinction(Fact fact, Node positiveForFact, Node negativeForFact) {
        this.fact = fact;
        this.positiveForFact = positiveForFact;
        this.negativeForFact = negativeForFact;
    }


    @Override
    public boolean isLeaf() {
        return false;
    }

    public Distinction addPositive(Distinction positiveForFact) {
        return new Distinction(fact, positiveForFact, negativeForFact);
    }

    public Distinction addNegative(Distinction negativeForFact) {
        return new Distinction(fact, positiveForFact, negativeForFact);
    }
}
