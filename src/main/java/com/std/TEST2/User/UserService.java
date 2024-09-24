package com.std.TEST2.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String nickname, String password){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
    public Principal getUser(String username){
        Optional<SiteUser> user = this.userRepository.findByusername(username);
        if(user.isPresent()){
            return (Principal) user.get();
        } else {
            throw new NoSuchElementException("site_user not found");
        }
    }
}
