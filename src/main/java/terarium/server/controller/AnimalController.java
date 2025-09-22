package terarium.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import terarium.server.dto.Animal.CreateAnimalDto;
import terarium.server.dto.Animal.GetAnimalDto;
import terarium.server.dto.Animal.UpdateAnimalDto;
import terarium.server.service.AnimalService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class AnimalController {
    
    @Autowired
    private AnimalService animalService;
    
    @GetMapping("/animal")
    public List<GetAnimalDto> GetAllAnimals() {
        return animalService.GetAllAnimals();
    }
    
    @GetMapping("/animal/{animalId}")
    public GetAnimalDto getAnimalById(@PathVariable int animalId) {
        return animalService.GetAnimalById(animalId);
    }
    
    @PostMapping("/animal")
    public GetAnimalDto createAnimal(@RequestBody CreateAnimalDto createAnimalDto) {
        return animalService.CreateAnimal(createAnimalDto);
    }
    
    @DeleteMapping("/animal/{animalId}")
    public GetAnimalDto deleAnimal(@PathVariable("animalId") int animalId){
        return animalService.DeleteAnimal(animalId);
    }
    
    @PutMapping("animal/{animalId}")
    public GetAnimalDto updateAnimal(@PathVariable int animalId, @RequestBody UpdateAnimalDto entity) {
        return animalService.UpdateAnimal(entity, animalId);
    }
}