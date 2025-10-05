package terarium.server.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import terarium.server.ServerApplication;
import terarium.server.dto.Animal.CreateAnimalDto;
import terarium.server.dto.Animal.UpdateAnimalDto;
import terarium.server.dto.Animal.ResponseAnimalDto;
import terarium.server.dto.Response.ListResponseDto;
import terarium.server.dto.Response.OkResponseDto;
import terarium.server.model.Animal;
import terarium.server.service.AnimalService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Tag(name = "Animal")
@Schema(name = "Animal controller", description = "Controller for CRUD requests ")
public class AnimalController {
    Logger log = ServerApplication.log;
    
    @Autowired
    private AnimalService animalService;
    
    @GetMapping("/animals")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ListResponseDto GetAllAnimals() {
        return new ListResponseDto(animalService.getAllAnimals(), HttpStatus.OK);
    }
    
    @GetMapping("/animals/{animalId}")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseAnimalDto getAnimalById(@PathVariable int animalId) throws Exception {
        
        Animal animal = animalService.getAnimalById(animalId);
        
        return new ResponseAnimalDto(animal, HttpStatus.OK);
        
    }
    
    @PostMapping("/animals")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseAnimalDto createAnimal(@RequestBody @Schema(implementation = CreateAnimalDto.class) CreateAnimalDto createAnimalDto) throws Exception {
        
        Animal animal = Animal.fromDto(createAnimalDto);
        
        animal = animalService.createAnimal(animal);
        
        return new ResponseAnimalDto(animal, HttpStatus.OK);
        
    }
    
    @DeleteMapping("/animals/{animalId}")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public OkResponseDto deleAnimal(@PathVariable("animalId") int animalId) throws Exception {
        
        animalService.deleteAnimal(animalId);
        
        return new OkResponseDto("Animal created", HttpStatus.OK);
        
    }
    
    @PutMapping("animals/{animalId}")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseAnimalDto updateAnimal(@PathVariable int animalId, @RequestBody @Schema(implementation = UpdateAnimalDto.class) UpdateAnimalDto updateAnimalDto) throws Exception {
        Animal animal = Animal.fromDto(updateAnimalDto);
        
        animal = animalService.updateAnimal(animal, animalId);
        
        return new ResponseAnimalDto(animal, HttpStatus.OK);
    }
}