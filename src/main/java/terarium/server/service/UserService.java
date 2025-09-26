package terarium.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.model.User;
import terarium.server.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    public User getUserById(int id) throws IOException {
        Optional<User> user = userRepository.findById(id);
        
        if (!user.isPresent()) throw new IOException();
        
        return user.get();
    }
    
    public User createUser(User user) throws IOException {
        return userRepository.save(user);
    }
    
    public void deleteUser(int id) throws IOException {
        Optional<User> user = userRepository.findById(id);
        
        if (!user.isPresent()) throw new IOException();
        
        userRepository.deleteById(id);
    }
    
    public User updateUser(User user, int id){
        user.setId(id);
        
        return userRepository.save(user);
    }
}
