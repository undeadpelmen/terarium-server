package terarium.server.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.ServerApplication;
import terarium.server.model.Terarium;
import terarium.server.repository.TerariumRepository;

@Service
public class TerariumService {
    private Logger log = ServerApplication.log;
    
    @Autowired
    private TerariumRepository terariumRepository;
    
    public List<Terarium> GetAllTerarium(){
        return terariumRepository.findAll();
    }
    
    public Terarium GetTerariumById(int id){
        Optional<Terarium> terarium = terariumRepository.findById(id);
        
        if (terarium.isPresent()) return terarium.get();
        
        return null;
    }
    
    public Terarium CreateTerarium(Terarium terarium){
        try{
            return terariumRepository.save(terarium);
        } catch(Error e) {
            log.error("Ceate terarium error", e);
            
            return null;
        }
    }
    
    public Terarium DeleteTerarium(int id){
        Optional<Terarium> terarium = terariumRepository.findById(id);
        
        if (terarium.isEmpty()) return null;
        
        terariumRepository.deleteById(id);
        
        return terarium.get();
    }
    
    public Terarium UpdateTerarium(Terarium terarium, int id){
        try{
            terarium.setId(id);
            
            return terariumRepository.save(terarium);
        } catch (Error e) {
            log.error("Update Terarium error", e);
            
            return null;
        }
    }
}
