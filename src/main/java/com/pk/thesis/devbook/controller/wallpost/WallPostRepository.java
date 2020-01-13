package com.pk.thesis.devbook.controller.wallpost;

import com.pk.thesis.devbook.controller.wallpost.WallPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallPostRepository extends JpaRepository<WallPost, Long> {
}
