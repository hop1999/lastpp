package webi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import webi.model.User;
import webi.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final UserService userService;

    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> allUsers = userService.listUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable int id) {
        Optional<User> user = userService.show(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/users")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/api/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable int id) {
        userService.update(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ModelAndView printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        model.addAttribute("messages", messages);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/api/auth")
    public ResponseEntity<User> getAuthUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}
