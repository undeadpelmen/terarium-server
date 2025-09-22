package terarium.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import terarium.server.dto.Animal.CreateAnimalDto;
import terarium.server.dto.Animal.GetAnimalDto;
import terarium.server.dto.Animal.UpdateAnimalDto;

@Service
public class AnimalService {
    public List<GetAnimalDto> GetAllAnimals(){
        return null;
    }
    
    public GetAnimalDto GetAnimalById(int id){
        return null;
    }
    
    public GetAnimalDto CreateAnimal(CreateAnimalDto createAnimalDto){
        return null;
    }
    
    public GetAnimalDto DeleteAnimal(int id){
        return null;
    }
    
    public GetAnimalDto UpdateAnimal(UpdateAnimalDto animal, int id){
        return null;
    }
}
