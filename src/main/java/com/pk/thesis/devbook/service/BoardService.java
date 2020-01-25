package com.pk.thesis.devbook.service;

import com.pk.thesis.devbook.models.dto.UserDTO;
import com.pk.thesis.devbook.models.entity.BoardPost;
import com.pk.thesis.devbook.models.entity.User;
import com.pk.thesis.devbook.repository.BoardPostRepository;
import com.pk.thesis.devbook.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    private BoardPostRepository boardRepository;
    private UserRepository userRepository;
    private ModelMapper modelmapper;

    @Autowired
    public BoardService(BoardPostRepository boardRepository,
                        UserRepository userRepository,
                        ModelMapper modelmapper) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.modelmapper = modelmapper;
    }


    //TODO: pobrac wszystkich znajomych usera i od nich wyciagnac wszystkie ich boardposty
    public List<BoardPost> getBoardPostsForUser(String username){
        List<User> friendsOfUser = userRepository.findFriendsByUsername(username);
        modelmapper.map(friendsOfUser, UserDTO.class);
        return null;
    }




}


