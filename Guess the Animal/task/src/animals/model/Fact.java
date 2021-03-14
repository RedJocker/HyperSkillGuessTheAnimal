package animals.model;

import java.util.Objects;

import static animals.userInterface.WordService.Verb;

public class Fact {

    public final String description;
    public final Verb verb;


    public Fact(Verb verb, String description) {
        this.verb = verb;
        this.description = description;
    }

    public String getPositiveAffirmation(){
        return verb.positive + " " + description;
    }

    public String getNegativeAffirmation(){
        return verb.negative + " " + description;
    }

    public String getQuestion(){
        return verb.question + " " + description + "?";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Fact fact = (Fact) other;
        return description.equals(fact.description) &&
                verb == fact.verb;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, verb);
    }
}
