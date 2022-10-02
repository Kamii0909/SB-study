package com.kien.tft.dao.preload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kien.tft.model.Trait;
import com.kien.tft.model.Unit;

public class LocalPreload extends AbstractPreload{
    private List<Query> queries;
    private File unitFile;
    private File traitFile;

    public LocalPreload(String pathToTraitFile, String pathToUnitFile) throws IOException{
        super();
        this.queries = new ArrayList<>();
        this.traitFile = new ClassPathResource(pathToTraitFile).getFile();
        this.unitFile = new ClassPathResource(pathToUnitFile).getFile();
    }

    public void init() throws IOException{
        ObjectMapper mapper = new ObjectMapper();

        for(JsonNode traitData: mapper.readTree(traitFile)){
            traits.add(new Trait(traitData.get("name").asText()));
        }

        for(JsonNode unitData: mapper.readTree(unitFile)){
            Unit unit = new Unit(unitData.get("name").asText());
            units.add(unit);
            List<Trait> list = new ArrayList<>(4);
            for(JsonNode traitData: unitData.get("traits")){
                list.add(traits.stream()
                    .filter(t -> t.getName().equals(traitData.get("name").asText()))
                    .findFirst().get());
            }
            queries.add(new Query(unit, list));
        }
    }
    
    @Override
    public List<Query> getQueries() {
        return queries;
    }
}
