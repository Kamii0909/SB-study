package com.kien.demo.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kien.demo.model.Trait;
import com.kien.demo.model.Unit;
import com.kien.demo.service.TftService;

@RestController
@RequestMapping("/tft")
public class TftController {

    private final TftService tftService;

    @Autowired
    public TftController(TftService tftService) {
        this.tftService = tftService;
    }

    @GetMapping(path = "/traits")
    public String getAllTraits(){
        return Arrays.toString(tftService.getAllTraits().toArray());
    }

    @GetMapping(path = "/traits/contain/{traitName}")
    public ResponseEntity<String> getTraitsContain(@PathVariable("traitName") String traitName){
        Collection<Trait> requestedTraits = tftService.getTraitsContain(traitName);
        if(requestedTraits.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(Arrays.toString(requestedTraits.toArray()));
        }
    }
    
    @GetMapping(path = "/traits/exact/{traitName}")
    public String getTraitsExact(@PathVariable("traitName") String traitName){
        return tftService.getTraitsExact(traitName).orElse(null).toString();
    }
    

    @GetMapping(path = "/traits/contain")
    public ResponseEntity<String> getTraitsContain(@RequestBody Unit[] unit){
        Collection<Trait> requestedTraits = tftService.getTraitsContain(unit);
        if(requestedTraits.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(Arrays.toString(requestedTraits.toArray()));
        }
    }
   
    @GetMapping(path = "/avg")
    public ResponseEntity<String> averageConnectedScore(@RequestBody List<Unit> unit){
        unit = unit.stream().map(u -> tftService.getUnitsExact(u.getName()).orElse(null)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
        .body(tftService.getAverageConnectedScore(unit).toString());
    }

    @DeleteMapping
    public ResponseEntity<String> ignoreATrait(@RequestBody Trait trait){
        return ResponseEntity.status(HttpStatus.OK).body(tftService.ignoreATrait(trait));
    }



    
}
