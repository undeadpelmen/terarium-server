package terarium.server.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import terarium.server.ServerApplication;
import terarium.server.dto.Response.ErrorDto;
import terarium.server.dto.Response.ListResponseDto;
import terarium.server.dto.Response.OkResponseDto;
import terarium.server.dto.Terarium.CreateTerariumDto;
import terarium.server.dto.Terarium.ResponseTerariumDto;
import terarium.server.dto.Terarium.UpdateTerariumDto;
import terarium.server.error.EntityCreateException;
import terarium.server.error.EntityDeleteException;
import terarium.server.error.EntityGetException;
import terarium.server.error.EntityUpdateException;
import terarium.server.model.Animal;
import terarium.server.model.Terarium;
import terarium.server.service.AnimalService;
import terarium.server.service.TerariumService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Tag(name = "Terarium")
@Schema(name = "Terarium controller", description = "Controller for CRUD requests ")
public class TerariumController {
    Logger log = ServerApplication.log;
    
    @Autowired
    private TerariumService terariumService;
    
    @Autowired
    private AnimalService animalService;
    
    @GetMapping("/terariums")
    public ListResponseDto GetAllTerariums() throws IOException {
        List<Terarium> terariums = terariumService.getAllTerariums();
        
        return new ListResponseDto(terariums);
    }
    
    @GetMapping("/terariums/{terariumId}")
    @ApiResponse(responseCode = "200",   useReturnTypeSchema = true)
    public ResponseTerariumDto getTerariumById(@PathVariable int terariumId) throws Exception {
        Terarium terarium = terariumService.getTerariumById(terariumId);
        
        return new ResponseTerariumDto(terarium);
    }
    
    @GetMapping("/terariums/aftor/{aftorId}")
    @ApiResponse(responseCode = "200",   useReturnTypeSchema = true)
    public ListResponseDto getTerariumByAftorId(@PathVariable int aftorId) throws IOException {
            List<Terarium> terariums = terariumService.getTerariumsByAftorId(aftorId);
            
            return new ListResponseDto(terariums);
    }
    
    @GetMapping("/terariums/mac/{mac}")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ResponseTerariumDto getTerariumsByMac(@PathVariable String mac) throws Exception {
        
        Terarium terarium = terariumService.getTerariumsByMac(mac);
        
        return new ResponseTerariumDto(terarium);
    }
    
    @PostMapping("/terariums")
    @ApiResponse(responseCode = "201",   useReturnTypeSchema = true)
    public ResponseTerariumDto createTerarium(@RequestBody @Schema(implementation = CreateTerariumDto.class) CreateTerariumDto createTerariumDto) throws IOException {
        try {
            Animal animal = animalService.getAnimalById(createTerariumDto.getAnimalId());
            
            Terarium terarium = Terarium.fromDto(createTerariumDto, animal);
            
            terarium = terariumService.createTerarium(terarium);
            
            return new ResponseTerariumDto(terarium);
        } catch (Exception e) {
            throw new EntityCreateException();
        }
    }
    
    @DeleteMapping("/terariums/{terariumId}")
    @ApiResponse(responseCode = "200",   useReturnTypeSchema = true)
    public OkResponseDto deleTerarium(@PathVariable int terariumId) throws Exception {
        terariumService.deleteTerarium(terariumId);
        
        return new OkResponseDto("Terarium deleted");
    }
    
    @PutMapping("terariums/{terariumId}")
    @ApiResponse(responseCode = "201",   useReturnTypeSchema = true)
    public ResponseTerariumDto updateTerarium(@PathVariable int terariumId, @RequestBody @Schema(implementation = UpdateTerariumDto.class) UpdateTerariumDto updateTerariumDto) throws Exception {
        try {
            Animal animal = animalService.getAnimalById(updateTerariumDto.getAnimalId());
            
            Terarium terarium = Terarium.fromDto(updateTerariumDto, animal);
            
            terarium = terariumService.updateTerarium(terarium, terariumId);
            
            return new ResponseTerariumDto(terarium);
        } catch (Exception e) {
            throw new EntityUpdateException();
        }
    }
    
    @ExceptionHandler(EntityGetException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityGetExceptionHandler(Exception e) {
        log.debug("Get Terarium Exception", e);
        
        return new ErrorDto("Cant get Terarium");
    }
    
    @ExceptionHandler(EntityCreateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto EntityCreateExceptionHandler(Exception e) {
        log.debug("Create Terarium Exception", e);
        
        return new ErrorDto("Cant create Terarium");
    }
    
    @ExceptionHandler(EntityDeleteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityDeleteExceptionHandler(Exception e) {
        log.debug("Delete Terarium Exception", e);
        
        return new ErrorDto("Cant delete Terarium");
    }
    
    @ExceptionHandler(EntityUpdateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityUpdateExceptionHandler(Exception e) {
        log.debug("Update Terarium Exception", e);
        
        return new ErrorDto("Cant update Terarium");
    }
}