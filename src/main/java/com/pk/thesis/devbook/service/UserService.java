package com.pk.thesis.devbook.service;

import com.pk.thesis.devbook.models.dto.UserDTO;
import com.pk.thesis.devbook.models.entity.InvitationsToFriends;
import com.pk.thesis.devbook.models.entity.User;
import com.pk.thesis.devbook.payload.request.InvitationUsernamesRequest;
import com.pk.thesis.devbook.repository.InvitationsToFriendsRepository;
import com.pk.thesis.devbook.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private InvitationsToFriendsRepository invitationsToFriendsRepository;
    private Type userDtoType = new TypeToken<List<UserDTO>>() {
    }.getType();
    private Type pageDtoType = new TypeToken<Page<UserDTO>>() {
    }.getType();

    @Autowired
    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper,
                       InvitationsToFriendsRepository invitationsToFriendsRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.invitationsToFriendsRepository = invitationsToFriendsRepository;
    }

    public UserDTO getUserDTO(String username) {
        return userRepository.findByUsername(username)
                .map(u -> modelMapper.map(u, UserDTO.class))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDTO inviteFriend(InvitationUsernamesRequest request) {
        User userToInvite = userRepository.findByUsername(request.getUsernameToProcess())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = userRepository.findByUsername(request.getMainUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<User> invitedUsers = user.getInvitedFriends().stream()
                .map(u -> u.getTo()).collect(Collectors.toList());;
        List<User> invitations = user.getInvitationsToFriends().stream()
                .map(u -> u.getFrom()).collect(Collectors.toList());

        InvitationsToFriends invitation = new InvitationsToFriends(user, userToInvite, new Date());

        //TODO: troche to ulepszyc
        if(!invitedUsers.contains(userToInvite)){
            /*user.getInvitedFriends().add(invitation);*/
            invitationsToFriendsRepository.save(invitation);

      /*      user.getInvitedFriends().add(userToInvite);*/
            return modelMapper.map(userRepository.save(user), UserDTO.class);
        } else {
            return null; //user juz jest zaproszony do znajomych!
        }
    }

    public Page<UserDTO> searchUser(String username, PageRequest pageable) {
        return userRepository.searchUsersByUsername(username, pageable)
                .map(user -> modelMapper.map(user, UserDTO.class));
    }

    public UserDTO acceptUserInvitationToFriends(InvitationUsernamesRequest request) {
        User user = getUser(request.getMainUsername());
/*        User userToAccept = getUser(request.getUsernameToProcess());

        List<User> friendsOfUser = user.getFriends();
        if(friendsOfUser.contains(userToAccept)){
            return null;
        }

        user.getInvitationsToFriends().remove(userToAccept);
        user.getFriends().add(userToAccept);*/
        return modelMapper.map(user, UserDTO.class);
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exist"));
    }
}
