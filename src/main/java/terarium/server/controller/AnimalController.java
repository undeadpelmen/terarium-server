package terarium.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import terarium.server.dto.Animal.CreateAnimalDto;
import terarium.server.dto.Animal.UpdateAnimalDto;
import terarium.server.model.Animal;
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
    public List<Animal> GetAllAnimals() {
        return animalService.GetAllAnimals();
    }
    
    @GetMapping("/animal/{animalId}")
    public Animal getAnimalById(@PathVariable int animalId) {
        return animalService.GetAnimalById(animalId);
    }
    
    @PostMapping("/animal")
    public Animal createAnimal(@RequestBody CreateAnimalDto createAnimalDto) {
        Animal animal = new Animal().FromDto(createAnimalDto);
        
        return animalService.CreateAnimal(animal);
    }
    
    @DeleteMapping("/animal/{animalId}")
    public Animal deleAnimal(@PathVariable("animalId") int animalId){
        return animalService.DeleteAnimal(animalId);
    }
    
    @PutMapping("animal/{animalId}")
    public Animal updateAnimal(@PathVariable int animalId, @RequestBody UpdateAnimalDto updateAnimalDto) {
        return animalService.UpdateAnimal(updateAnimalDto, animalId);
    }
}