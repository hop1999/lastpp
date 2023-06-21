package webi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webi.model.User;
import webi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> show(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserById(int id) {
        User user = null;
        Optional<User> userFromBD = userRepository.findById(id);
        if (userFromBD.isPresent()) {
            user = userFromBD.get();
        }
        return user;
    }

    @Override
    @Transactional
    public void update(User user, int id) {
        if (user.getPassword().isEmpty()) {
            user.setPassword(findUserById(id).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

}

