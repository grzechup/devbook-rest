package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
