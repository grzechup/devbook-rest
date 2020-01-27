package com.pk.thesis.devbook.service;

import com.pk.thesis.devbook.models.dto.FriendsAndInvitationsDTO;
import com.pk.thesis.devbook.models.dto.FullUserDTO;
import com.pk.thesis.devbook.models.dto.UserDTO;
import com.pk.thesis.devbook.models.entity.Friends;
import com.pk.thesis.devbook.models.entity.InvitationsToFriends;
import com.pk.thesis.devbook.models.entity.User;
import com.pk.thesis.devbook.payload.request.InvitationUsernamesRequest;
import com.pk.thesis.devbook.repository.FriendsRepository;
import com.pk.thesis.devbook.repository.InvitationsToFriendsRepository;
import com.pk.thesis.devbook.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private InvitationsToFriendsRepository invitationsToFriendsRepository;
    private FriendsRepository friendsRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper,
                       InvitationsToFriendsRepository invitationsToFriendsRepository,
                       FriendsRepository friendsRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.invitationsToFriendsRepository = invitationsToFriendsRepository;
        this.friendsRepository = friendsRepository;
    }

    public UserDTO getUserDTO(String username) {
        return userRepository.findByUsername(username)
                .map(u -> modelMapper.map(u, UserDTO.class))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public FullUserDTO inviteFriend(InvitationUsernamesRequest request) {
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
            return modelMapper.map(userRepository.save(user), FullUserDTO.class);
        } else {
            return null; //user juz jest zaproszony do znajomych!
        }
    }

    public Page<FullUserDTO> searchUser(String username, PageRequest pageable) {
        return userRepository.searchUsersByUsername(username, pageable)
                .map(user -> modelMapper.map(user, FullUserDTO.class));
    }

    public FullUserDTO acceptUserInvitationToFriends(InvitationUsernamesRequest request) {
        User mainUser = getUserByUsername(request.getMainUsername());
        User userToAccept = getUserByUsername(request.getUsernameToProcess());
        Friends friends = new Friends(userToAccept, mainUser, new Date());
        InvitationsToFriends invitationToDelete = mainUser.getInvitationsToFriends().stream()
                .filter(inv -> inv.getFrom().getUsername().equals(userToAccept.getUsername()))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("Invitation not exist"));
        invitationsToFriendsRepository.deleteById(invitationToDelete.getId());
        friendsRepository.save(friends);
        return modelMapper.map(mainUser, FullUserDTO.class);
    }

    public FriendsAndInvitationsDTO getFriendsAndInvitations(String username) {
        User user = getUserByUsername(username);
        return modelMapper.map(user,FriendsAndInvitationsDTO.class);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exist"));
    }

}
