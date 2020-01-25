package com.pk.thesis.devbook.repository;


import com.pk.thesis.devbook.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

    List<User> findFriendsByUsername(String username);

	@Query("SELECT u FROM User u where username like %?1%")
	Page<User> searchUsersByUsername(String username, Pageable pageable);

	@Query("SELECT u FROM User u where firstname like %:firstname%")
	Optional<List<User>> searchUsersByFirstname(@Param("firstname") String firstname);

	@Query(name = "SELECT * FROM users  INNER JOIN tbl_invites_to_friends \n" +
			"ON tbl_invites_to_friends.invited_person_id = 2\n" +
			"WHERE tbl_invites_to_friends.person_id = users.id",
			nativeQuery = true)
	List<User> findInvitationsToFriendsByUsername(String username);

}
