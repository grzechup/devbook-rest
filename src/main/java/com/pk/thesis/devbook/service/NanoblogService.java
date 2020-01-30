package com.pk.thesis.devbook.service;

import com.pk.thesis.devbook.models.dto.NanoblogPostDTO;
import com.pk.thesis.devbook.models.entity.NanoblogPost;
import com.pk.thesis.devbook.models.entity.User;
import com.pk.thesis.devbook.repository.NanoblogPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NanoblogService {

    private NanoblogPostRepository nanoblogPostRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public NanoblogService(NanoblogPostRepository nanoblogPostRepository,
                           UserService userService,
                           ModelMapper modelMapper) {
        this.nanoblogPostRepository = nanoblogPostRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    public NanoblogPostDTO addNewNanoblogPost(String username, String content) {
        User user = userService.getUserByUsername(username);
        NanoblogPost nanoblogPost = new NanoblogPost(user, content, new Date());
        nanoblogPostRepository.save(nanoblogPost);
        return modelMapper.map(nanoblogPost, NanoblogPostDTO.class);
    }

    public List<NanoblogPostDTO> getNanoblogPosts() {
        return nanoblogPostRepository.findAll().stream()
                .map(n -> modelMapper.map(n, NanoblogPostDTO.class)).collect(Collectors.toList());
    }
}
