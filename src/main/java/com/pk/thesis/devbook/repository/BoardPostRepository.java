package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.BoardPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {
}
