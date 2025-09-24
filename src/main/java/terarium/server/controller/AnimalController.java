package terarium.server.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import terarium.server.dto.Animal.CreateAnimalDto;
import terarium.server.dto.Animal.UpdateAnimalDto;
import terarium.server.dto.Error.ErrorDto;
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
    @Autowired
    private AnimalService animalService;
    
    @GetMapping("/animal")
    public List<Animal> GetAllAnimals() {
        return animalService.GetAllAnimals();
    }
    
    @GetMapping("/animal/{animalId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Animal.class))))
    @ApiResponse(responseCode = "404", description = "Animal not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getAnimalById(@PathVariable int animalId) {
        Animal animal = animalService.GetAnimalById(animalId);
        
        if (animal != null) {
            return new ResponseEntity<>(animal,HttpStatus.OK);
        } else {
            ErrorDto error = new ErrorDto(404, "ANIMAL NOT FOUND", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/animal")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Animal.class))))
    @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createAnimal(@RequestBody @Schema(implementation = CreateAnimalDto.class) CreateAnimalDto createAnimalDto) {
        Animal animal = new Animal().FromDto(createAnimalDto);
        
        animal = animalService.CreateAnimal(animal);
        
        if (animal != null) {
            return new ResponseEntity<>(animal,HttpStatus.OK);
        } else {
            ErrorDto error = new ErrorDto(400, "POSHEL HAXER", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/animal/{animalId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Animal.class))))
    @ApiResponse(responseCode = "404", description = "Animal not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> deleAnimal(@PathVariable("animalId") int animalId){
        Animal animal = animalService.DeleteAnimal(animalId);
        
        if (animal != null) {
            return new ResponseEntity<>(animal,HttpStatus.OK);
        } else {
            ErrorDto error = new ErrorDto(404, "ANIMAL NOT FOUND", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("animal/{animalId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Animal.class))))
    @ApiResponse(responseCode = "404", description = "Animal not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> updateAnimal(@PathVariable int animalId, @RequestBody @Schema(implementation = UpdateAnimalDto.class) UpdateAnimalDto updateAnimalDto) {
        Animal animal = new Animal().FromDto(updateAnimalDto);
        
        animal.setId(animalId);
        
        animal = animalService.UpdateAnimal(animal);
        
        if (animal != null) {
            return new ResponseEntity<>(animal,HttpStatus.OK);
        } else {
            ErrorDto error = new ErrorDto(404, "ANIMAL NOT FOUND", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}