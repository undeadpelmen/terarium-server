package terarium.server.controller;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import terarium.server.ServerApplication;
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
    Logger log = ServerApplication.log;
    
    @Autowired
    private AnimalService animalService;
    
    @GetMapping("/animal")
    public List<Animal> GetAllAnimals() {
        return animalService.getAllAnimals();
    }
    
    @GetMapping("/animal/{animalId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Animal.class))))
    @ApiResponse(responseCode = "404", description = "Animal not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getAnimalById(@PathVariable int animalId) {
        try {
            Animal animal = animalService.getAnimalById(animalId);
            
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get animal by id exseption", e);
            
            ErrorDto error = new ErrorDto(404, "ANIMAL NOT FOUND", "Can't find animal with this Id", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/animal")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Animal.class))))
    @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createAnimal(@RequestBody @Schema(implementation = CreateAnimalDto.class) CreateAnimalDto createAnimalDto) {
        try {
            Animal animal = Animal.fromDto(createAnimalDto);
            
            animal = animalService.createAnimal(animal);
            
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Create animal by id exseption", e);
            
            ErrorDto error = new ErrorDto(400, "POSHEL NAXER", "Can't save this animal to DB", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/animal/{animalId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Animal.class))))
    @ApiResponse(responseCode = "404", description = "Animal not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> deleAnimal(@PathVariable("animalId") int animalId){
        try {
            animalService.deleteAnimal(animalId);
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Delete animal by id exseption", e);
            
            ErrorDto error = new ErrorDto(404, "ANIMAL NOT FOUND", "Can't delete animal with this Id", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("animal/{animalId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Animal.class))))
    @ApiResponse(responseCode = "404", description = "Animal not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> updateAnimal(@PathVariable int animalId, @RequestBody @Schema(implementation = UpdateAnimalDto.class) UpdateAnimalDto updateAnimalDto) {
        try {
            Animal animal = Animal.fromDto(updateAnimalDto);
            
            animal = animalService.updateAnimal(animal, animalId);
            
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update animal by id exseption", e);
            
            ErrorDto error = new ErrorDto(400, "POSHEL NAXER", "Can't update this animal to DB", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}