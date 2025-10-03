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
import terarium.server.dto.Terarium.CreateTerariumDto;
import terarium.server.dto.Terarium.UpdateTerariumDto;
import terarium.server.dto.Error.ErrorDto;
import terarium.server.model.Animal;
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
@Schema(name = "Terarium controller", description = "Controller for CRUD requests ")
public class TerariumController {
    Logger log = ServerApplication.log;
    
    @Autowired
    private TerariumService terariumService;
    
    @Autowired
    private AnimalService animalService;
    
    @GetMapping("/terariums")
    public List<Terarium> GetAllTerariums() {
        return terariumService.getAllTerariums();
    }
    
    @GetMapping("/terariums/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getTerariumById(@PathVariable int terariumId) {
        try {
            Terarium terarium = terariumService.getTerariumById(terariumId);
            
            return new ResponseEntity<>(terarium, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get terarium by id exseption", e);
            
            ErrorDto error = new ErrorDto(404, "TERARIUM NOT FOUND", "Can't find terarium with this Id", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/terariums/aftor/{aftorId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getTerariumByAftorId(@PathVariable int aftorId) {
        try {
            List<Terarium> terariums = terariumService.getTerariumsByAftorId(aftorId);
            
            return new ResponseEntity<>(terariums, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get terariums by aftor id exseption", e);
            
            ErrorDto error = new ErrorDto(404, "TERARIUM NOT FOUND", "Can't find terarium with this Id", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/terariums/mac/{mac}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Terarium.class)))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getTerariumsByMac(@PathVariable String mac){
        try {
            Terarium terarium = terariumService.getTerariumsByMac(mac);
            
            return new ResponseEntity<>(terarium, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Cant get Terariums by mac");
            
            ErrorDto error = new ErrorDto(404, "TERARIUM NOT FOUND", "Can't find terarium with this mac", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/terariums")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createTerarium(@RequestBody @Schema(implementation = CreateTerariumDto.class) CreateTerariumDto createTerariumDto) {
        try {
            Animal animal = animalService.getAnimalById(createTerariumDto.getAnimalId());
            
            Terarium terarium = Terarium.fromDto(createTerariumDto, animal);
            
            terarium = terariumService.createTerarium(terarium);
            
            return new ResponseEntity<>(terarium, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Create terarium by id exseption", e);
            
            ErrorDto error = new ErrorDto(400, "POSHEL NAXER", "Can't save this terarium to DB", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/terariums/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> deleTerarium(@PathVariable int terariumId){
        try {
            terariumService.deleteTerarium(terariumId);
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Delete terarium by id exseption", e);
            
            ErrorDto error = new ErrorDto(404, "TERARIUM NOT FOUND", "Can't delete terarium with this Id", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("terariums/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "400", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> updateTerarium(@PathVariable int terariumId, @RequestBody @Schema(implementation = UpdateTerariumDto.class) UpdateTerariumDto updateTerariumDto) {
        try {
            Animal animal = animalService.getAnimalById(updateTerariumDto.getAnimalId());
            
            Terarium terarium = Terarium.fromDto(updateTerariumDto, animal);
            
            terarium = terariumService.updateTerarium(terarium, terariumId);
            
            return new ResponseEntity<>(terarium, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update terarium by id exseption", e);
            
            ErrorDto error = new ErrorDto(400, "POSHEL NAXER", "Can't update this terarium to DB", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}