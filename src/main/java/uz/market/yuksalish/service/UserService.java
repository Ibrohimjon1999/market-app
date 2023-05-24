package uz.market.yuksalish.service;

import jakarta.servlet.http.PushBuilder;
import org.springframework.stereotype.Service;
import uz.market.yuksalish.domain.User;
import uz.market.yuksalish.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
