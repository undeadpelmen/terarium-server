package terarium.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.error.EntityCreateException;
import terarium.server.error.EntityDeleteException;
import terarium.server.error.EntityGetException;
import terarium.server.error.EntityUpdateException;
import terarium.server.model.Animal;
import terarium.server.repository.AnimalRepository;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    
    public List<Animal> getAllAnimals() throws IOException {
        try {
            return animalRepository.findAll();
        } catch(Exception e) {
            throw new EntityGetException();
        }
    }
    
    public Animal getAnimalById(int id) throws IOException {
        Optional<Animal> animal = animalRepository.findById(id);
        
        if (!animal.isPresent()) throw new EntityGetException("animal get exception");
        
        return animal.get();
    }
    
    public Animal createAnimal(Animal animal) throws IOException {
        try{
            return animalRepository.save(animal);
        } catch(Exception e) {
            throw new EntityCreateException();
        }
    }
    
    public void deleteAnimal(int id) throws IOException {
        Optional<Animal> animal = animalRepository.findById(id);
        
        if (!animal.isPresent()) throw new EntityDeleteException();
        
        animalRepository.deleteById(id);
    }
    
    public Animal updateAnimal(Animal animal, int id) throws IOException {
        animal.setId(id);
        
        try{
            return animalRepository.save(animal);
        } catch (Exception e) {
            throw new EntityUpdateException();
        }
    }
}
