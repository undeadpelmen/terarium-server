package terarium.server.controller;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import terarium.server.ServerApplication;
import terarium.server.dto.Response.ErrorDto;
import terarium.server.dto.Response.ListResponseDto;
import terarium.server.dto.Response.OkResponseDto;
import terarium.server.dto.Terarium.CreateTerariumDto;
import terarium.server.dto.Terarium.ResponseTerariumDto;
import terarium.server.dto.Terarium.UpdateTerariumDto;
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
    public ListResponseDto GetAllTerariums() {
        List<Terarium> terariums = terariumService.getAllTerariums();
        
        return new ListResponseDto(terariums, HttpStatus.OK);
    }
    
    @GetMapping("/terariums/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseTerariumDto getTerariumById(@PathVariable int terariumId) throws Exception {
        Terarium terarium = terariumService.getTerariumById(terariumId);
        
        return new ResponseTerariumDto(terarium, HttpStatus.OK);
    }
    
    @GetMapping("/terariums/aftor/{aftorId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ListResponseDto getTerariumByAftorId(@PathVariable int aftorId) {
            List<Terarium> terariums = terariumService.getTerariumsByAftorId(aftorId);
            
            return new ListResponseDto(terariums, HttpStatus.OK);
    }
    
    @GetMapping("/terariums/mac/{mac}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Terarium.class)))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseTerariumDto getTerariumsByMac(@PathVariable String mac) throws Exception {
        
        Terarium terarium = terariumService.getTerariumsByMac(mac);
        
        return new ResponseTerariumDto(terarium, HttpStatus.OK);
    }
    
    @PostMapping("/terariums")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseTerariumDto createTerarium(@RequestBody @Schema(implementation = CreateTerariumDto.class) CreateTerariumDto createTerariumDto) throws Exception {
        Animal animal = animalService.getAnimalById(createTerariumDto.getAnimalId());
        
        Terarium terarium = Terarium.fromDto(createTerariumDto, animal);
        
        terarium = terariumService.createTerarium(terarium);
        
        return new ResponseTerariumDto(terarium, HttpStatus.OK);
    }
    
    @DeleteMapping("/terariums/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "404", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public OkResponseDto deleTerarium(@PathVariable int terariumId) throws Exception {
        terariumService.deleteTerarium(terariumId);
        
        return new OkResponseDto("Terarium deleted", HttpStatus.OK);
    }
    
    @PutMapping("terariums/{terariumId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = Terarium.class))))
    @ApiResponse(responseCode = "400", description = "Terarium not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseTerariumDto updateTerarium(@PathVariable int terariumId, @RequestBody @Schema(implementation = UpdateTerariumDto.class) UpdateTerariumDto updateTerariumDto) throws Exception {
        Animal animal = animalService.getAnimalById(updateTerariumDto.getAnimalId());
        
        Terarium terarium = Terarium.fromDto(updateTerariumDto, animal);
        
        terarium = terariumService.updateTerarium(terarium, terariumId);
        
        return new ResponseTerariumDto(terarium, HttpStatus.OK);
    }
    
    @ExceptionHandler(Exception.class)
    public ErrorDto ExceptionHandler(Exception e) {
        log.error(e.getClass().getSimpleName(), e.getMessage());
        
        log.debug("error", e);
        
        return new ErrorDto(400, "error", "Something went wrong", new Timestamp(System.currentTimeMillis()));
    }
}