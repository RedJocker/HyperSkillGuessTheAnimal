package animals.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = Distinction.class, name = "distinction"),
            @JsonSubTypes.Type(value = Animal.class, name = "animal")})
public interface Node {

    public boolean isLeaf();
}
