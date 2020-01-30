package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.NanoblogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NanoblogCommentRepository  extends JpaRepository<NanoblogComment, Long> {
}