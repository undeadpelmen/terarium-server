package terarium.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import terarium.server.error.EntityCreateException;
import terarium.server.error.EntityDeleteException;
import terarium.server.error.EntityGetException;
import terarium.server.error.EntityUpdateException;
import terarium.server.model.User;
import terarium.server.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers() throws IOException {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new EntityGetException();
        }
    }
    
    public User getUserById(int id) throws IOException {
        Optional<User> user = userRepository.findById(id);
        
        if (!user.isPresent()) throw new EntityGetException();
        
        return user.get();
    }
    
    public User createUser(User user) throws IOException {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new EntityCreateException();
        }
    }
    
    public void deleteUser(int id) throws IOException {
        Optional<User> user = userRepository.findById(id);
        
        if (!user.isPresent()) throw new EntityDeleteException();
        
        userRepository.deleteById(id);
    }
    
    public User updateUser(User user, int id) throws IOException {
        try {
            user.setId(id);
            
            return userRepository.save(user);
        } catch (Exception e) {
            throw new EntityUpdateException();
        }
    }
}
