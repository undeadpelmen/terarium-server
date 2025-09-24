package terarium.server.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import terarium.server.dto.Error.ErrorDto;
import terarium.server.dto.Terarium.CreateTerariumDto;
import terarium.server.dto.Terarium.UpdateTerariumDto;
import terarium.server.model.Terarium;
import terarium.server.service.AnimalService;
import terarium.server.service.TerariumService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@Tag(name = "Terarium")
@Schema(name = "Terarium Controller", description = "Controller for CRUD requests")
public class TerariumController {
    @Autowired
    private TerariumService terariumService;
    
    @Autowired
    private AnimalService animalService;
    
    @GetMapping("terarium")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Terarium.class))))
    public ResponseEntity<?> getAllTerarium() {
        return new ResponseEntity<>(terariumService.GetAllTerarium(), HttpStatus.OK);
    }
    
    @GetMapping("terarium/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getTerariumById(@PathVariable("terariumId") int terariumId) {
        Terarium terarium = terariumService.GetTerariumById(terariumId);
        
        if (terarium != null) {
            return new ResponseEntity<>(terarium,HttpStatus.OK);
        } else {
            ErrorDto error = new ErrorDto(404, "TERARIUM NOT FOUND", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("terarium")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Bad request not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createTerarium(@RequestBody CreateTerariumDto createTerariumDto) {
        Terarium terarium = new Terarium();
        
        terarium.setName(createTerariumDto.getName());
        terarium.setAnimal(animalService.GetAnimalById(createTerariumDto.getAnimalId()));
        
        terarium = terariumService.CreateTerarium(terarium);
        
        if (terarium != null) {
            return new ResponseEntity<>(terarium,HttpStatus.OK);
        } else {
            ErrorDto error = new ErrorDto(400, "POSHEL HAXER", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("terarium/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> deleteTerarium(@PathVariable("terariumId") int terariumId){
        Terarium terarium = terariumService.DeleteTerarium(terariumId);
        
        if (terarium != null) {
            return new ResponseEntity<>(terarium,HttpStatus.OK);
        } else {
            ErrorDto error = new ErrorDto(404, "TERARIUM NOT FOUND", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("terarium/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> putMethodName(@PathVariable("terariumId") int terariumId, @RequestBody UpdateTerariumDto updateTerariumDto) {
        Terarium terarium = new Terarium();
        
        terarium.setAnimal(animalService.GetAnimalById(updateTerariumDto.getAnimalId()));
        terarium.setName(updateTerariumDto.getName());
        
        terarium = terariumService.UpdateTerarium(terarium, terariumId);
        
        if (terarium != null) {
            return new ResponseEntity<>(terarium,HttpStatus.OK);
        } else {
            ErrorDto error = new ErrorDto(404, "TERARIUM NOT FOUND", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
