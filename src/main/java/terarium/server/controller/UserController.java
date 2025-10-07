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
import terarium.server.dto.Response.ErrorDto;
import terarium.server.dto.Response.ListResponseDto;
import terarium.server.dto.Response.OkResponseDto;
import terarium.server.dto.User.CreateUserDto;
import terarium.server.dto.User.UpdateUserDto;
import terarium.server.error.EntityCreateException;
import terarium.server.error.EntityDeleteException;
import terarium.server.error.EntityGetException;
import terarium.server.error.EntityUpdateException;
import terarium.server.dto.User.ResponseUserDto;
import terarium.server.model.User;
import terarium.server.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Tag(name = "User")
@Schema(name = "User controller", description = "Controller for CRUD requests ")
public class UserController {
    Logger log = ServerApplication.log;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    public ListResponseDto GetAllUsers() throws IOException {
        return new ListResponseDto(userService.getAllUsers());
    }
    
    @GetMapping("/users/{userId}")
    @ApiResponse(responseCode = "200",  useReturnTypeSchema = true)
    public ResponseUserDto getUserById(@PathVariable int userId) throws Exception {
        User user = userService.getUserById(userId);
        
        return new ResponseUserDto(user);
    }
    
    @PostMapping("/users")
    @ApiResponse(responseCode = "201",  useReturnTypeSchema = true)
    public ResponseUserDto createUser(@RequestBody @Schema(implementation = CreateUserDto.class) CreateUserDto createUserDto) throws Exception {
        User user = User.fromDto(createUserDto);
        
        user = userService.createUser(user);
        
        return new ResponseUserDto(user);
    }
    
    @DeleteMapping("/users/{userId}")
    @ApiResponse(responseCode = "200",  useReturnTypeSchema = true)
    public OkResponseDto deleUser(@PathVariable int userId) throws Exception {
        
        userService.deleteUser(userId);
        
        return new OkResponseDto("User dleted");
    }
    
    @PutMapping("users/{userId}")
    @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    public ResponseUserDto updateUser(@PathVariable int userId, @RequestBody @Schema(implementation = UpdateUserDto.class) UpdateUserDto updateUserDto) throws Exception {
        User user = User.fromDto(updateUserDto);
        
        user = userService.updateUser(user, userId);
        
        return new ResponseUserDto(user);
    }
    
    @ExceptionHandler(EntityGetException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityGetExceptionHandler(Exception e) {
        log.debug("Get User Exception", e);
        
        return new ErrorDto("Cant get User");
    }
    
    @ExceptionHandler(EntityCreateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto EntityCreateExceptionHandler(Exception e) {
        log.debug("Create User Exception", e);
        
        return new ErrorDto("Cant create User");
    }
    
    @ExceptionHandler(EntityDeleteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityDeleteExceptionHandler(Exception e) {
        log.debug("Delete User Exception", e);
        
        return new ErrorDto("Cant delete User");
    }
    
    @ExceptionHandler(EntityUpdateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto EntityUpdateExceptionHandler(Exception e) {
        log.debug("Update User Exception", e);
        
        return new ErrorDto("Cant update User");
    }
}