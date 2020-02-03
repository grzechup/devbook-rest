package com.pk.thesis.devbook.controllers;

import com.pk.thesis.devbook.payload.request.NewCommentRequest;
import com.pk.thesis.devbook.payload.request.NewNullpointerPostRequest;
import com.pk.thesis.devbook.service.NullpointerService;
import com.pk.thesis.devbook.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/nullpointer")
public class NullpointerController {

    private NullpointerService nullpointerService;

    @Autowired
    public NullpointerController(NullpointerService nullpointerService) {
        this.nullpointerService = nullpointerService;
    }

    @PostMapping
    public ResponseEntity<?> addNewNullpointerPost(@RequestBody NewNullpointerPostRequest request){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} added new nullpointer post", username);
        return ResponseEntity.ok(nullpointerService.addNewNullpointerPost(username, request.getTitle(), request.getContent()));
    }

    @GetMapping
    public ResponseEntity<?> getNullpointerPosts(){
        return ResponseEntity.ok(nullpointerService.getAllNullpointerPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNullPointerPost(@PathVariable("id") Long id){
        log.info("Getting nullpointer post with id: {}", id);
        return ResponseEntity.ok(nullpointerService.getNullpointerPost(id));
    }

    @PostMapping("/comment")
    public ResponseEntity<?> addNewCommentToNullpointerPost(@RequestBody NewCommentRequest request){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} added new comment on nullpointer post with id: {}", username, request.getId());
        return ResponseEntity.ok(nullpointerService.addNewCommentToNullPointerPost(
                username, request.getId(), request.getContent()));
    }

    @PostMapping("/comment/upvote")
    public ResponseEntity<?> upvoteNullpointerComment(@RequestBody Long id){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} upvoted nullpointer comment of id: {}", username, id);
        return ResponseEntity.ok(nullpointerService.upvoteNullpointerComment(username, id));
    }

    @PostMapping("/comment/downvote")
    public ResponseEntity<?> downvoteNullpointerComment(@RequestBody Long id){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} downvoted nullpointer comment of id: {}", username, id);
        return ResponseEntity.ok(nullpointerService.downvoteNullpointerComment(username, id));
    }

    @PostMapping("comment/delete")
    public ResponseEntity<?> deleteNullpointerComment(@RequestBody Long id){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} is deleting his nullpointer comment of id: {}", username, id);
        return ResponseEntity.ok(nullpointerService.deleteNullpointerComment( id));
    }

}
