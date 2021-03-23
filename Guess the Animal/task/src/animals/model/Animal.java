package animals.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("animal")
public class Animal implements Node {

    @JsonProperty("name")
    public final String name;

    @JsonProperty("preposition")
    private final String preposition;

    @JsonCreator
    public Animal(@JsonProperty("preposition") String preposition,
                  @JsonProperty("name") String name) {

        this.preposition = preposition;
        this.name = name;
    }

    @JsonIgnore
    public String getNameWithPreposition() {
        return String.format("%s %s", preposition, name);
    }

    @JsonIgnore
    @Override
    public boolean isLeaf() {
        return true;
    }
}
