package com.pk.thesis.devbook.controllers;

import com.pk.thesis.devbook.service.NanoblogService;
import com.pk.thesis.devbook.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/nanoblog")
public class NanoblogController {

    private NanoblogService nanoblogService;

    @Autowired
    public NanoblogController(NanoblogService nanoblogService) {
        this.nanoblogService = nanoblogService;
    }

    @PostMapping
    public ResponseEntity<?> addNewNanoblogPost(@RequestBody String content){
        String username = AuthUtil.getLoggedUsername();
        log.info("User {} added new nanoblogpost", username);
        return ResponseEntity.ok(nanoblogService.addNewNanoblogPost(username, content));
    }

    @GetMapping
    public ResponseEntity<?> getNanoblogPosts(){
        return ResponseEntity.ok(nanoblogService.getNanoblogPosts());
    }


}
