package com.pk.thesis.devbook.service;

import com.pk.thesis.devbook.models.dto.NullpointerCommentDTO;
import com.pk.thesis.devbook.models.dto.NullpointerPostDTO;
import com.pk.thesis.devbook.models.dto.NullpointerPostWithCommentsDTO;
import com.pk.thesis.devbook.models.entity.NullpointerComment;
import com.pk.thesis.devbook.models.entity.NullpointerPost;
import com.pk.thesis.devbook.models.entity.User;
import com.pk.thesis.devbook.repository.NullpointerCommentRepository;
import com.pk.thesis.devbook.repository.NullpointerPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NullpointerService {

    private NullpointerPostRepository nullpointerPostRepository;
    private NullpointerCommentRepository nullpointerCommentRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public NullpointerService(NullpointerPostRepository nullpointerPostRepository,
                              NullpointerCommentRepository nullpointerCommentRepository,
                              UserService userService,
                              ModelMapper modelMapper) {
        this.nullpointerPostRepository = nullpointerPostRepository;
        this.nullpointerCommentRepository = nullpointerCommentRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public NullpointerPostDTO addNewNullpointerPost(String username, String title, String content){
        User user = userService.getUserByUsername(username);
        NullpointerPost post = new NullpointerPost(user, title, content, new Date());
        return modelMapper.map(nullpointerPostRepository.save(post), NullpointerPostDTO.class);
    }

    public List<NullpointerPostDTO> getAllNullpointerPosts() {
        return nullpointerPostRepository.findAll().stream()
                .map(p -> modelMapper.map(p, NullpointerPostDTO.class)).collect(Collectors.toList());
    }

    public NullpointerPostDTO getNullpointerPost(Long id) {
        return modelMapper.map(getNullpointerPostById(id), NullpointerPostWithCommentsDTO.class);
    }

    @Transactional
    public NullpointerCommentDTO addNewCommentToNullPointerPost(String username, Long id, String content) {
        NullpointerPost post = getNullpointerPostById(id);
        User user = userService.getUserByUsername(username);

        NullpointerComment comment = new NullpointerComment(user, content);
        nullpointerCommentRepository.save(comment);
        post.getComments().add(comment);
        user.getNullpointerComments().add(comment);
        return modelMapper.map(comment, NullpointerCommentDTO.class);
    }

    @Transactional
    public NullpointerCommentDTO upvoteNullpointerComment(String username, Long id) {
        NullpointerComment comment = getNullpointerCommentById(id);
        User user = userService.getUserByUsername(username);
        Optional<User> pointDown = comment.getPointsDown().stream()
                .filter(pd -> pd.getUsername().equals(username)).findAny();
        if(pointDown.isPresent()){
            comment.getPointsDown().remove(user);
            user.getPointedDownNullpointerComments().remove(comment);
        }
        comment.getPointsUp().add(user);
        user.getPointedUpNullpointerComments().add(comment);
        return modelMapper.map(comment,NullpointerCommentDTO.class);
    }

    @Transactional
    public NullpointerCommentDTO downvoteNullpointerComment(String username, Long id) {
        NullpointerComment comment = getNullpointerCommentById(id);
        User user = userService.getUserByUsername(username);
        Optional<User> pointUp = comment.getPointsUp().stream()
                .filter(pd -> pd.getUsername().equals(username)).findAny();
        if(pointUp.isPresent()){
            comment.getPointsUp().remove(user);
            user.getPointedUpNullpointerComments().remove(comment);
        }
        comment.getPointsDown().add(user);
        user.getPointedDownNullpointerComments().add(comment);
        return modelMapper.map(comment,NullpointerCommentDTO.class);
    }

    @Transactional
    public NullpointerCommentDTO deleteNullpointerComment(Long id) {
        NullpointerComment comment = getNullpointerCommentById(id);
        comment.setIsDeleted(true);
        return modelMapper.map(comment, NullpointerCommentDTO.class);
    }


    private NullpointerPost getNullpointerPostById(Long id){
        NullpointerPost post =  nullpointerPostRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Nullpointer post not exist"));
        List<NullpointerComment> nonDeletedNullpointerComments = post.getComments().stream()
                .filter(c -> !c.getIsDeleted()).collect(Collectors.toList());
        post.setComments(nonDeletedNullpointerComments);
        return post;
    }

    private NullpointerComment getNullpointerCommentById(Long id){
        return nullpointerCommentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Nullpointer comment not exist"));
    }


}
