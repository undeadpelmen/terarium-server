package terarium.server.controller;

import java.io.IOException;

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
import terarium.server.dto.Response.ErrorDto;
import terarium.server.dto.Response.ListResponseDto;
import terarium.server.dto.Response.OkResponseDto;
import terarium.server.error.EntityCreateException;
import terarium.server.error.EntityDeleteException;
import terarium.server.error.EntityGetException;
import terarium.server.error.EntityUpdateException;
import terarium.server.model.Animal;
import terarium.server.service.AnimalService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public ListResponseDto GetAllAnimals() throws IOException {
        return new ListResponseDto(animalService.getAllAnimals());
    }
    
    @GetMapping("/animals/{animalId}")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseAnimalDto getAnimalById(@PathVariable int animalId) throws Exception {
        
        Animal animal = animalService.getAnimalById(animalId);
        
        return new ResponseAnimalDto(animal);
        
    }
    
    @PostMapping("/animals")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    public ResponseAnimalDto createAnimal(@RequestBody @Schema(implementation = CreateAnimalDto.class) CreateAnimalDto createAnimalDto) throws Exception {
        
        Animal animal = Animal.fromDto(createAnimalDto);
        
        animal = animalService.createAnimal(animal);
        
        return new ResponseAnimalDto(animal);
        
    }
    
    @DeleteMapping("/animals/{animalId}")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public OkResponseDto deleAnimal(@PathVariable("animalId") int animalId) throws Exception {
        
        animalService.deleteAnimal(animalId);
        
        return new OkResponseDto("Animal created");
        
    }
    
    @PutMapping("animals/{animalId}")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    public ResponseAnimalDto updateAnimal(@PathVariable int animalId, @RequestBody @Schema(implementation = UpdateAnimalDto.class) UpdateAnimalDto updateAnimalDto) throws Exception {
        Animal animal = Animal.fromDto(updateAnimalDto);
        
        animal = animalService.updateAnimal(animal, animalId);
        
        return new ResponseAnimalDto(animal);
    }
    
    @ExceptionHandler(EntityGetException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityGetExceptionHandler(Exception e) {
        log.debug("Get Animal Exception", e);
        
        return new ErrorDto("Cant get Animal");
    }
    
    @ExceptionHandler(EntityCreateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto EntityCreateExceptionHandler(Exception e) {
        log.debug("Create Animal Exception", e);
        
        return new ErrorDto("Cant create Animal");
    }
    
    @ExceptionHandler(EntityDeleteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityDeleteExceptionHandler(Exception e) {
        log.debug("Delete Animal Exception", e);
        
        return new ErrorDto("Cant delete Animal");
    }
    
    @ExceptionHandler(EntityUpdateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityUpdateExceptionHandler(Exception e) {
        log.debug("Update Animal Exception", e);
        
        return new ErrorDto("Cant update Animal");
    }
}