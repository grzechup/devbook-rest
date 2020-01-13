package com.pk.thesis.devbook.enpoint.wallpost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pk.ajio.tagger.service.TaggerService;

import java.util.List;

@Service
public class WallPostService {

    private TaggerService taggerService;
    private final WallPostRepository wallPostRepository;

    @Autowired
    public WallPostService(TaggerService taggerService,
                           WallPostRepository wallPostRepository) {
        this.taggerService = taggerService;
        this.wallPostRepository = wallPostRepository;
    }

    public WallPost makePost(WallPostDTO wallPostDTO){
        WallPost wallPost = new WallPost();
        List<String> keywords = taggerService.getListOfKeywordsFromText(wallPostDTO.getContent());
        wallPost.setContent(wallPostDTO.getContent());
        wallPost.setKeywords(keywords);

        return  wallPostRepository.save(wallPost);
    }

}
