package terarium.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.model.Animal;
import terarium.server.dto.Animal.UpdateAnimalDto;
import terarium.server.repository.AnimalRepository;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    
    public List<Animal> GetAllAnimals(){
        return animalRepository.findAll();
    }
    
    public Animal GetAnimalById(int id){
        Optional<Animal> animal = animalRepository.findById(id);
        
        if (animal.isPresent()) return animal.get();
        
        return null;
    }
    
    public Animal CreateAnimal(Animal Animal){
        return animalRepository.save(Animal);
    }
    
    public Animal DeleteAnimal(int id){
        Optional<Animal> animal = animalRepository.findById(id);
        
        animalRepository.deleteById(id);
        
        return animal.isPresent() ? animal.get() : null;
    }
    
    public Animal UpdateAnimal(UpdateAnimalDto updateAnimalDto, int id){
        animalRepository.deleteById(id);
        
        Animal animal = new Animal().FromDto(updateAnimalDto);
        
        animal.setId(id);
        
        return animalRepository.save(animal);
    }
}
