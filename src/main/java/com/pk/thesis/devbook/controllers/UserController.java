package com.pk.thesis.devbook.controllers;

import com.pk.thesis.devbook.DevbookProperties;
import com.pk.thesis.devbook.models.dto.UserDTO;
import com.pk.thesis.devbook.payload.request.InvitationUsernamesRequest;
import com.pk.thesis.devbook.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    private UserService userService;
    private DevbookProperties properties;


    @Autowired
    public UserController(UserService userService,
                          DevbookProperties properties) {
        this.userService = userService;
        this.properties = properties;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username){
        log.info(properties.getPhotoStorePath());
        return ResponseEntity.ok(userService.getUserDTO(username));
    }

    //TODO: dodac parametry do paginacji
    @GetMapping(value = "/search")
    public ResponseEntity<Page<UserDTO>> searchUserByUsername(@RequestParam("username") Optional<String> username){
        return ResponseEntity.ok(userService.searchUser(username.orElse("_"), PageRequest.of(0,5)));
    }

    //TODO
    @GetMapping(value = "/search/by-names/")
    public ResponseEntity<List<UserDTO>> searchUsersByNames(){
        return null;
    }

    @PostMapping(value = "/invite")
    public ResponseEntity<UserDTO> inviteFriend(@RequestBody InvitationUsernamesRequest request) {
        return ResponseEntity.ok(userService.inviteFriend(request));
    }

    @PostMapping("/invitations/accept")
    public ResponseEntity<UserDTO> acceptUserInvitationToFriends(@RequestBody InvitationUsernamesRequest request){
        return ResponseEntity.ok(userService.acceptUserInvitationToFriends(request));
    }

}
