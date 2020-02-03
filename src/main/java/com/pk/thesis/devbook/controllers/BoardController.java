package com.pk.thesis.devbook.controllers;

import com.pk.thesis.devbook.models.dto.BoardPostDTO;
import com.pk.thesis.devbook.payload.request.NewCommentRequest;
import com.pk.thesis.devbook.service.BoardService;
import com.pk.thesis.devbook.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<List<BoardPostDTO>> getBoardsForUser(){
        String username = AuthUtil.getLoggedUsername();
        log.info("Getting board posts for username: {}", username);
        return ResponseEntity.ok(boardService.getBoardPostsForUser(username));
    }

    @PostMapping
    public ResponseEntity<?> addNewBoardPost(@RequestBody String content){
        String username =  AuthUtil.getLoggedUsername();
        log.info("Adding new post for user: {}", username);
        return ResponseEntity.ok(boardService.addNewBoardPost(content, username));
    }

    @PostMapping("/like")
    public ResponseEntity<?> likeBoardPost(@RequestBody Long id){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} liked board post with id: {}", username, id);
        return ResponseEntity.ok(boardService.likeBoardPost(username, id));
    }

    @PostMapping("/unlike")
    public ResponseEntity<?> unlikeBoardPost(@RequestBody Long id){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} unliked baord post with id: {}", username, id);
        return ResponseEntity.ok(boardService.unlikeBoardPost(username, id));
    }

    @PostMapping("/comment")
    public ResponseEntity<?> commentBoardPost(@RequestBody NewCommentRequest request){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} unliked baord post with id: {}", username, request.getId());
        return ResponseEntity.ok(boardService.commentBoardPost(username,request.getId(), request.getContent()));
    }

}
