package engine.services;

import engine.dtos.UserDto;
import engine.entities.User;
import engine.exceptions.UserWithEmailAlreadyExists;
import engine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserDto userDto) {
        Optional<User> checkByEmail = repository.findByEmail(userDto.getEmail());
        if (checkByEmail.isPresent()) {
            throw new UserWithEmailAlreadyExists(
                    String.format("User with the email [%s] already exists!", userDto.getEmail()));
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        repository.save(user);
    }

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    private Optional<String> getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.of(auth.getName());
    }

    public Optional<User> getCurrentUser() {
        Optional<String> optionalEmail = getCurrentUserName();
        if (optionalEmail.isEmpty()) {
            return Optional.empty();
        }
        return repository.findByEmail(optionalEmail.get());
    }
}