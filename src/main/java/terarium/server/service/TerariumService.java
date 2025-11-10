package terarium.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.error.EntityCreateException;
import terarium.server.error.EntityDeleteException;
import terarium.server.error.EntityGetException;
import terarium.server.model.Terarium;
import terarium.server.repository.TerariumRepository;

@Service
public class TerariumService {
    @Autowired
    private TerariumRepository terariumRepository;
    
    public List<Terarium> getAllTerariums() throws EntityGetException {
        try {
            return terariumRepository.findAll();
        } catch(Exception e) {
            throw new EntityGetException("Cant get Terariums", e);
        }
    }
    
    public Terarium getTerariumById(int id) throws EntityGetException {
        Optional<Terarium> terarium = terariumRepository.findById(id);
        
        if (!terarium.isPresent()) throw new EntityGetException("Terarium with this Id not found");
        
        return terarium.get();
    }
    
    public List<Terarium> getTerariumsByAftorId(int aftorId) throws EntityGetException {
        try {
            return terariumRepository.findByAftorId(aftorId);
        } catch(Exception e) {
            throw new EntityGetException("Terarium with this Aftor Id not found", e);
        }
    }
    
    public Terarium getTerariumsByMac(String mac) throws EntityGetException {
        Optional<Terarium> terarium = terariumRepository.findByMac(mac);
        
        if (!terarium.isPresent()) throw new EntityGetException("Terarium with this MAC not found");
        
        return terarium.get();
    }
    
    public Terarium createTerarium(Terarium terarium) throws EntityCreateException {
        try {
            return terariumRepository.save(terarium);
        } catch (Exception e) {
            throw new EntityCreateException("Cant create terarium with this args", e);
        }
    }
    
    public void deleteTerarium(int id) throws EntityDeleteException {
        Optional<Terarium> terarium = terariumRepository.findById(id);
        
        if (!terarium.isPresent()) throw new EntityDeleteException("Terarium with this Id not found");
        
        terariumRepository.deleteById(id);
    }
    
    public Terarium updateTerarium(Terarium terarium, int id){
        terarium.setId(id);
        
        return terariumRepository.save(terarium);
    }
}
