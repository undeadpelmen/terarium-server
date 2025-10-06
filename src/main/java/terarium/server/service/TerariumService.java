package terarium.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.error.EntityCreateException;
import terarium.server.error.EntityGetException;
import terarium.server.model.Terarium;
import terarium.server.repository.TerariumRepository;

@Service
public class TerariumService {
    @Autowired
    private TerariumRepository terariumRepository;
    
    public List<Terarium> getAllTerariums() throws IOException {
        try {
            return terariumRepository.findAll();
        } catch(Exception e) {
            throw new EntityGetException();
        }
    }
    
    public Terarium getTerariumById(int id) throws IOException {
        Optional<Terarium> terarium = terariumRepository.findById(id);
        
        if (!terarium.isPresent()) throw new EntityGetException();
        
        return terarium.get();
    }
    
    public List<Terarium> getTerariumsByAftorId(int aftorId) throws IOException {
        try {
            return terariumRepository.findByAftorId(aftorId);
        } catch(Exception e) {
            throw new EntityGetException();
        }
    }
    
    public Terarium getTerariumsByMac(String mac) throws IOException {
        Optional<Terarium> terarium = terariumRepository.findByMac(mac);
        
        if (!terarium.isPresent()) throw new EntityGetException();
        
        return terarium.get();
    }
    
    public Terarium createTerarium(Terarium terarium) throws IOException {
        try {
            return terariumRepository.save(terarium);
        } catch (Exception e) {
            throw new EntityCreateException();
        }
    }
    
    public void deleteTerarium(int id) throws IOException {
        Optional<Terarium> terarium = terariumRepository.findById(id);
        
        if (!terarium.isPresent()) throw new IOException();
        
        terariumRepository.deleteById(id);
    }
    
    public Terarium updateTerarium(Terarium terarium, int id){
        terarium.setId(id);
        
        return terariumRepository.save(terarium);
    }
}
