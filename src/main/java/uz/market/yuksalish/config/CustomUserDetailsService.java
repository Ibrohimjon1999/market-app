package uz.market.yuksalish.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.market.yuksalish.domain.Role;
import uz.market.yuksalish.domain.enumation.Status;
import uz.market.yuksalish.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowerCaseUsername = username.toLowerCase();
        return userRepository
                .findByUserName(lowerCaseUsername)
                .map(user -> createSpringSecurity(lowerCaseUsername, user))
                .orElseThrow(() -> new UserNotActivatedException("User " + username + " was not activated!"));
    }

    private User createSpringSecurity(String username, uz.market.yuksalish.domain.User user) {
        if (user.getStatus() != Status.Active) {
            throw new UserNotActivatedException("User " + username + " was not activated!");
        }

        List<GrantedAuthority> grantedAuthorities = user
                .getRoles()
                .stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(username, user.getPassword(), grantedAuthorities);
    }
}
