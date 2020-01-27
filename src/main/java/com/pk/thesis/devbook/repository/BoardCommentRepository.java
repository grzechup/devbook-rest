package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
