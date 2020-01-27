package com.pk.thesis.devbook.service;

import com.pk.thesis.devbook.models.dto.BoardCommentDTO;
import com.pk.thesis.devbook.models.dto.BoardPostDTO;
import com.pk.thesis.devbook.models.entity.BoardComment;
import com.pk.thesis.devbook.models.entity.BoardPost;
import com.pk.thesis.devbook.models.entity.User;
import com.pk.thesis.devbook.repository.BoardCommentRepository;
import com.pk.thesis.devbook.repository.BoardPostRepository;
import com.pk.thesis.devbook.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
    private BoardCommentRepository commentRepository;

    @Autowired
    public BoardService(BoardPostRepository boardRepository,
                        UserRepository userRepository,
                        ModelMapper modelMapper,
                        UserService userService,
                        BoardCommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }


    //TODO: pobrac wszystkich znajomych usera i od nich wyciagnac wszystkie ich boardposty
    public List<BoardPostDTO> getBoardPostsForUser(String username){
        User user = userService.getUserByUsername(username);
        List<BoardPost> boardPosts = new ArrayList();
        boardPosts.addAll(user.getBoardPosts());
        List<BoardPost> friendsPosts = new ArrayList<>();
        friendsPosts.addAll(user.getFriends().stream()
                .map(f -> f.getFrom().getBoardPosts()).collect(Collectors.toList()).stream()
                .collect(ArrayList::new, List::addAll, List::addAll));
        friendsPosts.addAll(user.getFriendsAccepted().stream()
                .map(f -> f.getTo().getBoardPosts()).collect(Collectors.toList()).stream()
                .collect(ArrayList::new, List::addAll, List::addAll));
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
        BoardPost boardPost = getBoardPostById(id);
        User user = userService.getUserByUsername(username);
        boardPost.getLikes().remove(user);
        user.getLikedBoards().remove(boardPost);
        return modelMapper.map(boardPost,BoardPostDTO.class);
    }

    @Transactional
    public BoardCommentDTO commentBoardPost(String username, Long id, String content) {
        BoardPost boardPost = getBoardPostById(id);
        User user = userService.getUserByUsername(username);

        BoardComment comment = new BoardComment(user, content);
        commentRepository.save(comment);
        boardPost.getComments().add(comment);
        user.getBoardComments().add(comment);
        return modelMapper.map(comment,BoardCommentDTO.class);
    }


    private BoardPost getBoardPostById(Long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BoardPost not found"));
    }
}


