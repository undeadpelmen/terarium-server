package terarium.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.model.Terarium;
import terarium.server.repository.TerariumRepository;

@Service
public class TerariumService {
    @Autowired
    private TerariumRepository terariumRepository;
    
    public List<Terarium> getAllTerariums(){
        return terariumRepository.findAll();
    }
    
    public Terarium getTerariumById(int id) throws IOException {
        Optional<Terarium> terarium = terariumRepository.findById(id);
        
        if (!terarium.isPresent()) throw new IOException();
        
        return terarium.get();
    }
    
    public List<Terarium> getTerariumsByAftorId(int aftorId) {
        return terariumRepository.findByAftorId(aftorId);
    }
    
    public Terarium createTerarium(Terarium terarium) throws IOException {
        return terariumRepository.save(terarium);
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
