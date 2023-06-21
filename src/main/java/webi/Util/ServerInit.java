package webi.Util;

import org.springframework.stereotype.Component;
import webi.model.Role;
import webi.model.User;
import webi.service.RoleServiceImpl;
import webi.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class ServerInit {
    private final UserService userService;
    private final RoleServiceImpl roleService;

    public ServerInit(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Set<Role> adminRole = new HashSet<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");

        adminRole.add(roleAdmin);
        adminRole.add(roleUser);

        Set<Role> userRole = new HashSet<>();
        userRole.add(roleUser);

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        User admin = new User("admin", "admin", 24, "admin@mail.ru", "qwerty111", adminRole);
        User user = new User("user", "user", 28, "user@mail.ru", "qwerty222", userRole);

        userService.add(admin);
        userService.add(user);

    }
}
