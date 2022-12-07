package springlogin.services;

import springlogin.enums.MessageTypes;
import springlogin.exceptions.MyMessageResponse;
import springlogin.models.User;
import springlogin.models.UserModel;
import springlogin.payload.response.MessageResponse;
import springlogin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // return all Users

    public List<User> list(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) new MyMessageResponse("Error: No Users listed", MessageTypes.WARN);
        return users;
    }

    // return User by id

    public User findById( Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            new MyMessageResponse(String.format("User id: %d not found", id), MessageTypes.ERROR);
        return user.orElse(new User());
    }

    // return User by name

    public User findByName( UserModel userModel) {
        Optional<User> user = userRepository.findByUsername(userModel.getUsername());
        if(user.isEmpty()) new MyMessageResponse(String.format("User name: %s not found", userModel.getUsername()), MessageTypes.INFO);
        return user.orElse(new User());
    }

    // add new User

    public ResponseEntity<MessageResponse> add(UserModel userModel){

        if(userRepository.existsByUsername(userModel.getUsername()))
            return ResponseEntity.ok(new MyMessageResponse("Error: User already exists", MessageTypes.WARN));

        userRepository.save(userModel.translateModelToUser());
        return ResponseEntity.ok(new MyMessageResponse("new User added", MessageTypes.INFO));
    }

    // delete by id

    public ResponseEntity<MessageResponse> delete( User user){
        Long id = user.getId();
        if(!userRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Cannot delete User with id: "+id, MessageTypes.WARN));

        userRepository.deleteById(id);
        return ResponseEntity.ok(new MyMessageResponse("User deleted with id: " + id, MessageTypes.INFO));
    }

    // edit/update a User record - only if record with id exists

    public ResponseEntity<MessageResponse> update(Long id, User user){

        // check if exists first
        // then update

        if(!userRepository.existsById(id))
            return ResponseEntity.ok(new MyMessageResponse("Error: Id does not exist ["+id+"] -> cannot update record", MessageTypes.WARN));

        userRepository.save(user);
        return ResponseEntity.ok(new MyMessageResponse("User record updated", MessageTypes.INFO));
    }


}
