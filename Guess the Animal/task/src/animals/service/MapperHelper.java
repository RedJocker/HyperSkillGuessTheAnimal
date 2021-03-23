package animals.service;

import animals.model.Node;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.*;

public class MapperHelper {

    private final ObjectMapper mapper;
    private final String fileName;


    public MapperHelper(String type) {
        switch(type){
            case "yaml":
                this.mapper = new YAMLMapper();
                this.fileName = "animals.yaml";
                break;
            case "xml":
                this.mapper = new XmlMapper();
                this.fileName = "animals.xml";
                break;
            case "json":
            default:
                this.mapper = new JsonMapper();
                this.fileName = "animals.json";
                break;
        }

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    }

    public void saveKnowledge(Node root){
        final File file = new File(fileName);
        try(final OutputStreamWriter outWriter =
                    new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)))
        ) {
            this.mapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(outWriter, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node loadKnowledge(){
        try {
            return this.mapper
                    .readValue(new File(fileName), Node.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
