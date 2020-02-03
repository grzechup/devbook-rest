package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.BoardPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {
}
