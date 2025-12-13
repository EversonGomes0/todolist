package br.com.everson.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    
    @PostMapping("/")
    public UserModel create(@RequestBody UserModel userModel){
        UserModel user =this.userRepository.findByUserName(userModel.getUserName());
        if(user != null){
            System.out.println("User already exists!");
            throw new RuntimeException("User already exists!");
        }
        UserModel userCreated = this.userRepository.save(userModel);
        return userCreated;
        
    }
}
