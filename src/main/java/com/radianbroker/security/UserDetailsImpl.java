package com.radianbroker.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.radianbroker.entity.Role;
import com.radianbroker.entity.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String firstName;
	private String lastName;

	@JsonIgnore
	private String password;

	private boolean accountNonLocked;
	private boolean enabled;
	private boolean changePasswordAtLogon;
	private Long groupIdForAdmin;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String email, String password, String firstName, String lastName,
			boolean accountNonLocked, boolean enabled, Collection<? extends GrantedAuthority> authorities,
			boolean changePasswordAtLogon, Long groupIdForAdmin) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNonLocked = accountNonLocked;
		this.enabled = enabled;
		this.authorities = authorities;
		this.changePasswordAtLogon = changePasswordAtLogon;
		this.groupIdForAdmin = groupIdForAdmin;
	}

	public static UserDetailsImpl build(User user, List<Role> roles) {

		List<GrantedAuthority> authorities = roles.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase())).collect(Collectors.toList());
		
		return new UserDetailsImpl(user.getUserId(), user.getEmail(), user.getPasswordHash(), user.getFirstName(),
				user.getLastName(), true, user.isStatus(), authorities,
				false, user.getGroupIdForAdmin());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	public boolean isChangePasswordAtLogon() {
		return changePasswordAtLogon;
	}

	public Long getGroupIdForAdmin() {
		return groupIdForAdmin;
	}
	
}