package com.kien.tft.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kien.tft.model.Trait;
import com.kien.tft.model.Unit;
import com.kien.tft.service.TftService;

@RestController
@RequestMapping(
    produces = APPLICATION_JSON_VALUE
)
public class TftRestController {

    private TftService tftService;

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
    public Map<Unit, Collection<Unit>> adjaUnits(@RequestParam("name") Unit... units){
        Collection<Unit> unitList = Arrays.asList(units);
        return tftService
                        .allUnits().stream()
                        .filter(u -> unitList.contains(u))
                        .collect(Collectors.toMap(Function.identity(), u -> tftService.adjaUnit(u)));
    }

    @GetMapping(
        value = "/unit",
        params = "name"
    )
    public Collection<Trait> traitOf(@RequestParam("name") Unit unit){
        return tftService.traitOf(unit);
    }

    @GetMapping(
        value = "/trait",
        params = "name"
    )
    public Collection<Unit> unitOf(@RequestParam("name") Trait trait){
        return tftService.unitOf(trait);
    }

    @GetMapping(
        value = "/path/list",
        params = {"src", "des"}
    )
    public LinkedList<Unit> getPathAsList(@RequestParam("src") Unit src, @RequestParam("des") Unit des){
        return tftService.getPathAsList(src, des);
    }

    @GetMapping(
        value = "/path/length",
        params = {"src", "des"}
    )
    public int getPathLength(@RequestParam("src") Unit src, @RequestParam("des") Unit des){
        return tftService.getPathLength(src, des);
    }

    /*
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
    */

    public void setTftService(TftService tftService) {
        this.tftService = tftService;
    }
    
}
