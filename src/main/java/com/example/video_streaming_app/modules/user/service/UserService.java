package com.example.video_streaming_app.modules.user.service;

import com.example.video_streaming_app.modules.user.api.dto.request.user.UserCreateRequest;
import com.example.video_streaming_app.modules.user.api.dto.request.user.UserUpdateRequest;
import com.example.video_streaming_app.modules.user.api.dto.user.UserDto;
import com.example.video_streaming_app.modules.user.api.mappers.UserMapper;
import com.example.video_streaming_app.modules.user.domain.User;
import com.example.video_streaming_app.modules.user.repository.RoleRepository;
import com.example.video_streaming_app.modules.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(roleRepository.findByName("USER").get());
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException(username));
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    public UserDto updateUser(Long id, String loggedUsername, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new UsernameNotFoundException(loggedUsername));

        if(!user.getUsername().equals(loggedUsername)) {
            throw new SecurityException("You are not logged in");
        }

        if(request.getUsername() != null)
            user.setUsername(request.getUsername());
        if(request.getPassword() != null)
            user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(Long id, Authentication authentication) {
        String loggedUsername = authentication.getName();
        User user = userRepository.findById(id)
                .orElseThrow( () -> new UsernameNotFoundException(loggedUsername));

        if(!user.getUsername().equals(loggedUsername)) {
            throw new SecurityException("You are not logged in");
        }

        userRepository.deleteById(id);
    }






}
