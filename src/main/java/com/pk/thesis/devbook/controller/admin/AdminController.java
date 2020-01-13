package com.pk.thesis.devbook.controller.admin;

import com.pk.ajio.tagger.service.UpdateKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UpdateKeywordsService updateKeywordsService;

    @Autowired
    public AdminController(UpdateKeywordsService updateKeywordsService) {
        this.updateKeywordsService = updateKeywordsService;
    }

    @PostMapping("/update-keywords")
    public void updateKeywordsFromStackOverflow(){
        updateKeywordsService.proceed();
    }

}
