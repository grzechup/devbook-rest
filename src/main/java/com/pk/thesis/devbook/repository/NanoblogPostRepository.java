package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.NanoblogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NanoblogPostRepository extends JpaRepository<NanoblogPost, Long> {
}
