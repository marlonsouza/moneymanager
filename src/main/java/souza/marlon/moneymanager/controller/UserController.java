package souza.marlon.moneymanager.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import souza.marlon.moneymanager.dto.UserDto;
import souza.marlon.moneymanager.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto userDto){
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<UserDto> listAll(){
        return userService.findAll();
    }
}
