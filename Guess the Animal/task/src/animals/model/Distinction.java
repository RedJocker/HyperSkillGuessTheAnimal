package animals.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("distinction")
public class Distinction implements Node {

    @JsonProperty("fact")
    public final Fact fact;

    @JsonProperty("positiveForFact")
    public final Node positiveForFact;

    @JsonProperty("negativeForFact")
    public final Node negativeForFact;

    @JsonCreator
    public Distinction(@JsonProperty("fact") Fact fact,
                       @JsonProperty("positiveForFact") Node positiveForFact,
                       @JsonProperty("negativeForFact")Node negativeForFact) {

        this.fact = fact;
        this.positiveForFact = positiveForFact;
        this.negativeForFact = negativeForFact;
    }

    @JsonIgnore
    @Override
    public boolean isLeaf() {
        return false;
    }

    @JsonIgnore
    public Distinction addPositive(Distinction positiveForFact) {
        return new Distinction(fact, positiveForFact, negativeForFact);
    }

    @JsonIgnore
    public Distinction addNegative(Distinction negativeForFact) {
        return new Distinction(fact, positiveForFact, negativeForFact);
    }



}
