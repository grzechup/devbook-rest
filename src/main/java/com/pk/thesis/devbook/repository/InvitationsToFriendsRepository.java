package com.pk.thesis.devbook.repository;

import com.pk.thesis.devbook.models.entity.InvitationsToFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationsToFriendsRepository extends JpaRepository<InvitationsToFriends, Long> {
}
