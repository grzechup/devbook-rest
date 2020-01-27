package com.pk.thesis.devbook.service;

import com.pk.thesis.devbook.models.dto.BoardPostDTO;
import com.pk.thesis.devbook.models.entity.BoardPost;
import com.pk.thesis.devbook.models.entity.User;
import com.pk.thesis.devbook.repository.BoardPostRepository;
import com.pk.thesis.devbook.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BoardService {

    private BoardPostRepository boardRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public BoardService(BoardPostRepository boardRepository,
                        UserRepository userRepository,
                        ModelMapper modelMapper,
                        UserService userService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    //TODO: pobrac wszystkich znajomych usera i od nich wyciagnac wszystkie ich boardposty
    public List<BoardPostDTO> getBoardPostsForUser(String username){
        User user = userService.getUserByUsername(username);
        List<BoardPost> boardPosts = new ArrayList();
        boardPosts.addAll(user.getBoardPosts());
        List<BoardPost> friendsPosts = new ArrayList<>();
        friendsPosts.addAll(user.getFriends().stream()
                .map(friend -> friend.getFrom().getBoardPosts()).findFirst().orElse(Collections.emptyList()));
        friendsPosts.addAll(user.getFriendsAccepted().stream()
                .map(friend -> friend.getTo().getBoardPosts()).findFirst().orElse(Collections.emptyList()));
        boardPosts.addAll(friendsPosts);
        return boardPosts.stream().map(b -> modelMapper.map(b, BoardPostDTO.class)).collect(Collectors.toList());
    }


    public BoardPostDTO addNewBoardPost(String content, String username) {
        BoardPost boardPost = new BoardPost(content,userService.getUserByUsername(username), new Date());
        boardRepository.save(boardPost);
        return modelMapper.map(boardPost, BoardPostDTO.class);
    }

    @Transactional
    public BoardPostDTO likeBoardPost(String username, Long id) {
        BoardPost boardPost = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BoardPost not found"));
        User user = userService.getUserByUsername(username);
        //TODO: sprawdzenie czy boardPost juz jest polubiony przez usera
        boardPost.getLikes().add(user);
        user.getLikedBoards().add(boardPost);
        return modelMapper.map(boardPost,BoardPostDTO.class);
    }

    @Transactional
    public BoardPostDTO unlikeBoardPost(String username, Long id) {
        BoardPost boardPost = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BoardPost not found"));
        User user = userService.getUserByUsername(username);
        boardPost.getLikes().remove(user);
        user.getLikedBoards().remove(boardPost);
        return modelMapper.map(boardPost,BoardPostDTO.class);
    }
}


