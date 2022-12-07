package springlogin.controllers;

import springlogin.exceptions.NotFoundException;
import springlogin.models.User;
import springlogin.payload.request.LoginRequest;
import springlogin.payload.request.SignupRequest;
import springlogin.payload.response.MessageResponse;
import springlogin.payload.response.Response;
import springlogin.repositories.UserRepository;
import springlogin.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    final UserRepository userRepository;
    final AuthenticationManager authenticationManager;
    final PasswordEncoder encoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder(11);
    }

    // login

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

        // check user exists
        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username not found - please register"));
        }

        // check passwords match
        if (!encoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Password incorrect - please try again"));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new Response(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()));
    }

    // Register

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        // check passwords match
        if(!signupRequest.getPassword().equals(signupRequest.getPasswordConfirm())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Passwords do not match!"));
        }

        // make sure user does not already exist
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signupRequest.getUsername(),
                        signupRequest.getEmail(),
                        encoder.encode(signupRequest.getPassword()));


        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    // return all users

    @GetMapping(value={"/","/list"} )
    public @ResponseBody List<User> list(){
        return userRepository.findAll();
    }

    // return user by id

    @GetMapping(value="/user/findById")
    public @ResponseBody User findById(@RequestParam("id") Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Error: User Id : %d is not found.",id)));
    }

    // return user by firstname

    @GetMapping(value="/user/findByUsername/")
    public @ResponseBody User findByUsername(@RequestParam("username") String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: Username : ["+username+"] is not found."));
    }

    // delete by id

    @DeleteMapping(value="/user/deleteById")
    public ResponseEntity<MessageResponse> deleteById(@RequestParam("id") Long id){

        logger.info("delete User by id = "+id);
        if(!userRepository.existsById(id))
            return ResponseEntity.ok(new MessageResponse("Error: Cannot delete User with id: "+id));

        userRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("User deleted with id: " + id));
    }

    // edit/update a user record - only if record with id exists

    @PostMapping(value="/update")
    public @ResponseBody ResponseEntity<MessageResponse> update(@ModelAttribute User user){
        Long id = user.getId();

        // check if exists first
        // then update

        if(!userRepository.existsById(id))
            ResponseEntity.ok(new MessageResponse("Error: Id does not exist ["+id+"] -> cannot update record"));

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User record updated"));
    }

}
