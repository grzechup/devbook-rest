package com.pk.thesis.devbook.controllers;

import com.pk.thesis.devbook.models.entity.BoardPost;
import com.pk.thesis.devbook.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping(value = "/posts/{username}")
    public ResponseEntity<List<BoardPost>> getBoardsForUser(@PathVariable("username") String username){
        return ResponseEntity.ok(boardService.getBoardPostsForUser(username));
    }

}
