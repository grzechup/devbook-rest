package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.NullpointerComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  NullpointerCommentRepository  extends JpaRepository<NullpointerComment, Long> {
}
