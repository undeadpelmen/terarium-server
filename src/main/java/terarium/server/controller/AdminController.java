package terarium.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import terarium.server.configuration.RabbitInitConfiguration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Panel")
public class AdminController {
    @Autowired
    private RabbitInitConfiguration rabbitInitConfiguration;
    
    @GetMapping("/termac")
    public Map<String, String> getTerariumsMac() {
        return rabbitInitConfiguration.getTerMac();
    }
    
}
