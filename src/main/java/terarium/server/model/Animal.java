package terarium.server.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import terarium.server.dto.Animal.CreateAnimalDto;
import terarium.server.dto.Animal.UpdateAnimalDto;

@Entity
@Table(name="animal")
@Data
@NoArgsConstructor
@Tag(name = "Animal")
@Schema(description = "Animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(example = "666")
    private int id;
    
    @Column(name = "name",nullable = false,unique = true)
    @Schema(example = "Crested gecko")
    private String name;
    
    @Column(name = "lat_name",nullable = false,unique = true)
    @Schema(example = "Correloporhus ciliatus")
    private String lat_name;
    
    @Column(name = "day_max_t",nullable = false)
    @Schema(example = "28")
    private int day_max_t;
    
    @Column(name = "day_min_t",nullable = false)
    @Schema(example = "26")
    private int day_min_t;
    
    @Column(name = "night_max_t",nullable = false)
    @Schema(example = "24")
    private int night_max_t;
    
    @Column(name = "night_min_t",nullable = false)
    @Schema(example = "22")
    private int night_min_t;
    
    @Column(name = "uv_time",nullable = false)
    @Schema(example = "11.0")
    private float uv_time;
    
    @Column(name = "uv_req",nullable = false)
    @Schema(example = "5.0,13W")
    private String uv_req;
    
    @Column(name = "humidity_max",nullable = false)
    @Schema(example = "80")
    private int humidity_max;
    
    @Column(name = "humidity_min",nullable = false)
    @Schema(example = "60")
    private int humidity_min;
    
    @Column(name = "day_len",nullable = false)
    @Schema(example = "11.0")
    private float day_len;
    
    @Column(name = "fed_rate",nullable = false)
    @Schema(example = "2.0")
    private float feed_rate;
    
    public static Animal fromDto(CreateAnimalDto AnimalDto){
        Animal animal = new Animal();
        
        animal.setName(AnimalDto.getName());
        animal.setLat_name(AnimalDto.getLat_name());
        animal.setDay_len(AnimalDto.getDay_len());
        animal.setDay_max_t(AnimalDto.getDay_max_t());
        animal.setDay_min_t(AnimalDto.getDay_min_t());
        animal.setNight_max_t(AnimalDto.getNight_max_t());
        animal.setNight_min_t(AnimalDto.getNight_min_t());
        animal.setHumidity_max(AnimalDto.getHumidity_max());
        animal.setHumidity_min(AnimalDto.getHumidity_min());
        animal.setUv_req(AnimalDto.getUv_req());
        animal.setFeed_rate(AnimalDto.getFeed_rate());
        animal.setFood(AnimalDto.getFood());
        animal.setKide_feed_rate(AnimalDto.getKide_feed_rate());
        animal.setUv_time(AnimalDto.getUv_time());
        animal.setVitamins(AnimalDto.getVitamins());
        
        return animal;
    }
    
    public static Animal fromDto(UpdateAnimalDto AnimalDto){
        Animal animal = new Animal();
        
        animal.setName(AnimalDto.getName());
        animal.setLat_name(AnimalDto.getLat_name());
        animal.setDay_len(AnimalDto.getDay_len());
        animal.setDay_max_t(AnimalDto.getDay_max_t());
        animal.setDay_min_t(AnimalDto.getDay_min_t());
        animal.setNight_max_t(AnimalDto.getNight_max_t());
        animal.setNight_min_t(AnimalDto.getNight_min_t());
        animal.setHumidity_max(AnimalDto.getHumidity_max());
        animal.setHumidity_min(AnimalDto.getHumidity_min());
        animal.setUv_req(AnimalDto.getUv_req());
        animal.setFeed_rate(AnimalDto.getFeed_rate());
        animal.setFood(AnimalDto.getFood());
        animal.setKide_feed_rate(AnimalDto.getKide_feed_rate());
        animal.setUv_time(AnimalDto.getUv_time());
        animal.setVitamins(AnimalDto.getVitamins());
        
        return animal;
    }
}
