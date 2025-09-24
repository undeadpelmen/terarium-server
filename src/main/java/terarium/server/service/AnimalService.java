package terarium.server.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.model.Animal;
import terarium.server.ServerApplication;
import terarium.server.repository.AnimalRepository;

@Service
public class AnimalService {
    private Logger log = ServerApplication.log;
    
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
    
    public Animal CreateAnimal(Animal animal){
        try {
            return animalRepository.save(animal);
        } catch (Error e) {
            log.error("Crete Animal error", e);
            
            return null;
        }
    }
    
    public Animal DeleteAnimal(int id){
        Optional<Animal> animal = animalRepository.findById(id);
        
        if (animal.isEmpty()) return null;
        
        animalRepository.deleteById(id);
        
        return animal.get();
    }
    
    public Animal UpdateAnimal(Animal animal){
        try {
            return animalRepository.save(animal);
        } catch (Error e) {
            log.error("Update Animal error", e);
            
            return null;
        }
    }
}
