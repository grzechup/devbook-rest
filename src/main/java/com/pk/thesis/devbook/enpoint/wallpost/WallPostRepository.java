package com.pk.thesis.devbook.enpoint.wallpost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallPostRepository extends JpaRepository<WallPost, Long> {
}
