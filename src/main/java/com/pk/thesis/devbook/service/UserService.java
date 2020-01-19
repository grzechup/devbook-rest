package com.pk.thesis.devbook.service;

import com.pk.thesis.devbook.models.dto.UserDTO;
import com.pk.thesis.devbook.models.entity.User;
import com.pk.thesis.devbook.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private Type userDtoType  = new TypeToken<List<UserDTO>>(){}.getType();
    private Type pageDtoType = new TypeToken<Page<UserDTO>>(){}.getType();

    @Autowired
    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("User not found"));
        UserDTO dto  = modelMapper.map(user, UserDTO.class);
        return dto;
    }

    public User addFriend(String username) {
        return null;
    }

    public Page<UserDTO> searchUser(String username, PageRequest pageable) {
        return userRepository.searchUsersByUsername(username, pageable)
                        .map(user -> modelMapper.map(user, UserDTO.class));
    }
}
