package com.pk.thesis.devbook.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 40)
	private String username;

	@Size(max = 50)
	@Email
	private String email;

	@Size(max = 120)
	private String password;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column
	private Date created;

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

	@OneToMany(mappedBy="user")
	private List<BoardPost> boardPosts;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "board_posts__like",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "board_post_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<BoardPost> likedBoards;

	@OneToMany(mappedBy="to")
	private List<Friends> friends;

	@OneToMany(mappedBy="from")
	private List<Friends> friendsAccepted;

	@OneToMany(mappedBy="to")
	private List<InvitationsToFriends> invitationsToFriends;

	@OneToMany(mappedBy="from")
	private List<InvitationsToFriends> invitedFriends;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getExperience() {
		return experience;
	}

	public void setExperience(Long experience) {
		this.experience = experience;
	}

	public List<BoardPost> getBoardPosts() {
		return boardPosts;
	}

	public void setBoardPosts(List<BoardPost> boardPosts) {
		this.boardPosts = boardPosts;
	}

	public List<BoardPost> getLikedBoards() {
		return likedBoards;
	}

	public void setLikedBoards(List<BoardPost> likedBoards) {
		this.likedBoards = likedBoards;
	}

	public List<Friends> getFriends() {
		return friends;
	}

	public void setFriends(List<Friends> friends) {
		this.friends = friends;
	}

	public List<Friends> getFriendsAccepted() {
		return friendsAccepted;
	}

	public void setFriendsAccepted(List<Friends> friendsAccepted) {
		this.friendsAccepted = friendsAccepted;
	}


	public List<InvitationsToFriends> getInvitationsToFriends() {
		return invitationsToFriends;
	}

	public void setInvitationsToFriends(List<InvitationsToFriends> invitationsToFriends) {
		this.invitationsToFriends = invitationsToFriends;
	}


	public List<InvitationsToFriends> getInvitedFriends() {
		return invitedFriends;
	}

	public void setInvitedFriends(List<InvitationsToFriends> invitedFriends) {
		this.invitedFriends = invitedFriends;
	}
}
