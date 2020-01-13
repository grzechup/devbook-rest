package com.pk.thesis.devbook.controller.wallpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/post"))
public class WallPostController {

    private final WallPostService wallPostService;

    @Autowired
    public WallPostController(WallPostService wallPostService) {
        this.wallPostService = wallPostService;
    }


    @PostMapping
    public ResponseEntity<WallPost> publishPost(@RequestBody WallPostDTO wallPostDTO){
        return ResponseEntity.ok(wallPostService.makePost(wallPostDTO));
    }

}
