package com.kien.tft.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kien.tft.model.Trait;
import com.kien.tft.model.Unit;
import com.kien.tft.service.TftService;

@RestController
@RequestMapping(
    produces = APPLICATION_JSON_VALUE
)
public class TftRestController {

    private TftService tftService;
    private ObjectMapper objectMapper;

    @GetMapping(
        value = "/unit/all"
    )
    public Collection<Unit> allUnits(){
        return tftService.allUnits();
    }


    @GetMapping(
        value = "/trait/all"
    )
    public Collection<Trait> allTraits(){
        return tftService.allTraits();
    }

    @GetMapping(
        value = "/unit/adja",
        params = "name"
    )
    public Map<Unit, Collection<Unit>> adjaUnits(Collection<Unit> units){
        return tftService
                        .allUnits().stream()
                        .filter(u -> units.contains(u))
                        .collect(Collectors.toMap(Function.identity(), u -> tftService.adjaUnit(u)));
    }

    @GetMapping(
        value = "/unit"
    )
    public Collection<Trait> traitOf(@RequestBody Unit unit){
        return tftService.traitOf(unit);
    }

    @GetMapping(
        value = "/trait"
    )
    public Collection<Unit> unitOf(@RequestBody Trait trait){
        return tftService.unitOf(trait);
    }

    @GetMapping(
        value = "/path/list",
        consumes = APPLICATION_JSON_VALUE
    )
    public LinkedList<Unit> getPathAsList(@RequestBody String jsonAsString){
        Map<String, Unit> unitMap = parseUnitJson(jsonAsString);
        return unitMap == null ? 
            new LinkedList<>() : tftService.getPathAsList(unitMap.get("src"), unitMap.get("des"));
    }

    @GetMapping(
        value = "/path"
    )
    public int getPathLength(@RequestBody String jsonAsString){
        
        Map<String, Unit> unitMap = parseUnitJson(jsonAsString);

        return unitMap == null ? 
            -1 : tftService.getPathLength(unitMap.get("src"), unitMap.get("des"));
    }

    private Map<String, Unit> parseUnitJson(String jsonAsString){
        try {
            Map<String, Unit> result = new HashMap<>();
            JsonNode map = objectMapper.readTree(jsonAsString);
            for(JsonNode unit: map){
                result.put(unit.get("tag").asText(),tftService.getUnitFromName(unit.get("name").asText()));
            }
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }



    public TftService getTftService() {
        return tftService;
    }

    public void setTftService(TftService tftService) {
        this.tftService = tftService;
    }


    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
}
