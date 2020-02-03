package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.NullpointerPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NullpointerPostRepository extends JpaRepository<NullpointerPost, Long> {
}
