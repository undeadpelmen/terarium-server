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
import terarium.server.dto.User.CreateUserDto;
import terarium.server.dto.User.UpdateUserDto;
import terarium.server.dto.Error.ErrorDto;
import terarium.server.model.User;
import terarium.server.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Tag(name = "User")
@Schema(name = "User controller", description = "Controller for CRUD requests ")
public class UserController {
    Logger log = ServerApplication.log;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/user")
    public List<User> GetAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/user/{userId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        try {
            User user = userService.getUserById(userId);
            
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get user by id exseption", e);
            
            ErrorDto error = new ErrorDto(404, "USER NOT FOUND", "Can't find user with this Id", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/user")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createUser(@RequestBody @Schema(implementation = CreateUserDto.class) CreateUserDto createUserDto) {
        try {
            User user = User.fromDto(createUserDto);
            
            user = userService.createUser(user);
            
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Create user by id exseption", e);
            
            ErrorDto error = new ErrorDto(400, "POSHEL NAXER", "Can't save this user to DB", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/user/{userId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> deleUser(@PathVariable("userId") int userId){
        try {
            userService.deleteUser(userId);
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Delete user by id exseption", e);
            
            ErrorDto error = new ErrorDto(404, "USER NOT FOUND", "Can't delete user with this Id", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("user/{userId}")
    @ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true, content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody @Schema(implementation = UpdateUserDto.class) UpdateUserDto updateUserDto) {
        try {
            User user = User.fromDto(updateUserDto);
            
            user = userService.updateUser(user, userId);
            
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Update user by id exseption", e);
            
            ErrorDto error = new ErrorDto(400, "POSHEL NAXER", "Can't update this user to DB", new Timestamp(System.currentTimeMillis()));
            
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}