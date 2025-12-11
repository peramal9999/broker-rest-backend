package com.radianbroker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_groups")
public class UserGroup {

	@Id
	@Column(name = "user_group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userGroupId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	Group group;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	User user;

	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
