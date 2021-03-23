package animals.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static animals.userInterface.WordService.Verb;

public class Fact {

    @JsonProperty("description")
    public final String description;

    @JsonProperty("verb")
    public final Verb verb;

    @JsonCreator
    public Fact(@JsonProperty("verb") Verb verb,
                @JsonProperty("description") String description) {
        this.verb = verb;
        this.description = description;
    }

    @JsonIgnore
    public String getPositiveAffirmation(){
        return verb.positive + " " + description;
    }

    @JsonIgnore
    public String getNegativeAffirmation(){
        return verb.negative + " " + description;
    }

    @JsonIgnore
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
