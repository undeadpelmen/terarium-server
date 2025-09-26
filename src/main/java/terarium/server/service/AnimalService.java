package terarium.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.model.Animal;
import terarium.server.repository.AnimalRepository;

@Service
public class AnimalService {
    private Logger log = ServerApplication.log;
    
    @Autowired
    private AnimalRepository animalRepository;
    
    public List<Animal> getAllAnimals(){
        return animalRepository.findAll();
    }
    
    public Animal getAnimalById(int id) throws IOException {
        Optional<Animal> animal = animalRepository.findById(id);
        
        if (!animal.isPresent()) throw new IOException();
        
        return animal.get();
    }
    
    public Animal createAnimal(Animal animal) throws IOException {
        return animalRepository.save(animal);
    }
    
    public void deleteAnimal(int id) throws IOException {
        Optional<Animal> animal = animalRepository.findById(id);
        
        if (!animal.isPresent()) throw new IOException();
        
        animalRepository.deleteById(id);
    }
    
    public Animal updateAnimal(Animal animal, int id){
        animal.setId(id);
        
        return animalRepository.save(animal);
    }
}
