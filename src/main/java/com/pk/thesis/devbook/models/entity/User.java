package com.pk.thesis.devbook.models.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 40)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@ManyToMany()
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Role> roles = new HashSet<>();

	@Column(name = "experience")
	private Long experience = 0L;

	//TODO: w przyszlosci
/*	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_photos",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "photo_id"))
	private Set<Photo> photos = new HashSet<>();

	@Column(name = "profile_photo_id")
	private Long profilePhotoId;*/

	@ManyToMany()
	@JoinTable(	name = "user_board_posts",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "board_post_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<BoardPost> boardPosts = new HashSet<>();

	@ManyToMany
	@JoinTable(
			name = "board_posts__like",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "board_post_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<BoardPost> likedBoards;


	@ManyToMany
	@JoinTable(name="tbl_friends",
			joinColumns=@JoinColumn(name="personId"),
			inverseJoinColumns=@JoinColumn(name="friendId")
	)
	private List<User> friends;

	@ManyToMany
	@JoinTable(name="tbl_friends",
			joinColumns=@JoinColumn(name="friendId"),
			inverseJoinColumns=@JoinColumn(name="personId")
	)
	private List<User> friendOf;



	@ManyToMany
	@JoinTable(name="tbl_invites_to_friends",
			joinColumns=@JoinColumn(name="personId"),
			inverseJoinColumns=@JoinColumn(name="invited_personId")
	)
	private List<User> invitedFriends;

	@ManyToMany
	@JoinTable(name="tbl_invites_to_friends",
			joinColumns=@JoinColumn(name="invited_personId"),
			inverseJoinColumns=@JoinColumn(name="personId")
	)
	private List<User> invitedToFriends;





	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

}
