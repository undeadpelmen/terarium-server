package terarium.server.controller;

import java.sql.Timestamp;

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
import terarium.server.dto.User.CreateUserDto;
import terarium.server.dto.User.UpdateUserDto;
import terarium.server.dto.User.ResponseUserDto;
import terarium.server.model.User;
import terarium.server.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    
    @GetMapping("/users")
    public ListResponseDto GetAllUsers() {
        return new ListResponseDto(userService.getAllUsers(), HttpStatus.OK);
    }
    
    @GetMapping("/users/{userId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseUserDto getUserById(@PathVariable int userId) throws Exception {
        User user = userService.getUserById(userId);
        
        return new ResponseUserDto(user, HttpStatus.OK);
    }
    
    @PostMapping("/users")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponse(responseCode = "404", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseUserDto createUser(@RequestBody @Schema(implementation = CreateUserDto.class) CreateUserDto createUserDto) throws Exception {
        User user = User.fromDto(createUserDto);
        
        user = userService.createUser(user);
        
        return new ResponseUserDto(user, HttpStatus.OK);
    }
    
    @DeleteMapping("/users/{userId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public OkResponseDto deleUser(@PathVariable("userId") int userId) throws Exception {
        
        userService.deleteUser(userId);
        
        return new OkResponseDto("User dleted", HttpStatus.OK);
    }
    
    @PutMapping("users/{userId}")
    @ApiResponses(@ApiResponse(responseCode = "200",  content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    public ResponseUserDto updateUser(@PathVariable int userId, @RequestBody @Schema(implementation = UpdateUserDto.class) UpdateUserDto updateUserDto) throws Exception {
        User user = User.fromDto(updateUserDto);
        
        user = userService.updateUser(user, userId);
        
        return new ResponseUserDto(user, HttpStatus.OK);
    }
    
    @ExceptionHandler(Exception.class)
    public ErrorDto ExceptionHandler(Exception e) {
        log.error(e.getClass().getSimpleName(), e.getMessage());
        
        log.debug("error", e);
        
        return new ErrorDto(400, "error", "Something went wrong", new Timestamp(System.currentTimeMillis()));
    }
}