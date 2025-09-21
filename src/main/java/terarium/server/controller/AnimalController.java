package terarium.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import terarium.server.service.AnimalService;

@RestController
public class AnimalController {
    
    @Autowired
    private AnimalService animalService;
}
