package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.model.entity.WallPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallPostRepository extends JpaRepository<WallPost, Long> {
}
